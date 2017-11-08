package com.pl.services.implementations;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.pl.model.MovimientoDetalleModel;
import com.pl.repository.MovimientoDetalleRepository;
import com.pl.services.SolicitudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.pl.model.SolicitudInsumoModel;
import com.pl.repository.SolicitudRepository;

@Component("solicitudService")
public class SolicitudInsumoServiceImpl implements SolicitudService {

    @PersistenceContext
    private EntityManager entityManager;
    private final SolicitudRepository solicitudRepository;
    private final MovimientoDetalleRepository movimientoDetalleRepository;

    public SolicitudInsumoServiceImpl(SolicitudRepository solicitudRepository, MovimientoDetalleRepository movimientoDetalleRepository) {
        this.solicitudRepository = solicitudRepository;
        this.movimientoDetalleRepository = movimientoDetalleRepository;
    }

    @Override
    public SolicitudInsumoModel save(SolicitudInsumoModel solicitud) {
        System.out.println("el id detalle es => " + solicitud.getMovimientoDetalle().getId());
        if (solicitud.getMovimientoDetalle().getId() == 0) {
            System.out.println("nuevo registro");
            MovimientoDetalleModel movimientoDetalle = new MovimientoDetalleModel(
                    solicitud.getMovimientoDetalle().getInsumo(),
                    new SolicitudInsumoModel(
                            solicitud.getCodigo(),
                            solicitud.getFecha(),
                            solicitud.getIdPlan(),
                            solicitud.getCantidad(),
                            solicitud.getEstado()
                    ),
                    solicitud.getMovimientoDetalle().getMovimiento(),
                    "PROGRAMADO",
                    solicitud.getCantidad()
            );
            movimientoDetalle = this.movimientoDetalleRepository.save(movimientoDetalle);
            SolicitudInsumoModel respuesta = this.solicitudRepository.findById(movimientoDetalle.getSolicitud().getId());
            respuesta.setMovimientoDetalle(movimientoDetalle);
            return respuesta;
        } else {
            System.out.println("ya existia registro");
            solicitud.getMovimientoDetalle().setSolicitud(solicitud);
            return this.solicitudRepository.save(solicitud);
        }
    }

    @Override
    public Page<SolicitudInsumoModel> findAll(PageRequest page) {
        return this.solicitudRepository.findAll(page);
    }

    @Override
    public SolicitudInsumoModel findById(Integer id) {
        return this.solicitudRepository.findById(id);
    }

}
