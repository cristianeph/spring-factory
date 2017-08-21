package com.pl.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.pl.model.TrabajoModel;

public interface TrabajoRepository extends Repository<TrabajoModel, Long>{
	
	TrabajoModel save(TrabajoModel trabajo);
	TrabajoModel deleteById(Integer id);
	TrabajoModel findById(Integer id);
	Page<TrabajoModel> findAll(Pageable page);
	
	@Query("SELECT t FROM TrabajoModel t INNER JOIN t.plan p WHERE p.id = :id")
	Page<TrabajoModel> findByPlanId(Pageable page, @Param("id") Integer id);
	
	@Query("SELECT t FROM TrabajoModel t INNER JOIN t.plan p WHERE p.fecha BETWEEN :start AND :end ORDER BY p.id DESC")
	Page<TrabajoModel> findAllBetweenPlanFecha(Pageable page, @Param("start") Date start, @Param("end") Date end);
	
	@Query("SELECT t FROM TrabajoModel t INNER JOIN t.plan p WHERE t.merma > 0 AND p.fecha BETWEEN :start AND :end ORDER BY p.id DESC")
	Page<TrabajoModel> findMermaAllBetweenPlanFecha(Pageable page, @Param("start") Date start, @Param("end") Date end);

}
