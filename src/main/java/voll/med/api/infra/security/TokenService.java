package voll.med.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import voll.med.api.usuario.User;

@Service
public class TokenService {

	@Value("${api.security.token-secret}")
	private String secret;
		
	public String gerarToken(User usuario) {
		try {
			var algoritmo = Algorithm.HMAC256(secret);
			return JWT.create()
					.withIssuer("API Voll.med")
					.withSubject(usuario.getLogin())
					.withExpiresAt(dataExpiracao())
					.sign(algoritmo);
		} catch (JWTCreationException e) {
			throw new RuntimeException("erro ao gerar token", e);
		}
	}
	
	public String getSubject(String tokenJWT) {
		var algoritmo = Algorithm.HMAC256(secret);
		try {
		return JWT.require(algoritmo)
				.withIssuer("API Voll.med")
				.build()
				.verify(tokenJWT)
				.getSubject();
		} catch (JWTVerificationException e){
			throw new RuntimeException ("Token Inválido.");
			
		}
		
		}
	

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
