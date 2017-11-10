package com.pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pl.model.InsumoModel;
import com.pl.model.KardexInsumoModel;
import com.pl.services.InsumoService;
import com.pl.services.KardexService;

@RestController
public class InsumoController {
	
	@Autowired
	private InsumoService insumoService;
	
	@Autowired
	private KardexService kardexService;
	
	@RequestMapping("/produccion/insumo/{id}/{usuario}")
	InsumoModel findWithId(@PathVariable Integer id){
		return this.insumoService.findById(id);
	}
	
	@RequestMapping("/produccion/insumo/action/find")
	Iterable<KardexInsumoModel> findByRelacion(@RequestBody InsumoModel insumo){
		
		System.out.println("recibio: " + insumo.getId());
		System.out.println("recibio: " + insumo.getRelacion());
		Iterable<KardexInsumoModel> insumoModels = this.kardexService.sumStockByRelacion(insumo.getRelacion());
		
		return insumoModels;
		
	}
	
	@RequestMapping("/produccion/insumo/action/all")
	Iterable<InsumoModel> findAll(@RequestBody InsumoModel insumo){
		Iterable<InsumoModel> insumoModels = this.insumoService.findAll(insumo);
		return insumoModels;
	}
	
	@RequestMapping(
		value = "/api/produccion/insumo",
		method = RequestMethod.POST)
	InsumoModel saveOne(@RequestBody InsumoModel insumo){
		return this.insumoService.save(insumo);
	}

	@RequestMapping(
			value = "/api/produccion/insumo",
			method = RequestMethod.GET)
	Iterable<InsumoModel> all(){
		return this.insumoService.getAll(new PageRequest(0, Integer.MAX_VALUE));
	}
	
	@RequestMapping(
		value = "/api/produccion/insumo",
		params = {"page", "size"},
		method = RequestMethod.GET)
	Iterable<InsumoModel> getAll(@RequestParam Integer page, @RequestParam Integer size){
		return this.insumoService.getAll(new PageRequest((page - 1), size));
	}
	
	@RequestMapping(
		value = "/api/produccion/insumo/{id}",
		method = RequestMethod.GET)
	InsumoModel getById(@PathVariable Integer id){
		return this.insumoService.getById(id);
	}

}
