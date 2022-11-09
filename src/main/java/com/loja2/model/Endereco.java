package com.loja2.model;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Endereco {
	
	private String logradouro;
	
	private String numero;
	
	private String complemento;
	
	private String cep;

	
}
