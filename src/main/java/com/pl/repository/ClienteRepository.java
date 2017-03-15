package com.pl.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.pl.model.ClienteModel;

public interface ClienteRepository extends Repository<ClienteModel, Long>{
	
	ClienteModel save(ClienteModel cliente);
	ClienteModel deleteById(Integer id);
	ClienteModel findById(Integer id);
	Iterable<ClienteModel> findAll(Pageable page);

}
