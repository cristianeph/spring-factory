package com.pl.services;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.pl.model.PedidoModel;

public interface PedidoService {

	PedidoModel save(PedidoModel pedido);
	PedidoModel deleteById(PedidoModel pedido);
	PedidoModel findById(PedidoModel pedido);
	Page<PedidoModel> findAll(PedidoModel pedido);
	Page<PedidoModel> getAll(PageRequest page);
	Page<PedidoModel> findAllBetweenFecha(Date start, Date end);

}
