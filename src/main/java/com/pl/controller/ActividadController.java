package com.pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pl.model.ActividadModel;
import com.pl.services.ActividadService;

@RestController
@EnableAutoConfiguration
public class ActividadController {
	
	@Autowired
	private ActividadService actividadService;
	
	@RequestMapping("/production/actividad/action/save")
	ActividadModel save(@RequestBody ActividadModel actividad){
		
		ActividadModel actividadModel = this.actividadService.save(actividad);
		
		return actividadModel;
		
	}
	
	@RequestMapping("/production/actividad/action/find")
	ActividadModel findById(@RequestBody ActividadModel actividad){
		
		System.out.println("se recibe el parametro: " + actividad.getId());
		
		ActividadModel actividadModel = this.actividadService.findById(actividad);
		
		return actividadModel;
		
	}
	
	@RequestMapping("/production/actividad/action/delete")
	ActividadModel deleteById(@RequestBody ActividadModel actividad){
		
		ActividadModel actividadModel = this.actividadService.deleteById(actividad);
		
		return actividadModel;
		
	}
	
	@RequestMapping("/production/actividad/action/all")
	Iterable<ActividadModel> findAll(@RequestBody ActividadModel actividad){
		
		Iterable<ActividadModel> actividadModels = this.actividadService.findAll(actividad);
		
		return actividadModels;
		
	}

}
