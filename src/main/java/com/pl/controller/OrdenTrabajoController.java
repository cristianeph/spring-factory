package com.pl.controller;

import com.pl.model.MaquinaActividadModel;
import com.pl.services.ActividadService;
import com.pl.services.MaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pl.model.OrdenTrabajoModel;
import com.pl.services.TrabajoService;

@RestController
public class OrdenTrabajoController {
	
	@Autowired
	private TrabajoService trabajoService;
	@Autowired
	private MaquinaService maquinaService;
	@Autowired
	private ActividadService actividadService;
	
	@RequestMapping("/production/trabajo/action/save")
	OrdenTrabajoModel save(@RequestBody OrdenTrabajoModel trabajo){
		
		OrdenTrabajoModel ordenTrabajoModel = this.trabajoService.save(trabajo);
		
		return ordenTrabajoModel;
		
	}
	
	@RequestMapping("/production/trabajo/action/find")
	OrdenTrabajoModel findById(@RequestBody OrdenTrabajoModel trabajo){
		
		System.out.println("se recibe el parametro: " + trabajo.getId());
		
		OrdenTrabajoModel ordenTrabajoModel = this.trabajoService.findById(trabajo);
		
		return ordenTrabajoModel;
		
	}
	
	@RequestMapping("/production/trabajo/action/delete")
	OrdenTrabajoModel deleteById(@RequestBody OrdenTrabajoModel trabajo){
		
		OrdenTrabajoModel ordenTrabajoModel = this.trabajoService.deleteById(trabajo);
		
		return ordenTrabajoModel;
		
	}
	
	@RequestMapping("/production/trabajo/action/all")
	Iterable<OrdenTrabajoModel> findAll(@RequestBody OrdenTrabajoModel trabajo){
		
		Iterable<OrdenTrabajoModel> trabajoModels = this.trabajoService.findAll(trabajo);
		
		return trabajoModels;
		
	}

	@RequestMapping(
			value = "/api/production/trabajo/invalid",
			params = {"page", "size"},
			method = RequestMethod.GET)
	Iterable<OrdenTrabajoModel> getAllInvalid(@RequestParam Integer page, @RequestParam Integer size){
		return this.trabajoService.getAllInvalid(new PageRequest((page - 1), size));
	}

	@RequestMapping(
			value = "/api/production/trabajo",
			method = RequestMethod.POST)
	OrdenTrabajoModel saveOne(@RequestBody OrdenTrabajoModel trabajo){
		return this.trabajoService.save(trabajo);
	}
	
	@RequestMapping(
		value = "/api/production/trabajo", 
		params = {"page", "size"},
		method = RequestMethod.GET)
	Iterable<OrdenTrabajoModel> getAll(@RequestParam Integer page, @RequestParam Integer size){
		return this.trabajoService.getAll(new PageRequest((page - 1), size));
	}
	
	@RequestMapping(
		value = "/api/production/trabajo/{id}",
		method = RequestMethod.GET)
	OrdenTrabajoModel getById(@PathVariable Integer id){
		return this.trabajoService.getById(id);
	}

}
