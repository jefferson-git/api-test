package br.com.test.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto implements Serializable {

	@JsonIgnore
    private Integer id;
	
    @NotBlank(message = "O nome é requerido.")    
    private String nome;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    @NotBlank(message = "A senha é requerido.")    
    private String password;
    
    @NotBlank(message = "O email é requerido.")
    private String email;
}