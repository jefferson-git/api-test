package br.com.test.api.service;

import br.com.test.api.dto.UserDto;
import br.com.test.api.model.User;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    List<User> findAll();
    User create(UserDto dto);
    User update(UserDto dto);
    void delete(Integer id);
}
