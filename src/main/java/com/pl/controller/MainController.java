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

    @RequestMapping("/produccion/home")
    public String productionHome() {
        return "produccion/home";
    }

    @RequestMapping("/produccion/plan/validate/list")
    public String productionValPlanList() {
        return "produccion/listValidatePlan";
    }

    @RequestMapping("/produccion/plan/validate/form")
    public String productionValPlanForm() {
        return "produccion/formValidatePlan";
    }

    @RequestMapping("/produccion/plan/list")
    public String productionPlanList() {
        return "produccion/listPlan";
    }

    @RequestMapping("/produccion/plan/form")
    public String productionPlanForm() {
        return "produccion/formPlan";
    }

    @RequestMapping("/produccion/merma/list")
    public String productionMermaList() {
        return "produccion/listMerma";
    }

    @RequestMapping("/produccion/merma/form")
    public String productionMermaForm() {
        return "produccion/formMerma";
    }

    @RequestMapping("/produccion/pruebaproduccion/list")
    public String productionPruebaproduccionList() {
        return "produccion/listPruebaproduccion";
    }

    @RequestMapping("/produccion/pruebaproduccion/form")
    public String productionPruebaproduccionForm() {
        return "produccion/formPruebaproduccion";
    }

    @RequestMapping("/produccion/parteproduccion/list")
    public String productionParteproduccionList() {
        return "produccion/listParteproduccion";
    }

    @RequestMapping("/produccion/parteproduccion/form")
    public String productionParteproduccionForm() {
        return "produccion/formParteproduccion";
    }

    @RequestMapping("/produccion/validartrabajo/list")
    public String productionTrabajoList() {
        return "produccion/listValidartrabajo";
    }

    @RequestMapping("/produccion/validartrabajo/form")
    public String productionTrabajoForm() {
        return "produccion/formValidartrabajo";
    }

    @RequestMapping("/produccion/ordentrabajo/form")
    public String productionOrdenTrabajoForm() {
        return "produccion/formOrdentrabajo";
    }

    @RequestMapping("/produccion/ordentrabajo/list")
    public String productionOrdenTrabajoList() {
        return "produccion/listOrdentrabajo";
    }

    @RequestMapping("/produccion/movimientoalmacen/form")
    public String productionMovimientoAlmacenForm() {
        return "produccion/formMovimiento";
    }

    @RequestMapping("/produccion/movimientoalmacen/list")
    public String productionMovimientoAlmacenList() {
        return "produccion/listMovimiento";
    }

    @RequestMapping("/produccion/formula/list")
    public String productionFormulaList() {
        return "produccion/listFormula";
    }

    @RequestMapping("/produccion/formula/form")
    public String productionFormulaForm() {
        return "produccion/formFormula";
    }

    @RequestMapping("/produccion/actividad/form")
    public String productionActividadForm() {
        return "produccion/formActividad";
    }

    @RequestMapping("/produccion/actividad/list")
    public String productionActividadList() {
        return "produccion/listActividad";
    }

    @RequestMapping("/produccion/pruebaformula/form")
    public String productionPruebaFormulaForm() {
        return "produccion/formPruebaformula";
    }

    @RequestMapping("/produccion/pruebaformula/list")
    public String productionPruebaFormulaList() {
        return "produccion/listPruebaformula";
    }

    @RequestMapping("/produccion/solicitudformula/form")
    public String productionSolicitudFormulaForm() {
        return "produccion/formSolicitudformula";
    }

    @RequestMapping("/produccion/solicitudformula/list")
    public String productionSolicitudFormulaList() {
        return "produccion/listSolicitudformula";
    }

    @RequestMapping("/produccion/producto/form")
    public String productionProductForm() {
        return "produccion/formProducto";
    }

    @RequestMapping("/produccion/producto/list")
    public String productionProductList() {
        return "produccion/listProducto";
    }

    @RequestMapping("/produccion/maquina/form")
    public String productionMachineForm() {
        return "produccion/formMaquina";
    }

    @RequestMapping("/produccion/maquina/list")
    public String productionMachineList() {
        return "produccion/listMaquina";
    }

    @RequestMapping("/produccion/cliente/form")
    public String productionCustomerForm() {
        return "produccion/formCliente";
    }

    @RequestMapping("/produccion/cliente/list")
    public String productionCustomerList() {
        return "produccion/listCliente";
    }

    @RequestMapping("/produccion/insumo/form")
    public String productionInsumoForm() {
        return "produccion/formInsumo";
    }

    @RequestMapping("/produccion/insumo/list")
    public String productionInsumoList() {
        return "produccion/listInsumo";
    }

    @RequestMapping("/produccion/solicitudinsumo/list")
    public String productionSolicitudList() {
        return "produccion/listSolicitudinsumo";
    }

    @RequestMapping("/produccion/solicitudinsumo/form")
    public String productionSolicitudForm() {
        return "produccion/formSolicitudinsumo";
    }

    @RequestMapping("/produccion/avancepedido/list")
    public String productionAvanceList() {
        return "produccion/listAvancepedido";
    }

    @RequestMapping("/produccion/muestra/list")
    public String productionMuestraList() {
        return "produccion/listMuestra";
    }

    @RequestMapping("/produccion/muestra/form")
    public String productionMuestraForm() {
        return "produccion/formMuestra";
    }

    @RequestMapping("/produccion/pedido/list")
    public String productionPedidoList() {
        return "produccion/listPedido";
    }

    @RequestMapping("/produccion/pedido/form")
    public String productionPedidoForm() {
        return "produccion/formPedido";
    }

    @RequestMapping("/produccion/costo/list")
    public String productionCostoList() {
        return "produccion/listCosto";
    }

    @RequestMapping("/produccion/indicador/list")
    public String productionIndicadorList() {
        return "produccion/listIndicador";
    }

    @RequestMapping("/produccion/periodo/send/list")
    public String productionSendPeriodoList() {
        return "produccion/listSendCosto";
    }

    @RequestMapping("/produccion/periodo/send/form")
    public String productionSendPeriodoForm() {
        return "produccion/formSendCosto";
    }

    /*Añadiendo nuevo juego de
    homesites para el proyecto*/

    @RequestMapping("produccion/almacen")
    public String produccionAlmacen() {
        return "produccion/almacen/home";
    }

    @RequestMapping("produccion/contabilidad")
    public String produccionContabilidad() {
        return "produccion/contabilidad/home";
    }

    @RequestMapping("produccion/operario")
    public String produccionOperario() {
        return "produccion/operario/home";
    }

    @RequestMapping("produccion/produccion")
    public String produccionProduccion() {
        return "produccion/produccion/home";
    }

    @RequestMapping("produccion/quimico")
    public String produccionQuimico() {
        return "produccion/quimico/home";
    }

    @RequestMapping("produccion/ventas")
    public String produccionVentas() {
        return "produccion/ventas/home";
    }

}
