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
        /*movimiento.set*/
        this.movimientoRepository.save(movimiento);

        /*MovimientoDetalleModel detalle = new MovimientoDetalleModel();
        detalle.setInsumo(movimiento.getMovimientoDetalle().getInsumo());
        detalle.setSolicitud(null);
        detalle.setMovimiento(null);
        detalle.setCantidad(movimiento.getCantidad());
        detalle.setTipo("TEST");
        detalle = this.movimientoDetalleRepository.save(detalle);
        System.out.println("El id del detalle es => " + detalle.getId());
        System.out.println("El id del detalle movimiento es => " + detalle.getMovimiento());
        System.out.println("El id del detalle solicitud es => " + detalle.getSolicitud());
        System.out.println("El id del detalle insumo es => " + detalle.getInsumo());
        movimiento.setMovimientoDetalle(detalle);
        return this.movimientoRepository.save(movimiento);*/

        /*System.out.println("WTF?");
        MovimientoDetalleModel movimientoDetalle = new MovimientoDetalleModel();
        if (movimiento.getId() == 0) {
            System.out.println("Este movimiento NO existía");
            movimientoDetalle.setInsumo(movimiento.getMovimientoDetalle().getInsumo());
            movimientoDetalle.setMovimiento(null);
            movimientoDetalle.setCantidad(movimiento.getCantidad());
            movimientoDetalle = this.movimientoDetalleRepository.save(movimientoDetalle);
            movimientoDetalle.setMovimiento(movimiento);
            movimiento.setMovimientoDetalle(movimientoDetalle);
            movimiento = this.movimientoRepository.save(movimiento);
            movimientoDetalle = this.movimientoDetalleRepository.save(movimientoDetalle);
        } else {
            System.out.println("Este movimiento SI existía");
            movimientoDetalle = this.movimientoDetalleRepository.findByInsumoIdAndMovimientoId(
                    movimiento.getMovimientoDetalle().getInsumo().getId(),
                    movimiento.getId()
            );
            if (movimientoDetalle != null) {
                movimiento.setMovimientoDetalle(movimientoDetalle);
            }
        }
        return this.movimientoRepository.save(movimiento);*/
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
