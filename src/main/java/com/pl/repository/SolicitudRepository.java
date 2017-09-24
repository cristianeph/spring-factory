package com.pl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.pl.model.SolicitudModel;

public interface SolicitudRepository extends Repository<SolicitudModel, Long>{
	SolicitudModel save(SolicitudModel solicitud);
	SolicitudModel findById(Integer id);
	Page<SolicitudModel> findAll(Pageable page);
}
