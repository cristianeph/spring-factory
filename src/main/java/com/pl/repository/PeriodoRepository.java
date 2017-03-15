package com.pl.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.pl.model.PeriodoModel;

public interface PeriodoRepository extends Repository<PeriodoModel, Long>{
	
	PeriodoModel save(PeriodoModel periodo);
	PeriodoModel deleteById(Integer id);
	PeriodoModel findById(Integer id);
	Iterable<PeriodoModel> findAll(Pageable page);

}
