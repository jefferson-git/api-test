package br.com.test.api.service.implement;

import br.com.test.api.config.ModelMapperConfig;
import br.com.test.api.dto.UserDto;
import br.com.test.api.model.User;
import br.com.test.api.repository.UserRepository;
import br.com.test.api.service.UserService;
import br.com.test.api.service.exception.IntegrityConstraintViolationException;
import br.com.test.api.service.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
	
    private final UserRepository repository;
    private final ModelMapperConfig model;
    
    @Override
    public User findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User create(UserDto dto) {
    	verificaEmail(dto);
        return repository.save(model.mapper().map(dto, User.class));
    }

    @Override
    public User update(Integer id, UserDto dto) {     	
    	verificaEmail(dto);
    	dto.setId(findById(id).getId());    	
        return repository.save(model.mapper().map(dto, User.class));
    }

    @Override
    public void delete(Integer id) {
    	repository.delete(findById(id));
    }

	@Override
	public void verificaEmail(UserDto dto) {
		Optional<User> user = repository.findByEmail(dto.getEmail());
		if(user.isPresent())
    		throw new IntegrityConstraintViolationException("Esse email já está em uso!");		
	}
}
