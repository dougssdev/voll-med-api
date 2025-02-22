package voll.med.api.medicos;

import voll.med.api.endereco.Endereco;

public record DetalhamentoMedico(
		Long id, 
		String nome, 
		String email,
		String telefone,
		String crm,
		Especialidade especialidade, 
		Endereco endereco) {
	
	public DetalhamentoMedico(Medico medico) {
		this(medico.getId(),
			medico.getNome(), 
			medico.getEmail(), 
			medico.getTelefone(), 
			medico.getCrm(), 
			medico.getEspecialidade(), 
			medico.getEndereco());
	}
}
