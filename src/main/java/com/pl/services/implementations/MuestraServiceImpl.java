package com.pl.services.implementations;

import com.pl.model.MuestraModel;
import com.pl.repository.MuestraRepository;
import com.pl.services.MuestraService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component("muestraService")
public class MuestraServiceImpl implements MuestraService {

    private final MuestraRepository muestraRepository;

    public MuestraServiceImpl(MuestraRepository muestraRepository) {
        this.muestraRepository = muestraRepository;
    }

    @Override
    public MuestraModel save(MuestraModel muestra) {
        return this.muestraRepository.save(muestra);
    }

    @Override
    public Page<MuestraModel> findAll(PageRequest page) {
        return this.muestraRepository.findAll(page);
    }

    @Override
    public MuestraModel findById(Integer id) {
        return this.muestraRepository.findById(id);
    }
}
