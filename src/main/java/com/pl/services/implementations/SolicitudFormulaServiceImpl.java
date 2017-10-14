package com.pl.services.implementations;

import com.pl.model.SolicitudFormulaModel;
import com.pl.repository.SolicitudFormulaRepository;
import com.pl.services.SolicitudFormulaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component("solicitudFormulaService")
public class SolicitudFormulaServiceImpl implements SolicitudFormulaService {

    private final SolicitudFormulaRepository solicitudFormulaRepository;

    public SolicitudFormulaServiceImpl(SolicitudFormulaRepository solicitudFormulaRepository) {
        this.solicitudFormulaRepository = solicitudFormulaRepository;
    }

    @Override
    public SolicitudFormulaModel save(SolicitudFormulaModel solicitud) {
        return this.solicitudFormulaRepository.save(solicitud);
    }

    @Override
    public Page<SolicitudFormulaModel> findAll(PageRequest page) {
        return this.solicitudFormulaRepository.findAll(page);
    }

    @Override
    public SolicitudFormulaModel findById(Integer id) {
        return this.solicitudFormulaRepository.findById(id);
    }
}
