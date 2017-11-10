package com.pl.controller;

import com.pl.model.PruebaFormulaModel;
import com.pl.services.PruebaFormulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class PruebaFormulaController {

    @Autowired
    private PruebaFormulaService pruebaFormulaService;

    @RequestMapping(
            value = "/api/produccion/pruebaformula",
            method = RequestMethod.POST
    )
    PruebaFormulaModel save(@RequestBody PruebaFormulaModel prueba) {
        return this.pruebaFormulaService.save(prueba);
    }

    @RequestMapping(
            value = "/api/produccion/pruebaformula/{id}",
            method = RequestMethod.GET
    )
    PruebaFormulaModel get(@PathVariable Integer id) {
        return this.pruebaFormulaService.findById(id);
    }

    @RequestMapping(
            value = "/api/produccion/pruebaformula",
            params = {"page", "size"},
            method = RequestMethod.GET
    )
    Iterable<PruebaFormulaModel> all(@RequestParam Integer page, @RequestParam Integer size) {
        return this.pruebaFormulaService.findAll(new PageRequest((page - 1), size));
    }
}
