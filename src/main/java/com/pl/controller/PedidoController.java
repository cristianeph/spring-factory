package com.pl.controller;

import java.util.ArrayList;
import java.util.Collection;

import com.pl.model.*;
import com.pl.services.FormulaService;
import com.pl.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pl.services.PedidoService;

@RestController
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;

	@Autowired
    private FormulaService formulaService;

	@Autowired
    private ProductoService productoService;
	
	@RequestMapping("/produccion/pedido/action/save/details")
	PedidoModel saveDetails(@RequestBody PedidoModel pedido){
		
		System.out.println("se reciben detalles: " + pedido.getPedidoPreparados().size());
		
		for (PreparadoModel detalle : pedido.getPedidoPreparados()) {
			
			System.out.println("id del detalle: " + detalle.getId());
			
			detalle.setPedido(pedido);
			
		}
		
		//initial collection
		
		Collection<PreparadoModel> preparadoModelInitial = new ArrayList<PreparadoModel>();
		preparadoModelInitial.addAll(pedido.getPedidoPreparados());
		
		//actual collection
		
		pedido.clearPreparadoModel();
		
		PedidoModel pedidoActualizado = this.pedidoService.save(pedido);
		
		System.out.println("se resetearon los detalles: " + pedidoActualizado.getPedidoPreparados().size());
		
		pedidoActualizado.getPedidoPreparados().clear();
		pedidoActualizado.getPedidoPreparados().addAll(preparadoModelInitial);
		
		System.out.println("tamano guardado de detalles: " + preparadoModelInitial.size());
		
		System.out.println("tamano actual de detalles: " + pedidoActualizado.getPedidoPreparados().size());
		
		for (PreparadoModel detalle : pedidoActualizado.getPedidoPreparados()) {
			
			detalle.setPedido(pedidoActualizado);
			
		}
		
		PedidoModel pedidoProcesado = this.pedidoService.save(pedidoActualizado);
		
		System.out.println(pedidoProcesado.getPedidoPreparados().size());
		
		System.out.println("se actualizaron los detalles");
		
		for (PreparadoModel detalle : pedidoProcesado.getPedidoPreparados()) {
			
			System.out.println("se ha registrado el detalle: " + detalle.getItem());
			
		}
		
		return pedidoProcesado;
		
	}
	
	@RequestMapping("/produccion/pedido/action/save")
	PedidoModel save(@RequestBody PedidoModel pedido){
		
		PedidoModel pedidoModel = this.pedidoService.save(pedido);
		
		if((pedidoModel.getPedidoPreparados().size() == 0) && (pedidoModel.getProducto() != null)){
            ProductoModel productoTemporal = this.productoService.findById(pedidoModel.getProducto().getId());
			System.out.println("El pedido cumple con los requisitos para generar la copia de Formula");
            System.out.println("Producto " + pedidoModel.getProducto().getId());
            System.out.println("Formula: " + productoTemporal.getFormula().getId());
			System.out.println("Tamano: " + productoTemporal.getFormula().getFormulaIngredientes().size());
			
			for (IngredienteModel ingrediente : productoTemporal.getFormula().getFormulaIngredientes()) {
				
				PreparadoModel preparado = new PreparadoModel();
				preparado.setIdInsumo(ingrediente.getInsumo().getId());
				preparado.setDescripcion(ingrediente.getInsumo().getDescripcion());
				preparado.setCosto(ingrediente.getInsumo().getCosto());
				preparado.setCantidad(ingrediente.getCantidad());
				preparado.setCostoTotal(ingrediente.getCantidad().multiply(ingrediente.getCantidad()));
				preparado.setItem(ingrediente.getItem());
				preparado.setRelacion(ingrediente.getInsumo().getRelacion());
				preparado.setPedido(pedido);
				
				pedido.getPedidoPreparados().add(preparado);
				
			}
			
			this.pedidoService.save(pedido);
			
		}
		
		return pedidoModel;
		
	}
	
	@RequestMapping("/produccion/pedido/action/find")
	PedidoModel findById(@RequestBody PedidoModel pedido){
		
		System.out.println("se recibe el parametro: " + pedido.getId());
		
		PedidoModel pedidoModel = this.pedidoService.findById(pedido);
		
		return pedidoModel;
		
	}
	
	@RequestMapping("/produccion/pedido/action/delete")
	PedidoModel deleteById(@RequestBody PedidoModel pedido){
		
		PedidoModel pedidoModel = this.pedidoService.deleteById(pedido);
		
		return pedidoModel;
		
	}
	
	@RequestMapping("/produccion/pedido/action/all")
	Iterable<PedidoModel> findAll(@RequestBody PedidoModel pedido){
		Iterable<PedidoModel> pedidoModels = this.pedidoService.findAll(pedido);
		return pedidoModels;
	}
	
	@RequestMapping(
			value = "/api/produccion/pedido",
			params = {"page", "size"},
			method = RequestMethod.GET)
	Iterable<PedidoModel> getAll(@RequestParam Integer page, @RequestParam Integer size){
		return this.pedidoService.getAll(new PageRequest((page-1), size));
	}

}
