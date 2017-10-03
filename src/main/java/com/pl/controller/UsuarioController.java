package com.pl.controller;

import com.pl.model.UsuarioModel;
import com.pl.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(
            value = "/api/security/usuario",
            method = RequestMethod.POST)
    UsuarioModel saveOne(@RequestBody UsuarioModel usuario){
        return this.usuarioService.save(usuario);
    }

    @RequestMapping(
            value = "/api/security/usuario",
            params = {"page", "size"},
            method = RequestMethod.GET)
    Iterable<UsuarioModel> getAll(@RequestParam Integer page, @RequestParam Integer size){
        return this.usuarioService.getAll(new PageRequest((page - 1), size));
    }

    @RequestMapping(
            value = "/api/security/usuario/{id}",
            method = RequestMethod.GET)
    UsuarioModel getById(@PathVariable Integer id){
        return this.usuarioService.getById(id);
    }
}
