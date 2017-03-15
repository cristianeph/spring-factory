package com.pl.services;

import org.springframework.data.domain.Page;

import com.pl.model.PeriodoModel;

public interface PeriodoService {

	PeriodoModel save(PeriodoModel periodo);
	PeriodoModel deleteById(PeriodoModel periodo);
	PeriodoModel findById(PeriodoModel periodo);
	Page<PeriodoModel> findAll(PeriodoModel periodo);

}
