package com.pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pl.model.MaquinaModel;
import com.pl.services.MaquinaService;

@RestController
@EnableAutoConfiguration
public class MaquinaController {
	
	@Autowired
	private MaquinaService maquinaService;
	
	@RequestMapping("/production/maquina/action/save")
	MaquinaModel save(@RequestBody MaquinaModel maquina){
		
		MaquinaModel maquinaModel = this.maquinaService.save(maquina);
		
		return maquinaModel;
		
	}
	
	@RequestMapping("/production/maquina/action/find")
	MaquinaModel findById(@RequestBody MaquinaModel maquina){
		
		System.out.println("se recibe el parametro: " + maquina.getId());
		
		MaquinaModel maquinaModel = this.maquinaService.findById(maquina);
		
		return maquinaModel;
		
	}
	
	@RequestMapping("/production/maquina/action/delete")
	MaquinaModel deleteById(@RequestBody MaquinaModel maquina){
		
		MaquinaModel maquinaModel = this.maquinaService.deleteById(maquina);
		
		return maquinaModel;
		
	}
	
	@RequestMapping("/production/maquina/action/all")
	Iterable<MaquinaModel> findAll(@RequestBody MaquinaModel maquina){
		
		Iterable<MaquinaModel> maquinaModels = this.maquinaService.findAll(maquina);
		
		return maquinaModels;
		
	}

}
