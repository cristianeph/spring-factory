package com.pl.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.pl.model.TarjetaModel;

public interface TarjetaRepository extends Repository<TarjetaModel, Long>{
	
	TarjetaModel save(TarjetaModel tarjeta);
	void deleteById(Integer id);
	TarjetaModel findById(Integer id);
	Iterable<TarjetaModel> findAll(Pageable page);

}
