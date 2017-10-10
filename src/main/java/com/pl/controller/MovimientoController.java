package com.pl.controller;

import com.pl.model.KardexModel;
import com.pl.model.MovimientoAlmacenModel;
import com.pl.services.KardexService;
import com.pl.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;
    @Autowired
    private KardexService kardexService;

    @RequestMapping(
            value = "/api/production/movimiento",
            method = RequestMethod.POST)
    MovimientoAlmacenModel saveOne(@RequestBody MovimientoAlmacenModel movimiento) {
        return this.movimientoService.save(movimiento);
    }

    @RequestMapping(
            value = "/api/production/movimiento",
            params = {"page", "size"},
            method = RequestMethod.GET)
    Iterable<MovimientoAlmacenModel> getAll(@RequestParam Integer page, @RequestParam Integer size) {
        return this.movimientoService.getAll(new PageRequest((page - 1), size));
    }

    @RequestMapping(
            value = "/api/production/movimiento/parameters",
            method = RequestMethod.GET)
    Iterable<KardexModel> getAll() {
        return this.kardexService.getAll(new PageRequest(0, Integer.MAX_VALUE));
    }

    @RequestMapping(
            value = "/api/production/movimiento/{id}",
            method = RequestMethod.GET)
    MovimientoAlmacenModel getById(@PathVariable Integer id) {
        return this.movimientoService.getById(id);
    }
}
