package com.pl.services;

import com.pl.model.SolicitudFormulaModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface SolicitudFormulaService {
    SolicitudFormulaModel save(SolicitudFormulaModel solicitud);
    Page<SolicitudFormulaModel> findAll(PageRequest page);
    SolicitudFormulaModel findById(Integer id);
}
