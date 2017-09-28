package com.pl.repository;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.pl.model.OrdenTrabajoModel;

public interface TrabajoRepository extends Repository<OrdenTrabajoModel, Long>{
	
	OrdenTrabajoModel save(OrdenTrabajoModel trabajo);
	OrdenTrabajoModel deleteById(Integer id);
	OrdenTrabajoModel findById(Integer id);
	Page<OrdenTrabajoModel> findAll(Pageable page);
	Page<OrdenTrabajoModel> findByCantidadOrHoras(Pageable page, BigDecimal cantidad, Integer horas);
	
	@Query("SELECT t FROM OrdenTrabajoModel t INNER JOIN t.plan p WHERE p.id = :id")
	Page<OrdenTrabajoModel> findByPlanId(Pageable page, @Param("id") Integer id);
	
	@Query("SELECT t FROM OrdenTrabajoModel t INNER JOIN t.plan p WHERE p.fecha BETWEEN :start AND :end ORDER BY p.id DESC")
	Page<OrdenTrabajoModel> findAllBetweenPlanFecha(Pageable page, @Param("start") Date start, @Param("end") Date end);
	
	@Query("SELECT t FROM OrdenTrabajoModel t INNER JOIN t.plan p WHERE t.merma > 0 AND p.fecha BETWEEN :start AND :end ORDER BY p.id DESC")
	Page<OrdenTrabajoModel> findMermaAllBetweenPlanFecha(Pageable page, @Param("start") Date start, @Param("end") Date end);

}
