package com.pl.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.pl.model.ParteProduccionModel;

public interface TarjetaService {
	
	ParteProduccionModel save(ParteProduccionModel tarjeta);
	void deleteById(ParteProduccionModel tarjeta);
	ParteProduccionModel findById(ParteProduccionModel tarjeta);
	Page<ParteProduccionModel> findAll(ParteProduccionModel tarjeta);
	
	Page<ParteProduccionModel> getAll(PageRequest page);
	ParteProduccionModel getById(Integer id);

}
