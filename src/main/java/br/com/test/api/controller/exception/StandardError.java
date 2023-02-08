package br.com.test.api.controller.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter @Setter
@SuppressWarnings("serial")
public class StandardError implements Serializable{
	private LocalDateTime timestamp;
	private final Integer status;
	private String error;
	private String path;
	private final String documentation;
	
	public StandardError(LocalDateTime timestamp, Integer status, String error, String path, String urlDocumentation) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.path = path;
		var request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String url = request.getRequestURL().toString().replace(request.getRequestURI(), "");
        documentation = url + urlDocumentation;
	}	
}
