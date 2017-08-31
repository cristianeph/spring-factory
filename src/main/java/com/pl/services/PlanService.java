package com.pl.services;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.pl.model.PlanModel;

public interface PlanService {

	PlanModel save(PlanModel plan);
	PlanModel deleteById(PlanModel plan);
	PlanModel findById(PlanModel plan);
	Page<PlanModel> findAll(PlanModel plan);
	Page<PlanModel> findAllBetweenFecha(Date start, Date end);
	Page<PlanModel> findEstadoAllBetweenFecha(String estado, Date start, Date end);
	
	PlanModel findByPedidoId(Integer id);
	
	Page<PlanModel> getAll(PageRequest page);
	PlanModel getById(Integer id);

}
