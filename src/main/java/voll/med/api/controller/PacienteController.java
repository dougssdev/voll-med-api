package voll.med.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import voll.med.api.pacientes.DadosAtualizacaoPaciente;
import voll.med.api.pacientes.DadosListagemPaciente;
import voll.med.api.pacientes.DadosPaciente;
import voll.med.api.pacientes.DetalhamentoPaciente;
import voll.med.api.pacientes.Paciente;
import voll.med.api.repository.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired	
	private PacienteRepository pc;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosPaciente dados, UriComponentsBuilder uriBuilder) {
		var paciente = new Paciente(dados);
		pc.save(paciente);
		
		var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DetalhamentoPaciente(paciente));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(sort = "nome") Pageable paginacao){
		var page = pc.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente :: new);
		return ResponseEntity.ok(page);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
		var paciente = pc.getReferenceById(dados.id());
		paciente.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DetalhamentoPaciente(paciente));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id ) {
		var paciente = pc.getReferenceById(id);
		paciente.excluir();
		
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id ) {
		var paciente = pc.getReferenceById(id);
		
		return ResponseEntity.ok(new DetalhamentoPaciente(paciente));
		
	}
}
