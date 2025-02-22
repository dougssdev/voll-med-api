package voll.med.api.medicos;

import jakarta.validation.constraints.NotNull;
import voll.med.api.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
		
		@NotNull
		Long id,
		String nome, 
		String telefone,
		DadosEndereco endereco
		
		) {

}
