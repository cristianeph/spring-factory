package com.pl.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.pl.model.SolicitudInsumoModel;

public interface SolicitudRepository extends Repository<SolicitudInsumoModel, Long>{
	SolicitudInsumoModel save(SolicitudInsumoModel solicitud);
	SolicitudInsumoModel findById(Integer id);
	Page<SolicitudInsumoModel> findAll(Pageable page);
}
