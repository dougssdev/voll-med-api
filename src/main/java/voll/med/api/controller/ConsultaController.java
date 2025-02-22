package voll.med.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import voll.med.api.consulta.AgendaDeConsulta;
import voll.med.api.consulta.DadosAgendamentoConsulta;
import voll.med.api.consulta.DadosCancelamentoConsulta;
import voll.med.api.consulta.DadosDetalhamentoConsulta;

//Trecho de código suprimido

@RestController
@RequestMapping("consultas")
public class ConsultaController {

	@Autowired
	private AgendaDeConsulta agenda;
	
 @PostMapping
 @Transactional
 public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
     System.out.println(dados);
     return ResponseEntity.ok(new DadosDetalhamentoConsulta(null, null, null, null));
 }
 
 @DeleteMapping
 @Transactional
 public ResponseEntity excluir(@RequestBody @Valid DadosCancelamentoConsulta dados) {
	 agenda.cancelar(dados);
	 return ResponseEntity.noContent().build();
 }

}