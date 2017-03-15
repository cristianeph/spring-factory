package com.pl.services;

import org.springframework.data.domain.Page;

import com.pl.model.ActividadModel;

public interface ActividadService {

	ActividadModel save(ActividadModel actividad);
	ActividadModel deleteById(ActividadModel actividad);
	ActividadModel findById(ActividadModel actividad);
	Page<ActividadModel> findAll(ActividadModel actividad);

}
