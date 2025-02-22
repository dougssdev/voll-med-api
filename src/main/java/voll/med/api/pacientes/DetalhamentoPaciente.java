package voll.med.api.pacientes;

import voll.med.api.endereco.Endereco;

public record DetalhamentoPaciente(Long id,
		String nome, 
		String telefone, 
		String cpf, 
		Endereco endereco) {
	
	public DetalhamentoPaciente(Paciente paciente) {
		this(paciente.getId(), 
				paciente.getNome(), 
				paciente.getTelefone(), 
				paciente.getCpf(), 
				paciente.getEndereco());
		
	}
}
