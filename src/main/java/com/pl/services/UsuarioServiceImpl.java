package com.pl.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pl.model.UsuarioDetails;
import com.pl.model.UsuarioModel;

@Service("usuarioServiceImpl")
public class UsuarioServiceImpl implements UserDetailsService{
	
	private final UsuarioRepository usuarioRepository;
	
	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
		
		System.out.println("inicializando...");
		this.usuarioRepository = usuarioRepository;
		
	}

	@Override
	public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
		
		System.out.println("accediendo para validar...");
		
		UsuarioModel usuarioModel = usuarioRepository.findByUser(user);
		
		if(usuarioModel == null){

			System.out.println("El usuario es invalido");
			throw new UsernameNotFoundException("El usuario " + user + " no esta registrado");
			
		}else{
			
			System.out.println("El usuario es valido");
			List<String> userRoles = Arrays.asList(usuarioModel.getRol().toString());
			return new UsuarioDetails(usuarioModel, userRoles);

		}
		
	}
	
}
