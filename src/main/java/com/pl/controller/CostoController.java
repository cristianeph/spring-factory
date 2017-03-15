package com.pl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pl.model.CostoModel;
import com.pl.model.PlanModel;
import com.pl.model.PlanTarjetaModel;
import com.pl.model.TrabajoModel;
import com.pl.services.PlanService;
import com.pl.services.TrabajoService;

@RestController
@EnableAutoConfiguration
public class CostoController {
	
	@Autowired
	private PlanService planService;
	
	@Autowired
	private TrabajoService trabajoService;
	
	@RequestMapping("/production/cost/getCosts")
	CostoModel getCosts(@RequestBody CostoModel costo){
		
		Iterable<PlanModel> planModels = this.planService.findAllBetweenFecha(costo.getInicio(), costo.getFin());
		
		for (PlanModel planModel : planModels) {
			
			TrabajoModel trabajoModel = this.trabajoService.findByPlanId(planModel.getId());
			
			PlanTarjetaModel planTarjetaModel = new PlanTarjetaModel();
			planTarjetaModel.setPlan(planModel);
			planTarjetaModel.setTrabajo(trabajoModel);
			
			costo.getPlanTarjetas().add(planTarjetaModel);
			
		}
		
		return costo;
		
	}

}
