package com.pl.services;

import com.pl.model.SolicitudModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface SolicitudService {

	SolicitudModel save(SolicitudModel solicitud);
	Page<SolicitudModel> findAll(PageRequest page);
	SolicitudModel findById(Integer id);

}
