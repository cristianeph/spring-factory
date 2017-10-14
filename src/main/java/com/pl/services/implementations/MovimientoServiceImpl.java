package com.pl.services.implementations;

import com.pl.model.MovimientoAlmacenModel;
import com.pl.repository.KardexRepository;
import com.pl.repository.MovimientoRepository;
import com.pl.services.MovimientoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component("movimientoService")
public class MovimientoServiceImpl implements MovimientoService {
    @PersistenceContext
    private EntityManager entityManager;
    private final MovimientoRepository movimientoRepository;
    private final KardexRepository kardexRepository;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository, KardexRepository kardexRepository) {
        this.movimientoRepository = movimientoRepository;
        this.kardexRepository = kardexRepository;
    }

    @Override
    public MovimientoAlmacenModel save(MovimientoAlmacenModel movimiento) {
        return this.movimientoRepository.save(movimiento);
    }

    @Override
    public MovimientoAlmacenModel getById(Integer id) {
        return this.movimientoRepository.findById(id);
    }

    @Override
    public Page<MovimientoAlmacenModel> getAll(PageRequest page) {
        return this.movimientoRepository.findAll(page);
    }
}
