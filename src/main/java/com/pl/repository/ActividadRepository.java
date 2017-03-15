package com.pl.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.pl.model.ActividadModel;

public interface ActividadRepository extends Repository<ActividadModel, Long>{
	
	ActividadModel save(ActividadModel actividad);
	ActividadModel deleteById(Integer id);
	ActividadModel findById(Integer id);
	Iterable<ActividadModel> findAll(Pageable page);

}
