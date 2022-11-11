package com.loja3.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.loja3.exception.EntityNotFoundException;
import com.loja3.model.Filial;
import com.loja3.repository.FilialRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FilialService {

	
	private final FilialRepository filialRepository;

	@Transactional
	public Filial salvar(Filial filial) {
		return filialRepository.save(filial);
	}

	
	public Page<Filial> buscarTodasFiliais(Pageable pageable){
		return filialRepository.findAll(pageable);
	}

	public Filial buscarFilial(Long id) {

		Optional<Filial> optional = filialRepository.findById(id);

		return optional.orElseThrow(() -> new EntityNotFoundException("Filial não encontrada"));
	}

	@Transactional
	public void excluir(Long id) {
		Filial filialOriginal = this.buscarFilial(id);
		filialRepository.delete(filialOriginal);
	}

	public Filial atualizarFilial(Filial filial, Long id) {

		Filial filialOriginal = this.buscarFilial(id);

		filial.setId(filialOriginal.getId());
		return filialRepository.save(filial);

	}

}
