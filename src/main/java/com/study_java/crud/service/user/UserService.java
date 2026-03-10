package com.study_java.crud.service.user;

import com.study_java.crud.dto.UserDTO;
import com.study_java.crud.exceptions.ResourceNotFoundException;
import com.study_java.crud.modals.User;
import com.study_java.crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email not found"));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresentOrElse(userRepository::delete ,() -> {
           throw new ResourceNotFoundException("User not found");
        });
    }

    @Override
    public UserDTO updateUser(UserDTO request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setFullName(request.getFullName());
        user.setAddress(request.getAddress());
        user.setPhone(request.getPhone());
        user.setActive(request.isActive());
        return request;
    }

    @Override
    public User createUser(UserDTO request) {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setAddress(request.getAddress());
        user.setPhone(request.getPhone());
        user.setActive(request.isActive());
        user.setEmail(request.getEmail());
        return userRepository.save(user);
    }
}
