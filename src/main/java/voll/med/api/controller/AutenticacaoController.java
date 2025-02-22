package voll.med.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import voll.med.api.infra.security.DadosToken;
import voll.med.api.infra.security.TokenService;
import voll.med.api.usuario.DadosAutenticacao;
import voll.med.api.usuario.User;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager auth;
	
	@Autowired
	private TokenService ts;
	
	@PostMapping
	@Transactional
	public ResponseEntity realizarLogin(@RequestBody @Valid DadosAutenticacao dados) {
		var tokenAuth = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		var autenticacao = auth.authenticate(tokenAuth);
		
		var tokenJWT = ts.gerarToken((User) autenticacao.getPrincipal());
		
		return ResponseEntity.ok(new DadosToken(tokenJWT));
	}
}
