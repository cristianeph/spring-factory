package com.pl.repository;

import com.pl.model.PruebaFormulaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface PruebaFormulaRepository extends Repository<PruebaFormulaModel, Long> {
    PruebaFormulaModel save(PruebaFormulaModel pruebaformula);
    void deleteById(Integer id);
    PruebaFormulaModel findById(Integer id);
    Page<PruebaFormulaModel> findAll(Pageable page);
}
