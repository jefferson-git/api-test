package br.com.test.api.service.exception;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Setter @Getter
public class DataIntegrityViolationException extends RuntimeException{

	public DataIntegrityViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataIntegrityViolationException(String message) {
		super(message);
	}
}
