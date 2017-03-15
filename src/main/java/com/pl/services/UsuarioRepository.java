package com.pl.services;

import org.springframework.data.repository.Repository;

import com.pl.model.UsuarioModel;

public interface UsuarioRepository extends Repository<UsuarioModel, Long>{
	
	UsuarioModel findByUser(String user);

}
