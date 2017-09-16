package com.pl.services;

import com.pl.model.MuestraModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface MuestraService {

    MuestraModel save(MuestraModel muestra);
    Page<MuestraModel> findAll(PageRequest page);
    MuestraModel findById(Integer id);

}
