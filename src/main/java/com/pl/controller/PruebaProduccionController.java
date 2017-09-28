package com.pl.controller;

import com.pl.model.PruebaProduccionModel;
import com.pl.services.PruebaProduccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class PruebaProduccionController {
    @Autowired
    private PruebaProduccionService pruebaProduccionService;

    @RequestMapping(
            value = "/api/production/pruebaproduccion",
            method = RequestMethod.POST)
    PruebaProduccionModel saveOne(@RequestBody PruebaProduccionModel prueba){
        return this.pruebaProduccionService.save(prueba);
    }

    @RequestMapping(
            value = "/api/production/pruebaproduccion",
            params = {"page", "size"},
            method = RequestMethod.GET)
    Iterable<PruebaProduccionModel> getAll(@RequestParam Integer page, @RequestParam Integer size){
        return this.pruebaProduccionService.getAll(new PageRequest((page - 1), size));
    }

    @RequestMapping(
            value = "/api/production/pruebaproduccion/{id}",
            method = RequestMethod.GET)
    PruebaProduccionModel getById(@PathVariable Integer id){
        return this.pruebaProduccionService.getById(id);
    }
}
