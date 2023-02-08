package br.com.test.api.controller.exception;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	private List<FieldMessenger> errors = new ArrayList<>();	
	
	public void AddErrors(String fieldName, String message) {
		this.errors.add(new FieldMessenger(fieldName, message));
	}

	public ValidationError(LocalDateTime timestamp, Integer status, String error, String path, String urlDocumentation) {
		super(timestamp, status, error, path, urlDocumentation);
	}		
}
