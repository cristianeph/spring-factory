package com.pl.repository;

import com.pl.model.MovimientoAlmacenModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface MovimientoRepository extends Repository<MovimientoAlmacenModel, Long> {
    MovimientoAlmacenModel save(MovimientoAlmacenModel movimiento);
    MovimientoAlmacenModel deleteById(Integer id);
    MovimientoAlmacenModel findById(Integer id);
    Page<MovimientoAlmacenModel> findAll(Pageable page);
}
