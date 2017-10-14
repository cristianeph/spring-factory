package com.pl.services.implementations;

import java.util.Arrays;
import java.util.List;

import com.pl.repository.UsuarioRepository;
import com.pl.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pl.model.UsuarioDetails;
import com.pl.model.UsuarioModel;

/*@Service("usuarioServiceImpl")*/
@Service("usuarioService")
public class UsuarioServiceImpl implements UserDetailsService, UsuarioService {

    @Autowired
	private UsuarioRepository usuarioRepository;
	
	/*public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
		this.usuarioRepository = usuarioRepository;
	}*/

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

	@Override
	public UsuarioModel save(UsuarioModel usuario) {
		return this.usuarioRepository.save(usuario);
	}

	@Override
	public UsuarioModel getById(Integer id) {
		return this.usuarioRepository.findById(id);
	}

	@Override
	public UsuarioModel getByUser(String user) {
		return this.usuarioRepository.findByUser(user);
	}

	@Override
	public Page<UsuarioModel> getAll(Pageable page) {
		return this.usuarioRepository.findAll(page);
	}
}
