package com.pl.services;

import com.pl.model.PruebaProduccionModel;
import com.pl.repository.PruebaProduccionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component("pruebaProduccion")
public class PruebaProduccionServiceImpl implements PruebaProduccionService {
    @PersistenceContext
    private EntityManager entityManager;
    private final PruebaProduccionRepository pruebaProduccionRepository;
    public PruebaProduccionServiceImpl(PruebaProduccionRepository pruebaProduccionRepository) {
        this.pruebaProduccionRepository = pruebaProduccionRepository;
    }

    @Override
    public PruebaProduccionModel save(PruebaProduccionModel prueba) {
        return this.pruebaProduccionRepository.save(prueba);
    }

    @Override
    public PruebaProduccionModel getById(Integer id) {
        return this.pruebaProduccionRepository.findById(id);
    }

    @Override
    public Page<PruebaProduccionModel> getAll(PageRequest page) {
        return this.pruebaProduccionRepository.findAll(page);
    }
}
