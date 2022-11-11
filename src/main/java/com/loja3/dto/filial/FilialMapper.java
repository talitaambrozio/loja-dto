package com.loja3.dto.filial;

import com.loja3.dto.endereco.EnderecoMapper;
import com.loja3.model.Filial;

public class FilialMapper {

	public static Filial fromDTO(RegistroFilialDTO dto) {
		return new Filial(null, dto.getNome(), EnderecoMapper.fromDTO(dto.getEndereco()));
	}
	
	public static ConsultaFilialDTO fromEntity(Filial filial) {
		return new ConsultaFilialDTO(filial.getId(), filial.getNome(), EnderecoMapper.fromEntity(filial.getEndereco()));
	}
}
