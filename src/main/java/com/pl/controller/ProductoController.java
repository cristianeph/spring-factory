package com.pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.pl.model.ProductoModel;
import com.pl.services.ProductoService;

@RestController
@RequestMapping("/api/produccion")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	@RequestMapping("/producto/action/save")
	ProductoModel save(@RequestBody ProductoModel producto){
		
		ProductoModel productoModel = this.productoService.save(producto);
		
		return productoModel;
		
	}
	
	@RequestMapping("/producto/action/find")
	ProductoModel findById(@RequestBody ProductoModel producto){
		System.out.println("se recibe el parametro: " + producto.getId());
		ProductoModel productoModel = this.productoService.findById(producto.getId());
		return productoModel;
	}
	
	@RequestMapping("/producto/{id}")
	ProductoModel findWithId(@PathVariable Integer id){
		return this.productoService.findById(id);
	}
	
	@RequestMapping("/producto/action/delete/document/{id}")
	ProductoModel deleteById(@PathVariable Integer id){
		
		ProductoModel producto = new ProductoModel();
		producto.setId(id);
		
		this.productoService.deleteById(producto);
		
		return null;
		
	}
	
	@RequestMapping("/producto/action/all")
	Iterable<ProductoModel> findAll(@RequestBody ProductoModel producto){
		System.out.println("=>");
		System.out.println(producto.getDescripcion());
		Iterable<ProductoModel> productoModels = this.productoService.findAll(producto);
		return productoModels;
	}

	@RequestMapping(
        value = "/producto",
        params = {"page", "size"},
        method = RequestMethod.GET)
    Iterable<ProductoModel> getAll(@RequestParam Integer page, @RequestParam Integer size) {
	    return this.productoService.getAll(new PageRequest((page - 1), size));
    }

}
