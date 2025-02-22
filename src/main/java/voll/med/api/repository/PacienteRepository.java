package voll.med.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import voll.med.api.pacientes.DadosListagemPaciente;
import voll.med.api.pacientes.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

	Page<Paciente> findAllByAtivoTrue(Pageable paginacao);
	
}
