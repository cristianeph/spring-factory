package com.pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pl.model.TrabajoModel;
import com.pl.services.TrabajoService;

@RestController
@EnableAutoConfiguration
public class TrabajoController {
	
	@Autowired
	private TrabajoService trabajoService;
	
	@RequestMapping("/production/trabajo/action/save")
	TrabajoModel save(@RequestBody TrabajoModel trabajo){
		
		TrabajoModel trabajoModel = this.trabajoService.save(trabajo);
		
		return trabajoModel;
		
	}
	
	@RequestMapping("/production/trabajo/action/find")
	TrabajoModel findById(@RequestBody TrabajoModel trabajo){
		
		System.out.println("se recibe el parametro: " + trabajo.getId());
		
		TrabajoModel trabajoModel = this.trabajoService.findById(trabajo);
		
		return trabajoModel;
		
	}
	
	@RequestMapping("/production/trabajo/action/delete")
	TrabajoModel deleteById(@RequestBody TrabajoModel trabajo){
		
		TrabajoModel trabajoModel = this.trabajoService.deleteById(trabajo);
		
		return trabajoModel;
		
	}
	
	@RequestMapping("/production/trabajo/action/all")
	Iterable<TrabajoModel> findAll(@RequestBody TrabajoModel trabajo){
		
		Iterable<TrabajoModel> trabajoModels = this.trabajoService.findAll(trabajo);
		
		return trabajoModels;
		
	}
	
	@RequestMapping(
		value = "/api/production/trabajo", 
		params = {"page", "size"},
		method = RequestMethod.POST)
	Iterable<TrabajoModel> getAll(@RequestParam Integer page, @RequestParam Integer size){
		return this.trabajoService.getAll(new PageRequest((page - 1), size));
	}

}
