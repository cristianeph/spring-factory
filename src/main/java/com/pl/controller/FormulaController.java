package com.pl.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pl.model.FormulaModel;
import com.pl.model.IngredienteModel;
import com.pl.model.InsumoModel;
import com.pl.services.FormulaService;
import com.pl.services.InsumoService;

@RestController
public class FormulaController {
	
	@Autowired
	private FormulaService formulaService;
	
	@Autowired
	private InsumoService insumoService;
	
	@RequestMapping("/produccion/formula/action/save")
	FormulaModel save(@RequestBody FormulaModel formula){
		
		System.out.println(formula.getId());
		
		for (IngredienteModel detalle : formula.getFormulaIngredientes()) {
			
			System.out.println("id del detalle: " + detalle.getId());
			
			InsumoModel insumoEncontrado = insumoService.findById(detalle.getInsumo().getId());
			System.out.println("se encontro el plan actuaizado: " + insumoEncontrado.getId());
			detalle.setInsumo(insumoEncontrado);
			
			detalle.setFormula(formula);
			
		}
		
		//initial collection
		
		Collection<IngredienteModel> ingredienteModelInitial = new ArrayList<IngredienteModel>();
		ingredienteModelInitial.addAll(formula.getFormulaIngredientes());
		
		//actual collection
		
		formula.clearIngredienteModel();
		
		FormulaModel formulaActualizada = this.formulaService.save(formula);
		
		System.out.println("se resetearon los detalles: " + formulaActualizada.getFormulaIngredientes().size());
		
		formulaActualizada.getFormulaIngredientes().clear();
		formulaActualizada.getFormulaIngredientes().addAll(ingredienteModelInitial);
		
		System.out.println("tamano guardado de detalles: " + ingredienteModelInitial.size());
		
		System.out.println("tamano actual de detalles: " + formulaActualizada.getFormulaIngredientes().size());
		
		for (IngredienteModel detalle : formulaActualizada.getFormulaIngredientes()) {
			
			detalle.setFormula(formulaActualizada);
			
		}
		
		FormulaModel formulaProcesada = this.formulaService.save(formulaActualizada);
		
		System.out.println(formulaProcesada.getFormulaIngredientes().size());
		
		System.out.println("se actualizaron los detalles");
		
		for (IngredienteModel detalle : formulaProcesada.getFormulaIngredientes()) {
			
			System.out.println("se ha registrado el detalle: " + detalle.getItem());
			
		}
		
		return formulaProcesada;
		
	}
	
	@RequestMapping("/produccion/formula/action/find")
	FormulaModel findById(@RequestBody FormulaModel formula){
		
		System.out.println("se recibe el parametro: " + formula.getId());
		
		FormulaModel formulaModel = this.formulaService.findById(formula);
		
		return formulaModel;
		
	}
	
	@RequestMapping("/produccion/formula/action/delete")
	FormulaModel deleteById(@RequestBody FormulaModel formula){
		
		this.formulaService.deleteById(formula);
		
		return null;
		
	}
	
	@RequestMapping("/produccion/formula/action/delete/document/{id}")
	FormulaModel deleteByDocument(@PathVariable Integer id){
		
		FormulaModel formula = new FormulaModel();
		formula.setId(id);
		
		this.formulaService.deleteById(formula);
		
		return null;
		
	}
	
	@RequestMapping("/produccion/formula/action/all")
	Iterable<FormulaModel> findAll(@RequestBody FormulaModel formula){
		return this.formulaService.findAll(formula);
	}
	
	@RequestMapping(
		value = "/api/produccion/formula",
		params = {"page", "size"},
		method = RequestMethod.POST)
	Iterable<FormulaModel> getAll(@RequestParam Integer page, @RequestParam Integer size){
		return this.formulaService.getAll(new PageRequest((page - 1), size));
	}

}
