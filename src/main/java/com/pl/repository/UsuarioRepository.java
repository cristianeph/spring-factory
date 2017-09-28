package com.pl.repository;

import com.pl.model.UsuarioModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

public interface UsuarioRepository extends Repository<UsuarioModel, Long>{
    UsuarioModel save(UsuarioModel usuario);
    UsuarioModel findById(Integer id);
    UsuarioModel findByUser(String user);
    Page<UsuarioModel> findAll(Pageable page);
}
