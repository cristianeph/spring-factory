package com.pl.repository;

import com.pl.model.PruebaProduccionModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface PruebaProduccionRepository extends Repository<PruebaProduccionModel, Long>{
    PruebaProduccionModel save(PruebaProduccionModel prueba);
    PruebaProduccionModel findById(Integer id);
    Page<PruebaProduccionModel> findAll(Pageable page);
}
