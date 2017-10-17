package com.pl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.pl.model.ParteProduccionModel;

public interface ParteProduccionRepository extends Repository<ParteProduccionModel, Long>{
	
	ParteProduccionModel save(ParteProduccionModel tarjeta);
	void deleteById(Integer id);
	ParteProduccionModel findById(Integer id);
	Page<ParteProduccionModel> findAll(Pageable page);

}
