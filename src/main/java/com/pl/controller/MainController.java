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

    /*@RequestMapping("modulos/almacen")
    public String produccionAlmacen() {
        return "modulos/almacen/home";
    }

    @RequestMapping("modulos/contabilidad")
    public String produccionContabilidad() {
        return "modulos/contabilidad/home";
    }

    @RequestMapping("modulos/operario")
    public String produccionMainOperario() {
        return "modulos/operario/home";
    }

    @RequestMapping("modulos/produccion")
    public String produccionProduccion() {
        return "modulos/produccion/home";
    }*/

    /*CONTADOR*/

    @RequestMapping("modulos/contador")
    public String produccionContador() {
        return "modulos/contador/home";
    }

    @RequestMapping("modulos/contador/enviarcostos/form")
    public String contadorEnviarcostosForm() {
        return "modulos/contador/formSendCosto";
    }

    @RequestMapping("modulos/contador/enviarcostos/list")
    public String contadorEnviarcostosList() {
        return "modulos/contador/listSendCosto";
    }

    /*@RequestMapping("modulos/contador/costos/form")
    public String contadorCostosForm() {
        return "modulos/contador/formCosto";
    }*/

    @RequestMapping("modulos/contador/costos/list")
    public String contadorCostosList() {
        return "modulos/contador/listCosto";
    }

    /*@RequestMapping("modulos/contador/avancepedido/form")
    public String contadorAvancePedidoForm() {
        return "modulos/contador/formAvancepedido";
    }*/

    @RequestMapping("modulos/contador/avancepedido/list")
    public String contadorAvancePedidoList() {
        return "modulos/contador/listAvancepedido";
    }



    /*ALMACENERO*/

    @RequestMapping("modulos/almacenero")
    public String produccionAlmacenero() {
        return "modulos/almacenero/home";
    }

    @RequestMapping("modulos/almacenero/movimiento/form")
    public String almaceneroMovimientoForm() {
        return "modulos/almacenero/formMovimiento";
    }

    @RequestMapping("modulos/almacenero/movimiento/list")
    public String almaceneroMovimientoList() {
        return "modulos/almacenero/listMovimiento";
    }

    /*@RequestMapping("modulos/almacenero/solicitudinsumo/form")
    public String almaceneroSolicitudinsumoForm() {
        return "modulos/almacenero/formSolicitud";
    }*/

    @RequestMapping("modulos/almacenero/solicitudinsumo/list")
    public String almaceneroSolicitudinsumoList() {
        return "modulos/almacenero/listSolicitud";
    }

    /*OPERARIO*/

    @RequestMapping("modulos/operario")
    public String produccionOperario() {
        return "modulos/operario/home";
    }

    @RequestMapping("modulos/operario/parteproduccion/form")
    public String operarioParteproduccionForm() {
        return "modulos/operario/formParteproduccion";
    }

    @RequestMapping("modulos/operario/parteproduccion/list")
    public String operarioParteproduccionList() {
        return "modulos/operario/listParteproduccion";
    }

    /*JEFE PRODUCCION*/

    @RequestMapping("modulos/jefeproduccion")
    public String produccionJefeproduccion() {
        return "modulos/jefeproduccion/home";
    }

    @RequestMapping("modulos/jefeproduccion/actividad/form")
    public String jefeproduccionActividadForm() {
        return "modulos/jefeproduccion/formActividad";
    }

    @RequestMapping("modulos/jefeproduccion/actividad/list")
    public String jefeproduccionActividadList() {
        return "modulos/jefeproduccion/listActividad";
    }

    @RequestMapping("modulos/jefeproduccion/maquina/form")
    public String jefeproduccionMaquinaForm() {
        return "modulos/jefeproduccion/formMaquina";
    }

    @RequestMapping("modulos/jefeproduccion/maquina/list")
    public String jefeproduccionMaquinaList() {
        return "modulos/jefeproduccion/listMaquina";
    }

    @RequestMapping("modulos/jefeproduccion/ordentrabajo/form")
    public String jefeproduccionOrdentrabajoForm() {
        return "modulos/jefeproduccion/formOrdentrabajo";
    }

    @RequestMapping("modulos/jefeproduccion/ordentrabajo/list")
    public String jefeproduccionOrdentrabajoList() {
        return "modulos/jefeproduccion/listOrdentrabajo";
    }

    @RequestMapping("modulos/jefeproduccion/plan/form")
    public String jefeproduccionPlanForm() {
        return "modulos/jefeproduccion/formPlan";
    }

    @RequestMapping("modulos/jefeproduccion/plan/list")
    public String jefeproduccionPlanList() {
        return "modulos/jefeproduccion/listPlan";
    }

    @RequestMapping("modulos/jefeproduccion/validateplan/form")
    public String jefeproduccionValidateplanForm() {
        return "modulos/validateplan/formValidateplan";
    }

    @RequestMapping("modulos/jefeproduccion/validateplan/list")
    public String jefeproduccionValidateplanList() {
        return "modulos/validateplan/listValidateplan";
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
        return "modulos/quimico/formInsumo";
    }

    @RequestMapping("modulos/quimico/insumo/list")
    public String quimicoInsumoList() {
        return "modulos/quimico/listInsumo";
    }

    /*VENDEDOR*/

    @RequestMapping("modulos/vendedor")
    public String produccionVentas() {
        return "modulos/vendedor/home";
    }

    @RequestMapping("modulos/vendedor/pedido/form")
    public String vendedorPedidoForm() {
        return "modulos/vendedor/formPedido";
    }

    @RequestMapping("modulos/vendedor/pedido/list")
    public String vendedorPedidoList() {
        return "modulos/vendedor/listPedido";
    }

    @RequestMapping("modulos/vendedor/producto/form")
    public String vendedorProductForm() {
        return "modulos/vendedor/formProducto";
    }

    @RequestMapping("modulos/vendedor/producto/list")
    public String vendedorProductList() {
        return "modulos/vendedor/listProducto";
    }

    @RequestMapping("modulos/vendedor/cliente/form")
    public String vendedorCustomerForm() {
        return "modulos/vendedor/formCliente";
    }

    @RequestMapping("modulos/vendedor/cliente/list")
    public String vendedorCustomerList() {
        return "modulos/vendedor/listCliente";
    }
}
