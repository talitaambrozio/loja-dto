package com.loja3.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja3.dto.security.AuthResponseDTO;
import com.loja3.dto.security.LoginDTO;
import com.loja3.dto.security.RegistroUsuarioDTO;
import com.loja3.model.Perfil;
import com.loja3.model.Usuario;
import com.loja3.repository.PerfilRepository;
import com.loja3.repository.UsuarioRepository;
import com.loja3.security.JWTGerador;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	private AuthenticationManager authenticationManager;
	private UsuarioRepository usuarioRepository;
	private PerfilRepository perfilRepository;
	private PasswordEncoder passwordEncoder;
	private JWTGerador jwtGerador;
	

	@Autowired
	public AutenticacaoController(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository,
			PerfilRepository perfilRepository, PasswordEncoder passwordEncoder, JWTGerador jwtGerador) {
		this.authenticationManager = authenticationManager;
		this.usuarioRepository = usuarioRepository;
		this.perfilRepository = perfilRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtGerador = jwtGerador;
	}



	@PostMapping("cadastro")
	public ResponseEntity<String> cadastro(@RequestBody RegistroUsuarioDTO registrooUsuarioDTO){
		if(usuarioRepository.existsByEmail(registrooUsuarioDTO.getEmail())) {
			return new ResponseEntity<>("Usuário já existe!", HttpStatus.BAD_REQUEST);
		}
		Usuario usuario = new Usuario();
		usuario.setEmail(registrooUsuarioDTO.getEmail());
		usuario.setSenha(passwordEncoder.encode(registrooUsuarioDTO.getSenha()));
		
		Perfil perfil = perfilRepository.findByNome("USER").get();
		usuario.setPerfis(Collections.singletonList(perfil));
		usuarioRepository.save(usuario);
		
		return new ResponseEntity<>("Usuário registrado com sucesso!", HttpStatus.OK);
	}
	
	@PostMapping("login")
	public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO){
		Authentication authentication = authenticationManager
								.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwtGerador.gerarToken(authentication);
		return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
	}
}
