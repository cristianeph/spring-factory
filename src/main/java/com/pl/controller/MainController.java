package com.pl.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/masters/home")
    public String mastersHome() {
        return "masters/home";
    }

    @RequestMapping("/security/rol/list")
    public String securityRolList() {
        return "security/listRol";
    }

    @RequestMapping("/security/rol/form")
    public String securityRolForm() {
        return "security/formRol";
    }

    @RequestMapping("/security/usuario/list")
    public String securityUsuarioList() {
        return "security/listUsuario";
    }

    @RequestMapping("/security/usuario/form")
    public String securityUsuarioForm() {
        return "security/formUsuario";
    }

    @RequestMapping("/production/home")
    public String productionHome() {
        return "production/home";
    }

    @RequestMapping("/production/plan/validate/list")
    public String productionValPlanList() {
        return "production/listValidatePlan";
    }

    @RequestMapping("/production/plan/validate/form")
    public String productionValPlanForm() {
        return "production/formValidatePlan";
    }

    @RequestMapping("/production/plan/list")
    public String productionPlanList() {
        return "production/listPlan";
    }

    @RequestMapping("/production/plan/form")
    public String productionPlanForm() {
        return "production/formPlan";
    }

    @RequestMapping("/production/merma/list")
    public String productionMermaList() {
        return "production/listMerma";
    }

    @RequestMapping("/production/merma/form")
    public String productionMermaForm() {
        return "production/formMerma";
    }

    @RequestMapping("/production/pruebaproduccion/list")
    public String productionPruebaproduccionList() {
        return "production/listPruebaproduccion";
    }

    @RequestMapping("/production/pruebaproduccion/form")
    public String productionPruebaproduccionForm() {
        return "production/formPruebaproduccion";
    }

    @RequestMapping("/production/parteproduccion/list")
    public String productionParteproduccionList() {
        return "production/listParteproduccion";
    }

    @RequestMapping("/production/parteproduccion/form")
    public String productionParteproduccionForm() {
        return "production/formParteproduccion";
    }

    @RequestMapping("/production/validartrabajo/list")
    public String productionTrabajoList() {
        return "production/listValidartrabajo";
    }

    @RequestMapping("/production/validartrabajo/form")
    public String productionTrabajoForm() {
        return "production/formValidartrabajo";
    }

    @RequestMapping("/production/ordentrabajo/form")
    public String productionOrdenTrabajoForm() {
        return "production/formOrdentrabajo";
    }

    @RequestMapping("/production/ordentrabajo/list")
    public String productionOrdenTrabajoList() {
        return "production/listOrdentrabajo";
    }

    @RequestMapping("/production/movimientoalmacen/form")
    public String productionMovimientoAlmacenForm() {
        return "production/formMovimiento";
    }

    @RequestMapping("/production/movimientoalmacen/list")
    public String productionMovimientoAlmacenList() {
        return "production/listMovimiento";
    }

    @RequestMapping("/production/formula/list")
    public String productionFormulaList() {
        return "production/listFormula";
    }

    @RequestMapping("/production/formula/form")
    public String productionFormulaForm() {
        return "production/formFormula";
    }

    @RequestMapping("/production/actividad/form")
    public String productionActividadForm() {
        return "production/formActividad";
    }

    @RequestMapping("/production/actividad/list")
    public String productionActividadList() {
        return "production/listActividad";
    }

    @RequestMapping("/production/pruebaformula/form")
    public String productionPruebaFormulaForm() {
        return "production/formPruebaformula";
    }

    @RequestMapping("/production/pruebaformula/list")
    public String productionPruebaFormulaList() {
        return "production/listPruebaformula";
    }

    @RequestMapping("/production/solicitudformula/form")
    public String productionSolicitudFormulaForm() {
        return "production/formSolicitudformula";
    }

    @RequestMapping("/production/solicitudformula/list")
    public String productionSolicitudFormulaList() {
        return "production/listSolicitudformula";
    }

    @RequestMapping("/production/producto/form")
    public String productionProductForm() {
        return "production/formProducto";
    }

    @RequestMapping("/production/producto/list")
    public String productionProductList() {
        return "production/listProducto";
    }

    @RequestMapping("/production/maquina/form")
    public String productionMachineForm() {
        return "production/formMaquina";
    }

    @RequestMapping("/production/maquina/list")
    public String productionMachineList() {
        return "production/listMaquina";
    }

    @RequestMapping("/production/cliente/form")
    public String productionCustomerForm() {
        return "production/formCliente";
    }

    @RequestMapping("/production/cliente/list")
    public String productionCustomerList() {
        return "production/listCliente";
    }

    @RequestMapping("/production/insumo/form")
    public String productionInsumoForm() {
        return "production/formInsumo";
    }

    @RequestMapping("/production/insumo/list")
    public String productionInsumoList() {
        return "production/listInsumo";
    }

    @RequestMapping("/production/solicitudinsumo/list")
    public String productionSolicitudList() {
        return "production/listSolicitudinsumo";
    }

    @RequestMapping("/production/solicitudinsumo/form")
    public String productionSolicitudForm() {
        return "production/formSolicitudinsumo";
    }

    @RequestMapping("/production/avancepedido/list")
    public String productionAvanceList() {
        return "production/listAvancepedido";
    }

    @RequestMapping("/production/muestra/list")
    public String productionMuestraList() {
        return "production/listMuestra";
    }

    @RequestMapping("/production/muestra/form")
    public String productionMuestraForm() {
        return "production/formMuestra";
    }

    @RequestMapping("/production/pedido/list")
    public String productionPedidoList() {
        return "production/listPedido";
    }

    @RequestMapping("/production/pedido/form")
    public String productionPedidoForm() {
        return "production/formPedido";
    }

    @RequestMapping("/production/costo/list")
    public String productionCostoList() {
        return "production/listCosto";
    }

    @RequestMapping("/production/indicador/list")
    public String productionIndicadorList() {
        return "production/listIndicador";
    }

    @RequestMapping("/production/periodo/send/list")
    public String productionSendPeriodoList() {
        return "production/listSendCosto";
    }

    @RequestMapping("/production/periodo/send/form")
    public String productionSendPeriodoForm() {
        return "production/formSendCosto";
    }
}
