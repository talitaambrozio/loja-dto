package com.loja3.dto.fornecedor;

import com.loja3.dto.endereco.EnderecoDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ConsultaFornecedorDTO {
	
	private Long id;
	private String cnpj;
	private String nome;
	private String telefone;
	private String email;
	private EnderecoDTO endereco;

}
