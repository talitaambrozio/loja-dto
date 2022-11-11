package com.loja3.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.loja3.model.Perfil;
import com.loja3.model.Usuario;
import com.loja3.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private UsuarioRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado."));
		return new User(usuario.getEmail(), usuario.getSenha(), mapPerfisToAuthorities(usuario.getPerfis()));
	}

	private Collection<GrantedAuthority> mapPerfisToAuthorities(List<Perfil> perfis){
		return perfis.stream().map(perfil -> new SimpleGrantedAuthority(perfil.getNome())).collect(Collectors.toList());
	}
}
