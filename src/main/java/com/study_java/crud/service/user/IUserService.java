package com.study_java.crud.service.user;

import com.study_java.crud.dto.UserDTO;
import com.study_java.crud.modals.User;

public interface IUserService {
    User getUserById(Long id);
    User getUserByEmail(String email);
    void deleteUser(Long id);
    UserDTO updateUser(UserDTO user);
    User createUser(UserDTO user);
}
