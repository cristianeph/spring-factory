package com.pl.controller;

import com.pl.model.MuestraModel;
import com.pl.services.MuestraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class MuestraController {

    @Autowired
    private MuestraService muestraService;

    @RequestMapping(
            value = "/api/production/muestra",
            method = RequestMethod.POST
    )
    MuestraModel save(@RequestBody MuestraModel muestra) {
        return this.muestraService.save(muestra);
    }

    @RequestMapping(
            value = "/api/production/muestra/{id}",
            method = RequestMethod.GET
    )
    MuestraModel get(@PathVariable Integer id) {
        return this.muestraService.findById(id);
    }

    @RequestMapping(
            value = "/api/production/muestra",
            params = {"page", "size"},
            method = RequestMethod.GET
    )
    Iterable<MuestraModel> all(@RequestParam Integer page, @RequestParam Integer size) {
        return this.muestraService.findAll(new PageRequest((page - 1), size));
    }

}
