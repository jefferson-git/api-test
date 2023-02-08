package br.com.test.api.service.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;

@SuppressWarnings("serial")
@Setter
@Getter
public class MethodArgumentTypeMismatchException extends RuntimeException{

	public MethodArgumentTypeMismatchException(String message, Throwable cause) {
		super(message, cause);
	}

	public MethodArgumentTypeMismatchException(String message) {
		super(message);
	}

	public Errors getBindingResult() {
		return null;
	}
}
