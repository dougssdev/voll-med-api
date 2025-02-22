package voll.med.api.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratamentoDeErros {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratar404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratar400(MethodArgumentNotValidException ex) {
		
		var error = ex.getFieldErrors();
		
		return ResponseEntity.badRequest().body(error.stream().map(DataErros :: new).toList());
	}
	

	
	private record DataErros (String field, String message) {
		
		public DataErros(FieldError erro ) {
			this(erro.getField(), erro.getDefaultMessage());
		}
		
	}
	
}
