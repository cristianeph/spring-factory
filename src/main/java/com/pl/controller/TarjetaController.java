package com.pl.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.pl.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pl.model.ActividadModel;
import com.pl.model.MaquinaModel;
import com.pl.model.PlanModel;
import com.pl.model.ParteProduccionModel;
import com.pl.model.TrabajoModel;

@RestController
public class TarjetaController {
	
	@Autowired
	private TarjetaService tarjetaService;

    @Autowired
    private TrabajoService trabajoService;
	
	@Autowired
	private PlanService planService;
	
	@Autowired
	private MaquinaService maquinaService;
	
	@Autowired
	private ActividadService actividadService;
	
	@RequestMapping("/production/parteproduccion/action/save")
    ParteProduccionModel save(@RequestBody ParteProduccionModel parteproduccion){
		return this.tarjetaService.save(parteproduccion);
	}

	@RequestMapping("/production/parteproduccion/action/find")
	ParteProduccionModel findById(@RequestBody ParteProduccionModel tarjeta){
		return this.tarjetaService.findById(tarjeta);
	}

	@RequestMapping("/production/card/action/delete")
    ParteProduccionModel deleteById(@RequestBody ParteProduccionModel tarjeta){
		this.tarjetaService.deleteById(tarjeta);
		return null;
	}

	@RequestMapping("/production/card/action/delete/document/{id}")
    ParteProduccionModel deleteByDocument(@PathVariable Integer id){
		ParteProduccionModel tarjeta = new ParteProduccionModel();
		tarjeta.setId(id);
		this.tarjetaService.deleteById(tarjeta);
		return null;
	}

	@RequestMapping("/production/card/action/all")
	Iterable<ParteProduccionModel> findAll(@RequestBody ParteProduccionModel tarjeta){
		Iterable<ParteProduccionModel> tarjetaModels = this.tarjetaService.findAll(tarjeta);
		return tarjetaModels;
	}

	@RequestMapping(
		value = "/api/production/parteproduccion",
		method = RequestMethod.POST)
    ParteProduccionModel saveOne(@RequestBody ParteProduccionModel parte){
        return this.tarjetaService.save(parte);
	}
	
	@RequestMapping(
		value = "/api/production/parteproduccion",
		params = {"page", "size"},
		method = RequestMethod.GET)
	Iterable<ParteProduccionModel> getAll(@RequestParam Integer page, @RequestParam Integer size){
		return this.tarjetaService.getAll(new PageRequest((page - 1), size));
	}
	
	@RequestMapping(
		value = "/api/production/parteproduccion/{id}",
		method = RequestMethod.GET)
    ParteProduccionModel getById(@PathVariable Integer id){
		return this.tarjetaService.getById(id);
	}

}
