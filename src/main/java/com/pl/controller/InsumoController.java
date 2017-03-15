package com.pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pl.model.InsumoModel;
import com.pl.model.KardexInsumoModel;
import com.pl.services.InsumoService;
import com.pl.services.KardexService;

@RestController
@EnableAutoConfiguration
public class InsumoController {
	
	@Autowired
	private InsumoService insumoService;
	
	@Autowired
	private KardexService kardexService;
	
	@RequestMapping("/production/insumo/action/find")
	Iterable<KardexInsumoModel> findByRelacion(@RequestBody InsumoModel insumo){
		
		System.out.println("recibio: " + insumo.getId());
		System.out.println("recibio: " + insumo.getRelacion());
		Iterable<KardexInsumoModel> insumoModels = this.kardexService.sumStockByRelacion(insumo.getRelacion());
		
		return insumoModels;
		
	}
	
	@RequestMapping("/production/insumo/action/all")
	Iterable<InsumoModel> findAll(@RequestBody InsumoModel insumo){
		
		Iterable<InsumoModel> insumoModels = this.insumoService.findAll(insumo);
		
		return insumoModels;
		
	}

}
