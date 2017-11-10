package com.pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pl.model.PersonalModel;
import com.pl.services.PersonalService;

@RestController
public class PersonalController {
	
	@Autowired
	private PersonalService personalService;
	
	@RequestMapping("/produccion/personal/action/save")
	PersonalModel save(@RequestBody PersonalModel personal){
		
		PersonalModel personalModel = this.personalService.save(personal);
		
		return personalModel;
		
	}
	
	@RequestMapping("/produccion/personal/action/find")
	PersonalModel findById(@RequestBody PersonalModel personal){
		
		System.out.println("se recibe el parametro: " + personal.getId());
		
		PersonalModel personalModel = this.personalService.findById(personal);
		
		return personalModel;
		
	}
	
	@RequestMapping("/produccion/personal/action/delete")
	PersonalModel deleteById(@RequestBody PersonalModel personal){
		
		PersonalModel personalModel = this.personalService.deleteById(personal);
		
		return personalModel;
		
	}
	
	@RequestMapping("/produccion/personal/action/all")
	Iterable<PersonalModel> findAll(@RequestBody PersonalModel personal){
		
		Iterable<PersonalModel> personalModels = this.personalService.findAll(personal);
		
		return personalModels;
		
	}

}
