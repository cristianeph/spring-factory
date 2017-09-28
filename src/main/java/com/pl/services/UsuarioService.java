package com.pl.services;

import com.pl.model.UsuarioModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {
    UsuarioModel save(UsuarioModel usuario);
    UsuarioModel getById(Integer id);
    UsuarioModel getByUser(String user);
    Page<UsuarioModel> getAll(Pageable page);
}
