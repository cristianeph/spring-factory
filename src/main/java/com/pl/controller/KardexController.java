package com.pl.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pl.model.InsumoModel;
import com.pl.model.KardexModel;
import com.pl.model.KardexRelacionModel;
import com.pl.model.PlanModel;
import com.pl.model.PreparadoModel;
import com.pl.services.KardexService;

@RestController
@EnableAutoConfiguration
public class KardexController {
	
	@Autowired
	private KardexService kardexService;
	
	@RequestMapping("/production/kardex/action/find/sum")
	Iterable<KardexRelacionModel> sumKardexByRelacion(@RequestBody InsumoModel insumo){
		
		System.out.println("recibio: " + insumo.getId());
		System.out.println("recibio: " + insumo.getRelacion());
		
		Iterable<KardexRelacionModel> kardexRelaciones = this.kardexService.sumKardexByRelacion(insumo.getRelacion());
		
		return kardexRelaciones;
		
	}
	
	@RequestMapping("/production/kardex/action/find")
	Iterable<KardexModel> findByCodigoInsumo(@RequestBody PlanModel plan){
		
		System.out.println("recibio: " + plan.getEstado());
		
		List<KardexModel> kardexList = new ArrayList<KardexModel>();
		
		for (PreparadoModel preparadoModel : plan.getPedido().getPedidoPreparados()) {
			
			System.out.println("formula: " + preparadoModel.getDescripcion());
			System.out.println("formula: " + preparadoModel.getCantidad());
			
			KardexModel kardexModel = this.kardexService.findByIdInsumo(preparadoModel.getIdInsumo());
			kardexList.add(kardexModel);
			
		}
		
		return kardexList;
		
	}

}
