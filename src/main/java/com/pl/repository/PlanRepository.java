package com.pl.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.pl.model.PlanModel;

public interface PlanRepository extends Repository<PlanModel, Long>{
	
	PlanModel save(PlanModel plan);
	PlanModel deleteById(Integer id);
	PlanModel findById(Integer id);
	Iterable<PlanModel> findAll(Pageable page);

	@Query("SELECT p FROM PlanModel p WHERE p.fecha BETWEEN :start AND :end ORDER BY p.id DESC")
	Page<PlanModel> findAllBetweenFecha(Pageable page, @Param("start") Date start, @Param("end") Date end);

	@Query("SELECT p FROM PlanModel p WHERE p.estado = :estado AND p.fecha BETWEEN :start AND :end ORDER BY p.id DESC")
	Page<PlanModel> findEstadoAllBetweenFecha(Pageable page, @Param("estado") String estado, @Param("start") Date start, @Param("end") Date end);
	
	@Query("SELECT p FROM PlanModel p INNER JOIN p.pedido e WHERE e.id = :id")
	Page<PlanModel> findByPedidoId(Pageable page, @Param("id") Integer id);

}
