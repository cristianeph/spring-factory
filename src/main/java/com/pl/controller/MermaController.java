package com.pl.controller;

import com.pl.model.MermaModel;
import com.pl.model.MermaTrabajoModel;
import com.pl.model.OrdenTrabajoModel;
import com.pl.services.MermaService;
import com.pl.services.TrabajoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class MermaController {
    @Autowired
    private MermaService mermaService;
    @Autowired
    private TrabajoService trabajoService;

    @RequestMapping(
            value = "/api/produccion/merma",
            method = RequestMethod.POST)
    MermaModel saveOne(@RequestBody MermaModel merma){
        return this.mermaService.save(merma);
    }

    @RequestMapping(
            value = "/api/produccion/merma/compositesave",
            method = RequestMethod.POST)
    MermaTrabajoModel saveComposite(@RequestBody MermaTrabajoModel mermaTrabajo){
        System.out.println("Se recibe");
        System.out.println(mermaTrabajo.getMerma().getCantidad());
        System.out.println(mermaTrabajo.getTrabajo().getId());
        return this.mermaService.saveComposite(mermaTrabajo);
    }

    @RequestMapping(
            value = "/api/produccion/merma",
            params = {"page", "size"},
            method = RequestMethod.GET)
    Iterable<MermaModel> getAll(@RequestParam Integer page, @RequestParam Integer size){
        return this.mermaService.getAll(new PageRequest((page - 1), size));
    }

    @RequestMapping(
            value = "/api/produccion/merma/{id}",
            method = RequestMethod.GET)
    MermaModel getById(@PathVariable Integer id){
        return this.mermaService.getById(id);
    }

    @RequestMapping(
            value = "/api/produccion/merma/parameters",
            method = RequestMethod.GET)
    Iterable<OrdenTrabajoModel> getParameters(){
        return this.trabajoService.getAll(new PageRequest((0), Integer.MAX_VALUE));
    }
}
