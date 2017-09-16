package com.pl.repository;

import com.pl.model.MuestraModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface MuestraRepository extends Repository<MuestraModel, Long>{
    MuestraModel save(MuestraModel muestra);
    void deleteById(Integer id);
    MuestraModel findById(Integer id);
    Page<MuestraModel> findAll(Pageable page);
}
