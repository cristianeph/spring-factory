package com.pl.services;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.pl.model.TrabajoModel;

public interface TrabajoService {

	TrabajoModel save(TrabajoModel trabajo);
	TrabajoModel deleteById(TrabajoModel trabajo);
	TrabajoModel findById(TrabajoModel trabajo);
	Page<TrabajoModel> findAll(TrabajoModel trabajo);
	Page<TrabajoModel> getAll(PageRequest page);
	TrabajoModel getById(Integer id);
	
	TrabajoModel findByPlanId(Integer id);
	Page<TrabajoModel> findAllBetweenPlanFecha(Date start, Date end);
	Page<TrabajoModel> findMermaAllBetweenPlanFecha(Date start, Date end);

}
