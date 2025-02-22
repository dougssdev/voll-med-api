package voll.med.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import voll.med.api.repository.UserRepository;

@Component
public class Filtro extends OncePerRequestFilter {

	@Autowired
	private UserRepository ur;
	
	@Autowired
	TokenService ts;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		var tokenJWT = recuperarToken(request);
		
		
		if(tokenJWT != null) {
			var subject = ts.getSubject(tokenJWT);
			var user = ur.findByLogin(subject);
			
			var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		
		filterChain.doFilter(request, response);
		
	}

	private String recuperarToken(HttpServletRequest request) {
		
		var header = request.getHeader("Authorization");
		
		if(header != null) {
            return header.replace("Bearer ", "");
		}
		
		return null;
	}

}
