package voll.med.api.pacientes;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern;
import voll.med.api.endereco.DadosEndereco;

public record DadosPaciente(
		
		@NotBlank
		String nome, 
		
		@NotBlank
		@Email
		String email,

		@NotNull
		@Valid
		DadosEndereco endereco, 
		
		@NotBlank
		String telefone,
		
		@NotBlank
		@Pattern(regexp = "\\d{11}")
		String cpf
		) {
	
}
