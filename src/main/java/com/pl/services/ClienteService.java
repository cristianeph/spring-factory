package com.pl.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.pl.model.ClienteModel;

public interface ClienteService {

	ClienteModel save(ClienteModel cliente);
	ClienteModel deleteById(ClienteModel cliente);
	ClienteModel findById(ClienteModel cliente);
	Page<ClienteModel> findAll(ClienteModel cliente);
	Page<ClienteModel> getAll(PageRequest page);
	ClienteModel getById(Integer id);

}
