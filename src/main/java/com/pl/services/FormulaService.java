package com.pl.services;

import org.springframework.data.domain.Page;

import com.pl.model.FormulaModel;

public interface FormulaService {
	
	FormulaModel save(FormulaModel formula);
	void deleteById(FormulaModel formula);
	FormulaModel findById(FormulaModel formula);
	Page<FormulaModel> findAll(FormulaModel formula);

}
