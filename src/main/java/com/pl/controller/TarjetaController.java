package com.pl.controller;

import java.util.ArrayList;
import java.util.Collection;

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
import com.pl.model.MaquinaModel;
import com.pl.model.PlanModel;
import com.pl.model.TarjetaModel;
import com.pl.model.TrabajoModel;
import com.pl.services.ActividadService;
import com.pl.services.MaquinaService;
import com.pl.services.PlanService;
import com.pl.services.TarjetaService;

@RestController
@EnableAutoConfiguration
public class TarjetaController {
	
	@Autowired
	private TarjetaService tarjetaService;
	
	@Autowired
	private PlanService planService;
	
	@Autowired
	private MaquinaService maquinaService;
	
	@Autowired
	private ActividadService actividadService;
	
	@RequestMapping("/production/card/action/save")
	TarjetaModel save(@RequestBody TarjetaModel tarjeta){
		
		System.out.println(tarjeta.getId());
		
		for (TrabajoModel detalle : tarjeta.getTarjetaTrabajos()) {
			
			System.out.println("id del detalle: " + detalle.getId());
			
			PlanModel planEncontrado = planService.findById(detalle.getPlan());
			System.out.println("se encontro el plan actuaizado: " + planEncontrado.getId());
			detalle.setPlan(planEncontrado);
			
			MaquinaModel maquinaEncontrado = maquinaService.findById(detalle.getMaquina());
			System.out.println("se encontro la maquina actuaizado: " + maquinaEncontrado.getId() + " : " + maquinaEncontrado.getDescripcion());
			detalle.setMaquina(maquinaEncontrado);
			
			ActividadModel actividadEncontrado = actividadService.findById(detalle.getActividad());
			System.out.println("se encontro la actividad actuaizado: " + actividadEncontrado.getId() + " : " + actividadEncontrado.getDescripcion());
			detalle.setActividad(actividadEncontrado);
			
			detalle.setTarjeta(tarjeta);
			
		}
		
		//initial collection
		
		Collection<TrabajoModel> trabajoModelInitial = new ArrayList<TrabajoModel>();
		trabajoModelInitial.addAll(tarjeta.getTarjetaTrabajos());
		
		//actual collection
		
		tarjeta.clearTrabajoModel();
		
		TarjetaModel tarjetaActualizada = this.tarjetaService.save(tarjeta);
		
		System.out.println("se resetearon los detalles: " + tarjetaActualizada.getTarjetaTrabajos().size());
		
		tarjetaActualizada.getTarjetaTrabajos().clear();
		tarjetaActualizada.getTarjetaTrabajos().addAll(trabajoModelInitial);
		
		System.out.println("tamano guardado de detalles: " + trabajoModelInitial.size());
		
		System.out.println("tamano actual de detalles: " + tarjetaActualizada.getTarjetaTrabajos().size());
		
		for (TrabajoModel detalle : tarjetaActualizada.getTarjetaTrabajos()) {
			
			detalle.setTarjeta(tarjetaActualizada);
			
		}
		
		TarjetaModel tarjetaProcesada = this.tarjetaService.save(tarjetaActualizada);
		
		System.out.println(tarjetaProcesada.getTarjetaTrabajos().size());
		
		System.out.println("se actualizaron los detalles");
		
		for (TrabajoModel detalle : tarjetaProcesada.getTarjetaTrabajos()) {
			
			System.out.println("se ha registrado el detalle: " + detalle.getItem());
			
		}
		
		return tarjetaProcesada;
		
	}
	
	@RequestMapping("/production/card/action/delete")
	TarjetaModel deleteById(@RequestBody TarjetaModel tarjeta){
		this.tarjetaService.deleteById(tarjeta);
		return null;
	}
	
	@RequestMapping("/production/card/action/delete/document/{id}")
	TarjetaModel deleteByDocument(@PathVariable Integer id){
		TarjetaModel tarjeta = new TarjetaModel();
		tarjeta.setId(id);
		this.tarjetaService.deleteById(tarjeta);
		return null;
	}
	
	@RequestMapping("/production/card/action/all")
	Iterable<TarjetaModel> findAll(@RequestBody TarjetaModel tarjeta){
		Iterable<TarjetaModel> tarjetaModels = this.tarjetaService.findAll(tarjeta);
		return tarjetaModels;
	}
	
	@RequestMapping("/production/card/action/find")
	TarjetaModel findById(@RequestBody TarjetaModel tarjeta){
		return this.tarjetaService.findById(tarjeta);
	}
	
	@RequestMapping(
		value = "/api/production/tarjeta",
		method = RequestMethod.POST)
	TarjetaModel saveOne(@RequestBody TarjetaModel tarjeta){
		return this.tarjetaService.save(tarjeta);
	}
	
	@RequestMapping(
		value = "/api/production/tarjeta", 
		params = {"page", "size"},
		method = RequestMethod.GET)
	Iterable<TarjetaModel> getAll(@RequestParam Integer page, @RequestParam Integer size){
		return this.tarjetaService.getAll(new PageRequest((page - 1), size));
	}
	
	@RequestMapping(
		value = "/api/production/tarjeta/{id}",
		method = RequestMethod.GET)
	TarjetaModel getById(@PathVariable Integer id){
		return this.tarjetaService.getById(id);
	}

}
