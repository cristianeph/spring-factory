package com.pl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
	name="usuario"
)
public class UsuarioModel {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String user;
	private String password;
	private String email;
	@Column(name = "rol")
	@Enumerated(EnumType.STRING)
	private RolModel rol;
	
	public UsuarioModel(){
		
	}
	
	public UsuarioModel(UsuarioModel usuario) {
		super();
		this.id = usuario.id;
		this.user = usuario.user;
		this.email = usuario.email;
		this.password = usuario.password;
		this.rol = usuario.rol;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public RolModel getRol() {
		return rol;
	}

	public void setRol(RolModel rol) {
		this.rol = rol;
	}

}
