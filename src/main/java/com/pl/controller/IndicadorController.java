package com.pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pl.model.IndicadorAvanceModel;
import com.pl.model.IndicadorIncidenciaModel;
import com.pl.model.IndicadorMermaModel;
import com.pl.model.IndicadorProductividadModel;
import com.pl.model.IndicadorRechazoModel;
import com.pl.model.PedidoModel;
import com.pl.model.PedidoPlanTrabajoModel;
import com.pl.model.PlanModel;
import com.pl.model.TrabajoModel;
import com.pl.services.PedidoService;
import com.pl.services.PlanService;
import com.pl.services.TrabajoService;

@RestController
public class IndicadorController {
	
	@Autowired
	private PlanService planService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private TrabajoService trabajoService;
	
	@RequestMapping("/production/indicador/get/avance")
	IndicadorAvanceModel get(@RequestBody IndicadorAvanceModel indicador){
		
		Iterable<PedidoModel> pedidoModels = this.pedidoService.findAllBetweenFecha(indicador.getInicio(), indicador.getFin());
		
		for (PedidoModel pedidoModel : pedidoModels) {
			
			PlanModel planModel = this.planService.findByPedidoId(pedidoModel.getId());
			TrabajoModel trabajoModel = null;
			
			if(planModel != null){
				trabajoModel = this.trabajoService.findByPlanId(planModel.getId());
			}
			
			PedidoPlanTrabajoModel pedidoPlanModel = new PedidoPlanTrabajoModel();
			pedidoPlanModel.setPedido(pedidoModel);
			pedidoPlanModel.setPlan(planModel);
			pedidoPlanModel.setTrabajo(trabajoModel);
			
			indicador.getPedidoPlanes().add(pedidoPlanModel);
			
		}
		
		return indicador;
		
	}

	@RequestMapping("/production/indicador/get/productividad")
	IndicadorProductividadModel get(@RequestBody IndicadorProductividadModel indicador){
		
		Iterable<PlanModel> planModels = this.planService.findAllBetweenFecha(indicador.getInicio(), indicador.getFin());
		
		indicador.setPlanes(planModels);
		
		return indicador;
		
	}

	@RequestMapping("/production/indicador/get/incidencia")
	IndicadorIncidenciaModel get(@RequestBody IndicadorIncidenciaModel indicador){
		
		Iterable<TrabajoModel> trabajoModels = this.trabajoService.findAllBetweenPlanFecha(indicador.getInicio(), indicador.getFin());
		
		indicador.setTrabajos(trabajoModels);
		
		return indicador;
		
	}

	@RequestMapping("/production/indicador/get/merma")
	IndicadorMermaModel get(@RequestBody IndicadorMermaModel indicador){
		
		Iterable<TrabajoModel> trabajoModels = this.trabajoService.findMermaAllBetweenPlanFecha(indicador.getInicio(), indicador.getFin());
		
		indicador.setTrabajos(trabajoModels);
		
		return indicador;
		
	}

	@RequestMapping("/production/indicador/get/rechazo")
	IndicadorRechazoModel get(@RequestBody IndicadorRechazoModel indicador){
		
		Iterable<PlanModel> planModels = this.planService.findEstadoAllBetweenFecha("Rechazado", indicador.getInicio(), indicador.getFin());
		
		indicador.setPlanes(planModels);
		
		return indicador;
		
	}

}
