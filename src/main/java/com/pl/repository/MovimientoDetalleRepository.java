package com.pl.repository;

import com.pl.model.MovimientoDetalleModel;
import org.springframework.data.repository.Repository;

public interface MovimientoDetalleRepository extends Repository<MovimientoDetalleModel, Long>{
    MovimientoDetalleModel save(MovimientoDetalleModel movimientoDetalle);
    MovimientoDetalleModel findById(Integer id);
    MovimientoDetalleModel findByInsumoIdAndMovimientoId(Integer insumo, Integer movimiento);
    MovimientoDetalleModel findByInsumoId(Integer id);
    MovimientoDetalleModel findBySolicitudId(Integer id);
    MovimientoDetalleModel findByMovimientoId(Integer id);
}
