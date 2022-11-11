package com.loja3.dto.fornecedor;

import com.loja3.dto.endereco.EnderecoDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegistroFornecedorDTO {
	
	private String cnpj;
	private String nome;
	private String telefone;
	private String email;
	private EnderecoDTO endereco;
	

}
