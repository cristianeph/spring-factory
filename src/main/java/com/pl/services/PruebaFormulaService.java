package com.pl.services;

import com.pl.model.PruebaFormulaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PruebaFormulaService {

    PruebaFormulaModel save(PruebaFormulaModel prueba);
    Page<PruebaFormulaModel> findAll(PageRequest page);
    PruebaFormulaModel findById(Integer id);
}
