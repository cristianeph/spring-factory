package com.pl.controller;

import com.pl.model.MuestraPruebaModel;
import com.pl.model.SolicitudFormulaModel;
import com.pl.services.MuestraService;
import com.pl.services.PruebaFormulaService;
import com.pl.services.SolicitudFormulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class SolicitudFormulaController {

    @Autowired
    private SolicitudFormulaService solicitudFormulaService;
    @Autowired
    private MuestraService muestraService;
    @Autowired
    private PruebaFormulaService pruebaFormulaService;

    @RequestMapping(
            value = "/api/production/solicitudformula",
            method = RequestMethod.POST
    )
    SolicitudFormulaModel save(@RequestBody SolicitudFormulaModel solicitud) {
        return this.solicitudFormulaService.save(solicitud);
    }

    @RequestMapping(
            value = "/api/production/solicitudformula/{id}",
            method = RequestMethod.GET
    )
    SolicitudFormulaModel get(@PathVariable Integer id) {
        return this.solicitudFormulaService.findById(id);
    }

    @RequestMapping(
            value = "/api/production/solicitudformula",
            params = {"page", "size"},
            method = RequestMethod.GET
    )
    Iterable<SolicitudFormulaModel> all(@RequestParam Integer page, @RequestParam Integer size) {
        return this.solicitudFormulaService.findAll(new PageRequest((page - 1), size));
    }

    @RequestMapping(
            value = "/api/production/solicitudformula/parameters",
            method = RequestMethod.GET
    )
    MuestraPruebaModel getParameters() {
        MuestraPruebaModel muestraPrueba = new MuestraPruebaModel();
        muestraPrueba.setMuestras(this.muestraService.findAll(new PageRequest((0), Integer.MAX_VALUE)));
        muestraPrueba.setPruebas(this.pruebaFormulaService.findAll(new PageRequest((0), Integer.MAX_VALUE)));
        return muestraPrueba;
    }

}
