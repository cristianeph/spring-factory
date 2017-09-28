package com.pl.services;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.pl.model.OrdenTrabajoModel;

public interface TrabajoService {

	OrdenTrabajoModel save(OrdenTrabajoModel trabajo);
	OrdenTrabajoModel deleteById(OrdenTrabajoModel trabajo);
	OrdenTrabajoModel findById(OrdenTrabajoModel trabajo);
	Page<OrdenTrabajoModel> findAll(OrdenTrabajoModel trabajo);
	Page<OrdenTrabajoModel> getAllInvalid(PageRequest page);
	Page<OrdenTrabajoModel> getAll(PageRequest page);
	OrdenTrabajoModel getById(Integer id);
	
	OrdenTrabajoModel findByPlanId(Integer id);
	Page<OrdenTrabajoModel> findAllBetweenPlanFecha(Date start, Date end);
	Page<OrdenTrabajoModel> findMermaAllBetweenPlanFecha(Date start, Date end);

}
