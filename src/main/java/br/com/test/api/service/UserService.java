package br.com.test.api.service;

import br.com.test.api.dto.UserDto;
import br.com.test.api.model.User;

public interface UserService {
    User findById(Integer id);
    User findAll();
    User create(UserDto dto);
    User update(UserDto dto);
    void delete(Integer id);
}
