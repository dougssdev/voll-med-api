package voll.med.api.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
		
		@NotBlank
		String logradouro, 
		
		@NotBlank
		@Pattern(regexp = "\\d{8}")
		String cep,
		
		@NotBlank
		String uf, 
		
		@NotBlank
		String bairro, 
		
		String complemento, 
		
		String numero,
		
		@NotBlank
		String cidade ) {

}
