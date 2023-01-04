package br.com.test.api.controller.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.test.api.service.exception.*;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@Value("${springdoc.swagger-ui.path}")
	private String urlDocumentation;
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> ObjectNotFoundException(ObjectNotFoundException ex){
		StandardError erro = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), urlDocumentation);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<StandardError> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
		StandardError erro = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"argumento esperado na url é inteiro e não um caracter", urlDocumentation);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandardError> NotFoundException(NotFoundException ex){
		StandardError erro = new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(),
				"Essa url não existe!!", urlDocumentation);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> DataIntegrityViolationException(DataIntegrityViolationException ex){
		StandardError erro = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), urlDocumentation);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
		
	@ExceptionHandler(IntegrityConstraintViolationException.class)
	public ResponseEntity<StandardError> IntegrityConstraintViolationException(IntegrityConstraintViolationException ex){
		StandardError erro = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), urlDocumentation);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
		
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> MethodArgumentNotValidException(MethodArgumentNotValidException ex){
		ValidationError errors = new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
				"Erro na validação dos campos", urlDocumentation);
		for (FieldError field : ex.getBindingResult().getFieldErrors()) {
			errors.AddErrors(field.getField(), field.getDefaultMessage());
		}		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
}
