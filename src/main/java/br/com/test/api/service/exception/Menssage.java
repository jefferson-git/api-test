package br.com.test.api.service.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@SuppressWarnings("serial")
public class Menssage extends RuntimeException {

	public Menssage(String message, Throwable cause) {
		super(message, cause);
	}

	public Menssage(String message) {
		super(message);
	}   
}
