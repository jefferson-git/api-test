package br.com.test.api.service.implement;

import br.com.test.api.dto.UserDto;
import br.com.test.api.model.User;
import br.com.test.api.repository.UserRepository;
import br.com.test.api.service.UserService;
import br.com.test.api.service.exception.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
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
        return null;
    }

    @Override
    public User update(UserDto dto) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
