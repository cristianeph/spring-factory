package com.pl.services;

import org.springframework.data.domain.Page;

import com.pl.model.InsumoModel;

public interface InsumoService {
	
	Page<InsumoModel> findByRelacion(Integer relacion);
	InsumoModel findById(InsumoModel insumo);
	Page<InsumoModel> findAll(InsumoModel insumo);

}
