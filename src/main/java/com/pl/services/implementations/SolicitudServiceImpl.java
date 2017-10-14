package com.pl.services.implementations;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.pl.services.SolicitudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.pl.model.SolicitudModel;
import com.pl.repository.SolicitudRepository;

@Component("solicitudService")
public class SolicitudServiceImpl implements SolicitudService {

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

	@Override
	public Page<SolicitudModel> findAll(PageRequest page) {
		return this.solicitudRepository.findAll(page);
	}

	@Override
	public SolicitudModel findById(Integer id) {
		return this.solicitudRepository.findById(id);
	}

}
