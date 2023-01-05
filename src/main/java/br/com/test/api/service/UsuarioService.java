package br.com.test.api.service;

import br.com.test.api.dto.UsuarioDto;
import br.com.test.api.model.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario findById(Integer id);
    List<Usuario> findAll();
    Usuario create(UsuarioDto dto);
    Usuario update(Integer id, UsuarioDto dto);
    void delete(Integer id);
    void verificaEmail(UsuarioDto dto);
}
