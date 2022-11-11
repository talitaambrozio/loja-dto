package com.loja3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loja3.model.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>{
	
	Optional<Perfil> findByNome(String nome);

}
