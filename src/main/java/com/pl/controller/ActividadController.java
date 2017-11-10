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

import com.pl.model.ActividadModel;
import com.pl.services.ActividadService;

@RestController
public class ActividadController {
	
	@Autowired
	private ActividadService actividadService;
	
	@RequestMapping("/produccion/actividad/action/save")
	ActividadModel save(@RequestBody ActividadModel actividad){
		
		ActividadModel actividadModel = this.actividadService.save(actividad);
		
		return actividadModel;
		
	}
	
	@RequestMapping("/produccion/actividad/action/find")
	ActividadModel findById(@RequestBody ActividadModel actividad){
		
		System.out.println("se recibe el parametro: " + actividad.getId());
		
		ActividadModel actividadModel = this.actividadService.findById(actividad);
		
		return actividadModel;
		
	}
	
	@RequestMapping("/produccion/actividad/action/delete")
	ActividadModel deleteById(@RequestBody ActividadModel actividad){
		
		ActividadModel actividadModel = this.actividadService.deleteById(actividad);
		
		return actividadModel;
		
	}
	
	@RequestMapping("/produccion/actividad/action/all")
	Iterable<ActividadModel> findAll(@RequestBody ActividadModel actividad){
		
		Iterable<ActividadModel> actividadModels = this.actividadService.findAll(actividad);
		
		return actividadModels;
		
	}
	
	@RequestMapping(
		value = "/api/produccion/actividad",
		method = RequestMethod.POST)
	ActividadModel getAll(@RequestBody ActividadModel actividad){
		return this.actividadService.save(actividad);
	}
	
	@RequestMapping(
		value = "/api/produccion/actividad",
		params = {"page", "size"},
		method = RequestMethod.GET)
	Iterable<ActividadModel> getAll(@RequestParam Integer page, @RequestParam Integer size){
		return this.actividadService.getAll(new PageRequest((page - 1), size));
	}
	
	@RequestMapping(
		value = "/api/produccion/actividad/{id}",
		method = RequestMethod.GET)
	ActividadModel getById(@PathVariable Integer id){
		return this.actividadService.getById(id);
	}

}
