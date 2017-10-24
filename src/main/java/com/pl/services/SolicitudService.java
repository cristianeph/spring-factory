package com.pl.services;

import com.pl.model.SolicitudInsumoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface SolicitudService {

	SolicitudInsumoModel save(SolicitudInsumoModel solicitud);
	Page<SolicitudInsumoModel> findAll(PageRequest page);
	SolicitudInsumoModel findById(Integer id);

}
