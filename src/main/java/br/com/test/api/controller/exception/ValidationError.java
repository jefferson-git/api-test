package br.com.test.api.controller.exception;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	private List<FieldMessenger> errors = new ArrayList<>();	
	
	public void AddErrors(String fieldName, String message) {
		this.errors.add(new FieldMessenger(fieldName, message));
	}

	public ValidationError(Long timestamp, Integer status, String mensage, String urlDocumentation) {
		super(timestamp, status, mensage, urlDocumentation);
	}		
}
