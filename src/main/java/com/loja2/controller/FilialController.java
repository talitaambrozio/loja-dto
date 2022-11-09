package com.loja2.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja2.dto.filial.ConsultaFilialDTO;
import com.loja2.dto.filial.FilialMapper;
import com.loja2.dto.filial.RegistroFilialDTO;
import com.loja2.model.Filial;
import com.loja2.service.FilialService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("v1/filiais")
@AllArgsConstructor
public class FilialController {

	private final FilialService filialService;

	@GetMapping
	public ResponseEntity<Page<ConsultaFilialDTO>> buscarTodasAsFiliais(@PageableDefault(size = 3) Pageable pageable) {

		return ResponseEntity.ok(filialService.buscarTodasFiliais(pageable)
				.map(FilialMapper::fromEntity));

	}

	@PostMapping
	public ResponseEntity<ConsultaFilialDTO> salvarFilial(@RequestBody RegistroFilialDTO dto) {

		Filial filial = filialService.salvar(FilialMapper.fromDTO(dto));

		return ResponseEntity.ok(FilialMapper.fromEntity(filial));
	}

	@GetMapping("{id}")
	public ResponseEntity<ConsultaFilialDTO> buscarFilial(@PathVariable Long id) {

		Filial filial = filialService.buscarFilial(id);
		return ResponseEntity.ok(FilialMapper.fromEntity(filial));

	}

	@PutMapping("{id}")
	public ResponseEntity<ConsultaFilialDTO> alterarFilial(@RequestBody RegistroFilialDTO dto, @PathVariable Long id) {

		try {
			Filial filial = filialService.atualizarFilial(FilialMapper.fromDTO(dto), id);

			return ResponseEntity.ok(FilialMapper.fromEntity(filial));

		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("{id}")
	public ResponseEntity<ConsultaFilialDTO> excluirFilial(@PathVariable Long id) {

		try {
			filialService.excluir(id);

			return ResponseEntity.ok().build();

		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}

	}

}
