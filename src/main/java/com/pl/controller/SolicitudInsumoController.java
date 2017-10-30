package com.pl.controller;

import com.pl.model.InsumoModel;
import com.pl.model.SolicitudInsumoModel;
import com.pl.services.InsumoService;
import com.pl.services.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class SolicitudInsumoController {
    @Autowired
    private SolicitudService solicitudService;
    @Autowired
    private InsumoService insumoService;

    @RequestMapping(
            value = "/api/production/solicitudinsumo",
            method = RequestMethod.POST
    )
    SolicitudInsumoModel save(@RequestBody SolicitudInsumoModel prueba) {
        return this.solicitudService.save(prueba);
    }

    @RequestMapping(
            value = "/api/production/solicitudinsumo",
            params = {"page", "size"},
            method = RequestMethod.GET
    )
    Iterable<SolicitudInsumoModel> getAll(@RequestParam Integer page, @RequestParam Integer size) {
        return this.solicitudService.findAll(new PageRequest((page - 1), size));
    }

    @RequestMapping(
            value = "/api/production/solicitudinsumo/{id}",
            method = RequestMethod.GET
    )
    SolicitudInsumoModel getById(@PathVariable Integer id) {
        return this.solicitudService.findById(id);
    }

    @RequestMapping(
            value = "/api/production/solicitudinsumo/parameters",
            method = RequestMethod.GET)
    Iterable<InsumoModel> getAllParameters() {
        return this.insumoService.getAsParameters(new PageRequest(0, Integer.MAX_VALUE));
    }
}
