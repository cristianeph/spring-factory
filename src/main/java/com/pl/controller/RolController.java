package com.pl.controller;

import com.pl.model.RolModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class RolController {
    @RequestMapping(
            value = "/api/security/usuario",
            method = RequestMethod.GET)
    List<RolModel> getAll(){
        return Arrays.asList(RolModel.values());
    }

}
