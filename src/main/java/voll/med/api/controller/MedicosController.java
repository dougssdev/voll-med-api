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
import voll.med.api.medicos.DadosAtualizacaoMedico;
import voll.med.api.medicos.DadosDoMedico;
import voll.med.api.medicos.DadosListagemMedico;
import voll.med.api.medicos.DetalhamentoMedico;
import voll.med.api.medicos.Medico;
import voll.med.api.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicosController {
	
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosDoMedico dados, UriComponentsBuilder uriBuilder) {
		var medico = new Medico(dados);
		repository.save(medico);
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DetalhamentoMedico(medico));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listagem(@PageableDefault(sort = "nome") Pageable paginacao){
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico :: new);
		
		return ResponseEntity.ok(page);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
		var medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DetalhamentoMedico(medico));
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		var medico = repository.getReferenceById(id);
		medico.desativar();
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		var medico = repository.getReferenceById(id);
		return ResponseEntity.ok(new DetalhamentoMedico(medico));
	}
	
}
