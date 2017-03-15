package com.pl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.pl.model.InsumoModel;

public interface InsumoRepository extends Repository<InsumoModel, Long>{

	InsumoModel findById(Integer id);
	Iterable<InsumoModel> findAll(Pageable page);
	Page<InsumoModel> findByRelacion(Pageable page, Integer relacion);

}
