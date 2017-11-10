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

import com.pl.model.ClienteModel;
import com.pl.services.ClienteService;

@RestController
@RequestMapping("/api/produccion")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@RequestMapping("/action/save")
	ClienteModel save(@RequestBody ClienteModel cliente){
		ClienteModel clienteModel = this.clienteService.save(cliente);
		return clienteModel;
	}
	
	@RequestMapping("/action/find")
	ClienteModel findById(@RequestBody ClienteModel cliente){
		System.out.println("se recibe el parametro: " + cliente.getId());
		ClienteModel clienteModel = this.clienteService.findById(cliente);
		return clienteModel;
	}
	
	@RequestMapping("/action/delete")
	ClienteModel deleteById(@RequestBody ClienteModel cliente){
		ClienteModel clienteModel = this.clienteService.deleteById(cliente);
		return clienteModel;
	}
	
	@RequestMapping("/all")
	Iterable<ClienteModel> findAll(@RequestBody ClienteModel cliente){
		Iterable<ClienteModel> clienteModels = this.clienteService.findAll(cliente);
		return clienteModels;
	}
	
	@RequestMapping(
		value = "/cliente", 
		method = RequestMethod.POST)
	ClienteModel saveOne(@RequestBody ClienteModel cliente){
		return this.clienteService.save(cliente);
	}
	
	@RequestMapping(
		value = "/cliente", 
		params = {"page", "size"},
		method = RequestMethod.GET)
	Iterable<ClienteModel> getAll(@RequestParam Integer page, @RequestParam Integer size){
		return this.clienteService.getAll(new PageRequest((page - 1), size));
	}
	
	@RequestMapping(
		value = "/cliente/{id}",
		method = RequestMethod.GET)
	ClienteModel getById(@PathVariable Integer id){
		return this.clienteService.getById(id);
	}

}
