package com.pl.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.pl.model.PedidoModel;

public interface PedidoRepository extends Repository<PedidoModel, Long>{
	
	PedidoModel save(PedidoModel plan);
	PedidoModel deleteById(Integer id);
	PedidoModel findById(Integer id);
	Page<PedidoModel> findAll(Pageable page);

	@Query("SELECT p FROM PedidoModel p WHERE p.fecha BETWEEN :start AND :end ORDER BY p.id DESC")
	Page<PedidoModel> findAllBetweenFecha(Pageable page, @Param("start") Date start, @Param("end") Date end);

}
