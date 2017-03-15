package com.pl.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.pl.model.FormulaModel;

public interface FormulaRepository extends Repository<FormulaModel, Long>{
	
	FormulaModel save(FormulaModel tarjeta);
	void deleteById(Integer id);
	FormulaModel findById(Integer id);
	Iterable<FormulaModel> findAll(Pageable page);

}
