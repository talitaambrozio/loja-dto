package com.loja3.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loja3.model.Filial;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Long> {

	Page<Filial> findAll(Pageable pageable);
}
