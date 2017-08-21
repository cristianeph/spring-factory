package com.pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pl.model.ClienteModel;
import com.pl.services.ClienteService;

@RestController
@EnableAutoConfiguration
@RequestMapping("/production/cliente/action")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping("/save")
	ClienteModel save(@RequestBody ClienteModel cliente){
		ClienteModel clienteModel = this.clienteService.save(cliente);
		return clienteModel;
	}
	
	@RequestMapping("/find")
	ClienteModel findById(@RequestBody ClienteModel cliente){
		System.out.println("se recibe el parametro: " + cliente.getId());
		ClienteModel clienteModel = this.clienteService.findById(cliente);
		return clienteModel;
	}
	
	@RequestMapping("/delete")
	ClienteModel deleteById(@RequestBody ClienteModel cliente){
		ClienteModel clienteModel = this.clienteService.deleteById(cliente);
		return clienteModel;
	}
	
	@RequestMapping("/all")
	Iterable<ClienteModel> findAll(@RequestBody ClienteModel cliente){
		Iterable<ClienteModel> clienteModels = this.clienteService.findAll(cliente);
		return clienteModels;
	}

}
