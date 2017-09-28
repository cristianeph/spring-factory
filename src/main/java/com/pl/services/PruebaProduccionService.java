package com.pl.services;

import com.pl.model.PruebaProduccionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PruebaProduccionService {
    PruebaProduccionModel save(PruebaProduccionModel prueba);
    PruebaProduccionModel getById(Integer id);
    Page<PruebaProduccionModel> getAll(PageRequest page);
}
