package com.pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pl.model.PeriodoModel;
import com.pl.services.PeriodoService;

@RestController
public class PeriodoController {
	
	@Autowired
	private PeriodoService periodoService;
	
	@RequestMapping("/production/periodo/action/save")
	PeriodoModel save(@RequestBody PeriodoModel periodo){
		
		PeriodoModel periodoModel = this.periodoService.save(periodo);
		
		return periodoModel;
		
	}
	
	@RequestMapping("/production/periodo/action/find")
	PeriodoModel findById(@RequestBody PeriodoModel periodo){
		
		System.out.println("se recibe el parametro: " + periodo.getId());
		
		PeriodoModel periodoModel = this.periodoService.findById(periodo);
		
		return periodoModel;
		
	}
	
	@RequestMapping("/production/periodo/action/delete")
	PeriodoModel deleteById(@RequestBody PeriodoModel periodo){
		
		PeriodoModel periodoModel = this.periodoService.deleteById(periodo);
		
		return periodoModel;
		
	}
	
	@RequestMapping("/production/periodo/action/all")
	Iterable<PeriodoModel> findAll(@RequestBody PeriodoModel periodo){
		
		Iterable<PeriodoModel> periodoModels = this.periodoService.findAll(periodo);
		
		return periodoModels;
		
	}

}
