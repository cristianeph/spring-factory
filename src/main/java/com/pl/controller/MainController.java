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

    /*@RequestMapping("/security/rol/list")
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
    }*/

    /* @Todo REGISTRAR RESULTADO DE LA PRUEBA - DEBE SER UN ESTADO CON COMENTARIOS */
    /* @Todo ANTERIORMENTE ERA PARTE DE PRUEBAS */
    /*@RequestMapping("/produccion/pruebaproduccion/list")
    public String productionPruebaproduccionList() {
        return "produccion/listPruebaproduccion";
    }

    @RequestMapping("/produccion/pruebaproduccion/form")
    public String productionPruebaproduccionForm() {
        return "produccion/formPruebaproduccion";
    }*/

    /* @Todo ANTES ERA PLAN DE PRODUCCION*/
    /* @Todo ACTUALIZAR TARJETA DE HORARIO*/
    /*@RequestMapping("/produccion/parteproduccion/list")
    public String productionParteproduccionList() {
        return "produccion/listParteproduccion";
    }

    @RequestMapping("/produccion/parteproduccion/form")
    public String productionParteproduccionForm() {
        return "produccion/formParteproduccion";
    }*/

    /* @Todo DEBERIA SER VALIDAR ORDEN DE PRODUCCION*/
    /* @RequestMapping("/produccion/validartrabajo/list")
    public String productionTrabajoList() {
        return "produccion/listValidartrabajo";
    }

    @RequestMapping("/produccion/validartrabajo/form")
    public String productionTrabajoForm() {
        return "produccion/formValidartrabajo";
    }*/

    /* @Todo AHORA DEBE SER ORDEN DE PRODUCCION*/
    /* @RequestMapping("/produccion/ordentrabajo/form")
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

    @RequestMapping("/produccion/maquina/form")
    public String productionMachineForm() {
        return "produccion/formMaquina";
    }

    @RequestMapping("/produccion/maquina/list")
    public String productionMachineList() {
        return "produccion/listMaquina";
    }

    @RequestMapping("/produccion/insumo/form")
    public String productionInsumoForm() {
        return "produccion/formInsumo";
    }

    @RequestMapping("/produccion/insumo/list")
    public String productionInsumoList() {
        return "produccion/listInsumo";
    }*/

    /* @Todo ESTA SOLICITUD DE INSUMO DEBE TENER UN ESTADO, EL CUAL DEBE SER ACTUALIZADO COMO "CONFORME" EN OTRA INSTANCIA */
    /* @RequestMapping("/produccion/solicitudinsumo/list")
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
    }*/

    /* GENERAR COSTOS */
    @RequestMapping("/produccion/costo/list")
    public String productionCostoList() {
        return "produccion/listCosto";
    }

    /* @Todo FALTAN INDICADORES DE ALMACEN */
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

    /* Añadiendo nuevo juego de homesites para el proyecto*/

    @RequestMapping("modulos/")
    public String modulos() {
        return "modulos/home";
    }

    @RequestMapping("modulos/home")
    public String modulosHome() {
        return "modulos/home";
    }

    @RequestMapping("modulos/almacen")
    public String produccionAlmacen() {
        return "modulos/almacen/home";
    }

    @RequestMapping("modulos/contabilidad")
    public String produccionContabilidad() {
        return "modulos/contabilidad/home";
    }

    @RequestMapping("modulos/operario")
    public String produccionOperario() {
        return "modulos/operario/home";
    }

    @RequestMapping("modulos/produccion")
    public String produccionProduccion() {
        return "modulos/produccion/home";
    }

    /*QUIMICO*/

    @RequestMapping("modulos/quimico")
    public String produccionQuimico() {
        return "modulos/quimico/home";
    }

    @RequestMapping("modulos/quimico/formula/form")
    public String quimicoFormulaForm() {
        return "modulos/quimico/formFormula";
    }

    @RequestMapping("modulos/quimico/formula/list")
    public String quimicoFormulaList() {
        return "modulos/quimico/listFormula";
    }

    @RequestMapping("modulos/quimico/muestra/form")
    public String quimicoMuestraForm() {
        return "modulos/quimico/formMuestra";
    }

    @RequestMapping("modulos/quimico/muestra/list")
    public String quimicoMuestraList() {
        return "modulos/quimico/listMuestra";
    }

    @RequestMapping("modulos/quimico/pruebaproduccion/form")
    public String quimicoPruebaproduccionForm() {
        return "modulos/quimico/formPruebaproduccion";
    }

    @RequestMapping("modulos/quimico/pruebaproduccion/list")
    public String quimicoPruebaproduccionList() {
        return "modulos/quimico/listPruebaproduccion";
    }

    @RequestMapping("modulos/quimico/solicitudformula/form")
    public String quimicoSolicitudformulaForm() {
        return "modulos/quimico/formSolicitudformula";
    }

    @RequestMapping("modulos/quimico/solicitudformula/list")
    public String quimicoSolicitudformulaList() {
        return "modulos/quimico/listSolicitudformula";
    }

    @RequestMapping("modulos/quimico/solicitudinsumo/form")
    public String quimicoSolicitudinsumoForm() {
        return "modulos/quimico/formSolicitudinsumo";
    }

    @RequestMapping("modulos/quimico/solicitudinsumo/list")
    public String quimicoSolicitudinsumoList() {
        return "modulos/quimico/listSolicitudinsumo";
    }

    @RequestMapping("modulos/quimico/insumo/form")
    public String quimicoInsumoForm() {
        return "modulos/insumo/formSolicitudinsumo";
    }

    @RequestMapping("modulos/quimico/insumo/list")
    public String quimicoInsumoList() {
        return "modulos/insumo/listSolicitudinsumo";
    }

    /*VENDEDOR*/

    @RequestMapping("modulos/vendedor")
    public String produccionVentas() {
        return "modulos/vendedor/home";
    }

    @RequestMapping("vendedor/pedido/form")
    public String vendedorPedidoForm() {
        return "modulos/vendedor/formPedido";
    }

    @RequestMapping("vendedor/pedido/list")
    public String vendedorPedidoList() {
        return "modulos/vendedor/listPedido";
    }

    @RequestMapping("vendedor/producto/form")
    public String vendedorProductForm() {
        return "modulos/vendedor/formProducto";
    }

    @RequestMapping("vendedor/producto/list")
    public String vendedorProductList() {
        return "modulos/vendedor/listProducto";
    }

    @RequestMapping("vendedor/cliente/form")
    public String vendedorCustomerForm() {
        return "modulos/vendedor/formCliente";
    }

    @RequestMapping("vendedor/cliente/list")
    public String vendedorCustomerList() {
        return "modulos/vendedor/listCliente";
    }
}
