package com.pl.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

public class UsuarioDetails extends UsuarioModel implements UserDetails{

	private static final long serialVersionUID = 1L;
	private List<String> userRoles;

	public UsuarioDetails(UsuarioModel usuario, List<String> userRoles){
		
		super(usuario);
		this.userRoles = userRoles;
		
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		String roles = StringUtils.collectionToCommaDelimitedString(userRoles);
		return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return super.getUser();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
