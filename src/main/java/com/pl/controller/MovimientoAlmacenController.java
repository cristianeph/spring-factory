package com.pl.controller;

import com.pl.model.InsumoModel;
import com.pl.model.MovimientoAlmacenModel;
import com.pl.services.InsumoService;
import com.pl.services.KardexService;
import com.pl.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovimientoAlmacenController {
    @Autowired
    private MovimientoService movimientoService;
    @Autowired
    private InsumoService insumoService;

    @RequestMapping(
            value = "/api/production/movimientoalmacen",
            method = RequestMethod.POST
    )
    MovimientoAlmacenModel save(@RequestBody MovimientoAlmacenModel movimiento) {
        return this.movimientoService.save(movimiento);
    }

    @RequestMapping(
            value = "/api/production/movimientoalmacen",
            params = {"page", "size"},
            method = RequestMethod.GET
    )
    Iterable<MovimientoAlmacenModel> getAll(@RequestParam Integer page, @RequestParam Integer size) {
        return this.movimientoService.getAll(new PageRequest((page - 1), size));
    }

    @RequestMapping(
            value = "/api/production/movimientoalmacen/{id}",
            method = RequestMethod.GET
    )
    MovimientoAlmacenModel getById(@PathVariable Integer id) {
        return this.movimientoService.getById(id);
    }

    @RequestMapping(
            value = "/api/production/movimientoalmacen/parameters",
            method = RequestMethod.GET)
    Iterable<InsumoModel> getAllParameters() {
        return this.insumoService.getAsParameters(new PageRequest(0, Integer.MAX_VALUE));
    }
}
