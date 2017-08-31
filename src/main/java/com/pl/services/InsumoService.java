package com.pl.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.pl.model.InsumoModel;

public interface InsumoService {
	
	InsumoModel save(InsumoModel insumo);
	Page<InsumoModel> findByRelacion(Integer relacion);
	InsumoModel findById(Integer id);
	Page<InsumoModel> findAll(InsumoModel insumo);
	Page<InsumoModel> getAll(PageRequest page);
	InsumoModel getById(Integer id);

}
