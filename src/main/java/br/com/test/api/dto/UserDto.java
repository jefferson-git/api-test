package br.com.test.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @JsonIgnore
    private Integer id;
    @NotBlank(message = "O nome é requerido.")
    private String name;
    @NotBlank(message = "A senha é requerido.")
    private String password;
    @NotBlank(message = "O email é requerido.")
    private String email;

}
