package com.pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pl.model.ProductoModel;
import com.pl.services.ProductoService;

@RestController
@EnableAutoConfiguration
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	@RequestMapping("/production/producto/action/save")
	ProductoModel save(@RequestBody ProductoModel producto){
		
		ProductoModel productoModel = this.productoService.save(producto);
		
		return productoModel;
		
	}
	
	@RequestMapping("/production/producto/action/find")
	ProductoModel findById(@RequestBody ProductoModel producto){
		
		System.out.println("se recibe el parametro: " + producto.getId());
		
		ProductoModel productoModel = this.productoService.findById(producto);
		
		return productoModel;
		
	}
	
	@RequestMapping("/production/producto/action/delete/document/{id}")
	ProductoModel deleteById(@PathVariable Integer id){
		
		ProductoModel producto = new ProductoModel();
		producto.setId(id);
		
		this.productoService.deleteById(producto);
		
		return null;
		
	}
	
	@RequestMapping("/production/producto/action/all")
	Iterable<ProductoModel> findAll(@RequestBody ProductoModel producto){
		
		Iterable<ProductoModel> productoModels = this.productoService.findAll(producto);
		
		return productoModels;
		
	}

}
