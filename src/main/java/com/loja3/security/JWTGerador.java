package com.loja3.security;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTGerador {
	
	public String gerarToken(Authentication authentication) {
		String nomeUsuario = authentication.getName();
		Date dataAtual = new Date();
		Date dataExpiracao = new Date(dataAtual.getTime() + SecurityConstants.JWT_EXPIRACAO);
		
		String token = Jwts.builder()
							.setSubject(nomeUsuario)
							.setIssuedAt(new Date())
							.setExpiration(dataExpiracao)
							.signWith(SignatureAlgorithm.HS512, SecurityConstants.JWT_SECRET)
							.compact();
		return token;
	}
	
	public String getUsernameFromJWT(String token) {
		Claims claims = Jwts.parser()
							.setSigningKey(SecurityConstants.JWT_SECRET)
							.parseClaimsJws(token)
							.getBody();
		return claims.getSubject();		
	}
	
	public boolean validarToken(String token) {
		try {
			Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token);
			return true;
		}catch(Exception e) {
			throw new AuthenticationCredentialsNotFoundException("JWT expirado ou incorreto.");
		}
	}

}
