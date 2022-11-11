package com.loja3.dto.filial;

import com.loja3.dto.endereco.EnderecoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ConsultaFilialDTO {
	
	private Long id;
	private String nome;
	private EnderecoDTO endereco;
}
