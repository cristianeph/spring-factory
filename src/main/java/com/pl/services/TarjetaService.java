package com.pl.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.pl.model.TarjetaModel;

public interface TarjetaService {
	
	TarjetaModel save(TarjetaModel tarjeta);
	void deleteById(TarjetaModel tarjeta);
	TarjetaModel findById(TarjetaModel tarjeta);
	Page<TarjetaModel> findAll(TarjetaModel tarjeta);
	
	Page<TarjetaModel> getAll(PageRequest page);
	TarjetaModel getById(Integer id);

}
