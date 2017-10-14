package com.pl.services;

import com.pl.model.MovimientoAlmacenModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface MovimientoService {
    MovimientoAlmacenModel save(MovimientoAlmacenModel movimiento);
    MovimientoAlmacenModel getById(Integer id);
    Page<MovimientoAlmacenModel> getAll(PageRequest page);
}
