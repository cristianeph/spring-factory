package com.pl.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableAutoConfiguration
public class MainController {

	@RequestMapping("/")
	public String index(){
		return "index";
	}
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	@RequestMapping("/masters/home")
	public String mastersHome(){
		return "masters/home";
	}
	@RequestMapping("/production/home")
	public String productionHome(){
		return "production/home";
	}
	@RequestMapping("/production/pedido/list")
	public String productionPedidoList(){
		return "production/listPedido";
	}
	@RequestMapping("/production/pedido/form")
	public String productionPedidoForm(){
		return "production/formPedido";
	}
	@RequestMapping("/production/plan/validate/list")
	public String productionValPlanList(){
		return "production/listValidatePlan";
	}
	@RequestMapping("/production/plan/validate/form")
	public String productionValPlanForm(){
		return "production/formValidatePlan";
	}
	@RequestMapping("/production/plan/list")
	public String productionPlanList(){
		return "production/listPlan";
	}
	@RequestMapping("/production/plan/form")
	public String productionPlanForm(){
		return "production/formPlan";
	}
	@RequestMapping("/production/card/list")
	public String productionCardList(){
		return "production/listTarjeta";
	}
	@RequestMapping("/production/card/form")
	public String productionCardForm(){
		return "production/formTarjeta";
	}
	@RequestMapping("/production/formula/list")
	public String productionFormulaList(){
		return "production/listFormula";
	}
	@RequestMapping("/production/formula/form")
	public String productionFormulaForm(){
		return "production/formFormula";
	}
	@RequestMapping("/production/producto/list")
	public String productionProductList(){
		return "production/listProducto";
	}
	@RequestMapping("/production/producto/form")
	public String productionProductForm(){
		return "production/formProducto";
	}
	
	@RequestMapping("/production/costo/list")
	public String productionCostoList(){
		return "production/listCosto";
	}
	
	@RequestMapping("/production/indicador/list")
	public String productionIndicadorList(){
		return "production/listIndicador";
	}
	
	@RequestMapping("/production/periodo/send/list")
	public String productionSendPeriodoList(){
		return "production/listSendCosto";
	}
	@RequestMapping("/production/periodo/send/form")
	public String productionSendPeriodoForm(){
		return "production/formSendCosto";
	}
}
