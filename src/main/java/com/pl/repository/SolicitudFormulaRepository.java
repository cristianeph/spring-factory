package com.pl.repository;

import com.pl.model.SolicitudFormulaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface SolicitudFormulaRepository extends Repository<SolicitudFormulaModel, Long>{
    SolicitudFormulaModel save(SolicitudFormulaModel solicitud);
    void deleteById(Integer id);
    SolicitudFormulaModel findById(Integer id);
    Page<SolicitudFormulaModel> findAll(Pageable page);

}
