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

import com.pl.model.PedidoModel;
import com.pl.model.PersonalModel;
import com.pl.model.PlanInsumoModel;
import com.pl.model.PlanModel;
import com.pl.model.ProducidoModel;
import com.pl.model.SolicitudModel;
import com.pl.services.PedidoService;
import com.pl.services.PersonalService;
import com.pl.services.PlanService;
import com.pl.services.SolicitudService;

@RestController
@EnableAutoConfiguration
public class PlanController {
	
	@Autowired
	private PlanService planService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PersonalService personalService;

	@Autowired
	private SolicitudService solicitudService;
	
	@RequestMapping("/production/plan/action/save")
	PlanModel save(@RequestBody PlanModel plan){
		
		PedidoModel pedidoEncontrado = null;
		System.out.println("se recibe: " + plan.getPedido().getId());
		if(plan.getPedido().getId() != null){
			
			pedidoEncontrado = pedidoService.findById(plan.getPedido());
			System.out.println("obteniendo pedido: " + pedidoEncontrado.getCodigo());
			plan.setPedido(null);
			
		}
		
		System.out.println(plan.getId());
		
		for (ProducidoModel detalle: plan.getPlanProducidos()) {
			
			System.out.println("id del detalle: " + detalle.getId());
			
			PersonalModel personalEncontrado = personalService.findById(detalle.getPersonal());
			System.out.println("se encontro el personal actualizado: " + personalEncontrado.getId() + " : " + personalEncontrado.getApellidos());
			detalle.setPersonal(personalEncontrado);
			
			detalle.setPlan(plan);
			
		}
		
		//initial collection
		
		Collection<ProducidoModel> producidoModelInitial = new ArrayList<ProducidoModel>();
		producidoModelInitial.addAll(plan.getPlanProducidos());
		
		//actual collection
		
		plan.clearProducidoModel();
		
		PlanModel planActualizada = this.planService.save(plan);
		//System.out.println("obteniendo pedido: " + pedidoEncontrado.getCodigo());
		System.out.println("se resetearon los detalles: " + planActualizada.getPlanProducidos().size());
		
		planActualizada.getPlanProducidos().clear();
		planActualizada.getPlanProducidos().addAll(producidoModelInitial);
		
		System.out.println("tamano guardado de detalles: " + producidoModelInitial.size());
		
		System.out.println("tamano actual de detalles: " + planActualizada.getPlanProducidos().size());
		
		for (ProducidoModel detalle : planActualizada.getPlanProducidos()) {
			
			detalle.setPlan(planActualizada);
			
		}
		
		planActualizada.setPedido(pedidoEncontrado);
		
		PlanModel planProcesada = this.planService.save(planActualizada);
		
		System.out.println("se actualizaron los detalles" + planProcesada.getPlanProducidos().size());
		
		return planProcesada;
		
	}
	
	@RequestMapping("/production/plan/action/state")
	PlanModel setState(@RequestBody PlanModel plan){
		
		PlanModel planEncontrado = this.planService.findById(plan);
		planEncontrado.setEstado("Validado");
		this.planService.save(planEncontrado);
		System.out.println("se cambia el estado del plan");
		
		return planEncontrado;
		
	}
	
	@RequestMapping("/production/plan/action/request/resources")
	PlanModel requestResources(@RequestBody PlanInsumoModel planInsumo){
		System.out.println("se recibio: " + planInsumo.getPlan().getId());
		System.out.println("se recibio: " + planInsumo.getInsumo().getId());
		PlanModel planEncontrado = this.planService.findById(planInsumo.getPlan());
		planEncontrado.setEstado("Pendiente de solicitud");
		this.planService.save(planEncontrado);
		System.out.println("se cambia el estado del plan");
		SolicitudModel solicitud = new SolicitudModel();
		solicitud.setIdInsumo(planInsumo.getInsumo().getId());
		solicitud.setIdPlan(planInsumo.getPlan().getId());
		this.solicitudService.save(solicitud);
		System.out.println("se crea la solicitud");
		return planEncontrado;
	}
	
	@RequestMapping("/production/plan/action/find")
	PlanModel findById(@RequestBody PlanModel plan){
		System.out.println("se recibe el parametro: " + plan.getCodigo());
		PlanModel planModel = this.planService.findById(plan);
		return planModel;
	}
	
	@RequestMapping("/production/plan/action/delete")
	PlanModel deleteById(@RequestBody PlanModel plan){
		PlanModel planModel = this.planService.deleteById(plan);
		return planModel;
	}
	
	@RequestMapping("/production/plan/action/all")
	Iterable<PlanModel> findAll(@RequestBody PlanModel plan){
		Iterable<PlanModel> planModels = this.planService.findAll(plan);
		return planModels;
	}
	
	@RequestMapping(
		value = "/api/production/plan",
		method = RequestMethod.POST)
	PlanModel getAll(@RequestBody PlanModel plan){
		return this.planService.save(plan);
	}
	
	@RequestMapping(
		value = "/api/production/plan", 
		params = {"page", "size"},
		method = RequestMethod.GET)
	Iterable<PlanModel> getAll(@RequestParam Integer page, @RequestParam Integer size){
		return this.planService.getAll(new PageRequest((page - 1), size));
	}
	
	@RequestMapping(
		value = "/api/production/plan/{id}",
		method = RequestMethod.GET)
	PlanModel getById(@PathVariable Integer id){
		return this.planService.getById(id);
	}

}
