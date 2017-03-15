package com.pl.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import com.pl.model.SolicitudModel;
import com.pl.repository.SolicitudRepository;

@Component("solicitudService")
public class SolicitudServiceImpl implements SolicitudService{

	@PersistenceContext
	private EntityManager entityManager;
	
	private final SolicitudRepository solicitudRepository;
	
	public SolicitudServiceImpl(SolicitudRepository solicitudRepository){
		
		this.solicitudRepository = solicitudRepository;
		
	}

	@Override
	public SolicitudModel save(SolicitudModel solicitud) {
		
		return this.solicitudRepository.save(solicitud);
		
	}

}
