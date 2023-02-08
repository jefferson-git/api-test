package br.com.test.api.controller.exception;

import br.com.test.api.service.exception.DataIntegrityViolationException;
import br.com.test.api.service.exception.IntegrityConstraintViolationException;
import br.com.test.api.service.exception.NotFoundException;
import br.com.test.api.service.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;


@ControllerAdvice
public class ControllerExceptionHandler {
	
	@Value("${springdoc.swagger-ui.path}")
	private String urlDocumentation;
	
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> ObjectNotFoundException(ObjectNotFoundException ex, HttpServletRequest request){
		StandardError erro = new StandardError(
				LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage(),
				request.getRequestURI(), urlDocumentation
		);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<StandardError> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
																			 HttpServletRequest request){
		StandardError erro = new StandardError(
				LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				"argumento esperado na url é inteiro e não um caracter",
				request.getRequestURI(), urlDocumentation
		);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<StandardError> NotFoundException(NotFoundException ex, HttpServletRequest request){
		StandardError erro = new StandardError(
				LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
				"Essa url não existe!!", request.getRequestURI(), urlDocumentation
		);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> DataIntegrityViolationException(DataIntegrityViolationException ex,
																		 HttpServletRequest request){
		StandardError erro = new StandardError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				request.getRequestURI(), urlDocumentation);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(IntegrityConstraintViolationException.class)
	public ResponseEntity<StandardError> IntegrityConstraintViolationException(IntegrityConstraintViolationException ex,
																			   HttpServletRequest request){
		StandardError erro = new StandardError(
				LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(),
				request.getRequestURI(), urlDocumentation
		);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
		
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> MethodArgumentNotValidException(MethodArgumentNotValidException ex,
																		 HttpServletRequest request){
		ValidationError errors = new ValidationError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
				"Erro na validação dos campos", request.getRequestURI(), urlDocumentation);
		for (FieldError field : ex.getBindingResult().getFieldErrors()) {
			errors.AddErrors(field.getField(), field.getDefaultMessage());
		}		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
}
