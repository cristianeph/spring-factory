package com.pl.repository;

import org.springframework.data.repository.Repository;

import com.pl.model.SolicitudModel;

public interface SolicitudRepository extends Repository<SolicitudModel, Long>{
	
	SolicitudModel save(SolicitudModel solicitud);

}
