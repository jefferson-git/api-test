package br.com.test.api.service.implement;

import br.com.test.api.dto.UsuarioDto;
import br.com.test.api.model.Usuario;
import br.com.test.api.repository.UsuarioRepository;
import br.com.test.api.service.UsuarioService;
import br.com.test.api.service.exception.IntegrityConstraintViolationException;
import br.com.test.api.service.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
	
    private UsuarioRepository repository;

    private ModelMapper model;
    
    @Override
    public Usuario findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado com id:" + id));
    }

    @Override
    public List<Usuario> findAll() {
        return repository.findAll();
    }

    @Override
    public Usuario create(UsuarioDto dto) {
    	verificaEmail(dto);
        return repository.save(model.map(dto, Usuario.class));
    }

    @Override
    public Usuario update(Integer id, UsuarioDto dto) {
    	dto.setId(id);       	
    	verificaEmail(dto);    	
        return repository.save(model.map(dto, Usuario.class));
    }

    @Override
    public void delete(Integer id) {
    	repository.delete(findById(id));
    } 

	@Override
	public void verificaEmail(UsuarioDto dto) {
		Optional<Usuario> user = repository.findByEmail(dto.getEmail());		
		if(user.isPresent() && !user.get().getId().equals(dto.getId())) 
    		throw new IntegrityConstraintViolationException("Esse email já está em uso!");			
	}
}
