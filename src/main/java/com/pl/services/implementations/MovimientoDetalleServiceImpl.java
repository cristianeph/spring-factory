package com.pl.services.implementations;

import com.pl.model.MovimientoDetalleModel;
import com.pl.repository.MovimientoDetalleRepository;
import com.pl.services.MovimientoDetalleService;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component("movimientoDetalleService")
public class MovimientoDetalleServiceImpl  implements MovimientoDetalleService {
    @PersistenceContext
    private EntityManager entityManager;
    private final MovimientoDetalleRepository movimientoDetalleRepository;

    public MovimientoDetalleServiceImpl(MovimientoDetalleRepository movimientoDetalleRepository) {
        this.movimientoDetalleRepository = movimientoDetalleRepository;
    }

    @Override
    public MovimientoDetalleModel save(MovimientoDetalleModel movimientoDetalle) {
        return this.movimientoDetalleRepository.save(movimientoDetalle);
    }
}
