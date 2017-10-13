package com.pl.services.implementations;

import com.pl.model.PruebaFormulaModel;
import com.pl.repository.PruebaFormulaRepository;
import com.pl.services.PruebaFormulaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component("pruebaFormulaService")
public class PruebaFormulaServiceImpl implements PruebaFormulaService {

    private final PruebaFormulaRepository pruebaFormulaRepository;

    public PruebaFormulaServiceImpl(PruebaFormulaRepository pruebaFormulaRepository) {
        this.pruebaFormulaRepository = pruebaFormulaRepository;
    }

    @Override
    public PruebaFormulaModel save(PruebaFormulaModel prueba) {
        return this.pruebaFormulaRepository.save(prueba);
    }

    @Override
    public Page<PruebaFormulaModel> findAll(PageRequest page) {
        return this.pruebaFormulaRepository.findAll(page);
    }

    @Override
    public PruebaFormulaModel findById(Integer id) {
        return this.pruebaFormulaRepository.findById(id);
    }
}
