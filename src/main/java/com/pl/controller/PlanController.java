package com.pl.controller;

import com.pl.model.*;
import com.pl.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanController {

    @Autowired
    private PlanService planService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private SolicitudService solicitudService;

    @Autowired
    private MovimientoDetalleService movimientoDetalleService;

    @RequestMapping("/production/plan/action/save")
    PlanModel save(@RequestBody PlanModel plan) {

        PedidoModel pedidoEncontrado = pedidoService.findById(plan.getPedido());
        plan.setPedido(pedidoEncontrado);
        plan = this.planService.save(plan);

        return plan;

    }

    @RequestMapping("/production/plan/action/state")
    PlanModel setState(@RequestBody PlanModel plan) {

        PlanModel planEncontrado = this.planService.findById(plan);
        planEncontrado.setEstado("Validado");
        this.planService.save(planEncontrado);
        System.out.println("se cambia el estado del plan");

        return planEncontrado;

    }

    @RequestMapping("/production/plan/action/request/resources")
    PlanModel requestResources(@RequestBody PlanInsumoModel planInsumo) {
        PlanModel planEncontrado = this.planService.findById(planInsumo.getPlan());
        planEncontrado.setEstado("Pendiente de solicitud");
        this.planService.save(planEncontrado);
        System.out.println("se cambia el estado del plan");
        SolicitudInsumoModel solicitud = new SolicitudInsumoModel();
        solicitud.setIdPlan(planInsumo.getPlan().getId());
        /*solicitud.setCantidad(planInsumo.getPlan().get);*/
        solicitud = this.solicitudService.save(solicitud);
        MovimientoDetalleModel movimiento = new MovimientoDetalleModel();
        movimiento.setInsumo(planInsumo.getInsumo());
        movimiento.setSolicitud(solicitud);
        movimiento = this.movimientoDetalleService.save(movimiento);
        System.out.println("se crea la solicitud: " + solicitud.getId());
        return planEncontrado;
    }

    @RequestMapping("/production/plan/action/find")
    PlanModel findById(@RequestBody PlanModel plan) {
        System.out.println("se recibe el parametro: " + plan.getCodigo());
        PlanModel planModel = this.planService.findById(plan);
        return planModel;
    }

    @RequestMapping("/production/plan/action/delete")
    PlanModel deleteById(@RequestBody PlanModel plan) {
        PlanModel planModel = this.planService.deleteById(plan);
        return planModel;
    }

    @RequestMapping("/production/plan/action/all")
    Iterable<PlanModel> findAll(@RequestBody PlanModel plan) {
        Iterable<PlanModel> planModels = this.planService.findAll(plan);
        return planModels;
    }

    @RequestMapping(
            value = "/api/production/plan",
            method = RequestMethod.POST)
    PlanModel getAll(@RequestBody PlanModel plan) {
        return this.planService.save(plan);
    }

    @RequestMapping(
            value = "/api/production/plan",
            params = {"page", "size"},
            method = RequestMethod.GET)
    Iterable<PlanModel> getAll(@RequestParam Integer page, @RequestParam Integer size) {
        return this.planService.getAll(new PageRequest((page - 1), size));
    }

    @RequestMapping(
            value = "/api/production/plan/{id}",
            method = RequestMethod.GET)
    PlanModel getById(@PathVariable Integer id) {
        return this.planService.getById(id);
    }

}
