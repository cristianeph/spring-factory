package com.pl.controller;

import com.pl.model.SolicitudInsumoModel;
import com.pl.services.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class SolicitudInsumoController {
    @Autowired
    private SolicitudService solicitudService;

    @RequestMapping(
            value = "/api/production/solicitudinsumo",
            method = RequestMethod.POST
    )
    SolicitudInsumoModel save(@RequestBody SolicitudInsumoModel prueba) {
        return this.solicitudService.save(prueba);
    }

    @RequestMapping(
            value = "/api/production/solicitudinsumo/{id}",
            method = RequestMethod.GET
    )
    SolicitudInsumoModel get(@PathVariable Integer id) {
        return this.solicitudService.findById(id);
    }

    @RequestMapping(
            value = "/api/production/solicitudinsumo",
            params = {"page", "size"},
            method = RequestMethod.GET
    )
    Iterable<SolicitudInsumoModel> all(@RequestParam Integer page, @RequestParam Integer size) {
        return this.solicitudService.findAll(new PageRequest((page - 1), size));
    }
}
