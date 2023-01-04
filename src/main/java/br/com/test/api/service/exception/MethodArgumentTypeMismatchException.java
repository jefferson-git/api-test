package br.com.test.api.service.exception;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Setter @Getter
public class MethodArgumentTypeMismatchException extends RuntimeException{

	public MethodArgumentTypeMismatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public MethodArgumentTypeMismatchException(String message) {
		super(message);
	}
}
