package com.pl.services.implementations;

import com.pl.model.MovimientoAlmacenModel;
import com.pl.model.MovimientoDetalleModel;
import com.pl.repository.KardexRepository;
import com.pl.repository.MovimientoDetalleRepository;
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
    private final MovimientoDetalleRepository movimientoDetalleRepository;

    public MovimientoServiceImpl(MovimientoRepository movimientoRepository, KardexRepository kardexRepository, MovimientoDetalleRepository movimientoDetalleRepository) {
        this.movimientoRepository = movimientoRepository;
        this.kardexRepository = kardexRepository;
        this.movimientoDetalleRepository = movimientoDetalleRepository;
    }

    @Override
    public MovimientoAlmacenModel save(MovimientoAlmacenModel movimiento) {
        System.out.println("el id detalle es => " + movimiento.getMovimientoDetalle().getId());
        if (movimiento.getMovimientoDetalle().getId() == 0) {
            System.out.println("nuevo registro");
            MovimientoDetalleModel movimientoDetalle = new MovimientoDetalleModel(
                    movimiento.getMovimientoDetalle().getInsumo(),
                    movimiento.getMovimientoDetalle().getSolicitud(),
                    new MovimientoAlmacenModel(
                            movimiento.getFecha(),
                            movimiento.getTipo(),
                            movimiento.getCantidad()
                    ),
                    "SOLICITADO",
                    movimiento.getCantidad()
            );
            movimientoDetalle = this.movimientoDetalleRepository.save(movimientoDetalle);
            MovimientoAlmacenModel respuesta = this.movimientoRepository.findById(movimientoDetalle.getMovimiento().getId());
            respuesta.setMovimientoDetalle(movimientoDetalle);
            return respuesta;
        } else {
            System.out.println("ya existia registro");
            movimiento.getMovimientoDetalle().setMovimiento(movimiento);
            return this.movimientoRepository.save(movimiento);
        }
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
