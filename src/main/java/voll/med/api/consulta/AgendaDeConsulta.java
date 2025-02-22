package voll.med.api.consulta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import voll.med.api.medicos.Medico;
import voll.med.api.repository.ConsultaRepository;
import voll.med.api.repository.MedicoRepository;
import voll.med.api.repository.PacienteRepository;

@Service
public class AgendaDeConsulta {

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	public void agendar(DadosAgendamentoConsulta dados) {

		if(!pacienteRepository.existsById(dados.idPaciente())) {
			throw new ValidacaoException("Paciente não cadastrado.");
		}
		
		if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
			throw new ValidacaoException("Médico não cadastrado.");
		}
		
		var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
		var medico = escolheMedico(dados);
		var consulta = new Consulta(null, medico, paciente, dados.data(), null);
		
		consultaRepository.save(consulta);
	}

	private Medico escolheMedico(DadosAgendamentoConsulta dados) {
		
		if(dados.idMedico() != null) {
			return medicoRepository.getReferenceById(dados.idMedico());
		}
		
		if(dados.especialidade() == null) {
			throw new ValidacaoException("Especialidade deve ser informada quando não há medico.");
		}
		
		return medicoRepository.randomMedico(dados.especialidade(), dados.data());
		
	}

	public void cancelar(DadosCancelamentoConsulta dados) {
		
		if(!consultaRepository.existsById(dados.idConsulta())) {
			throw new ValidacaoException("Consulta não existente.");
		}
		
		var consulta = consultaRepository.getReferenceById(dados.idConsulta());
		consulta.cancelar(dados.motivo());
		
		
	}

}
