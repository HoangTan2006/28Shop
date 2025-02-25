package com.Shop28.service.impl;

import com.Shop28.dto.request.UserRequest;
import com.Shop28.dto.request.UserUpdateRequest;
import com.Shop28.dto.response.UserResponse;
import com.Shop28.entity.Role;
import com.Shop28.entity.User;
import com.Shop28.entity.UserHasRole;
import com.Shop28.repository.RoleRepository;
import com.Shop28.repository.UserRepository;
import com.Shop28.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("username not found in db"));
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {

        if(userRepository.existsByUsername(userRequest.getUsername())) throw new RuntimeException("Username already exists !");

        Role role = roleRepository.findByName("USER").orElseThrow(() -> new EntityNotFoundException("Role not found"));

        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .username(userRequest.getUsername())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
                .isLock(false)
                .build();

        UserHasRole userHasRole = UserHasRole.builder()
                .user(user)
                .role(role)
                .build();

        user.setRoles(Set.of(userHasRole));


        User userCreated = userRepository.save(user);

        return UserResponse.builder()
                .id(userCreated.getId())
                .firstName(userCreated.getFirstName())
                .lastName(userCreated.getLastName())
                .phone(userCreated.getPhone())
                .email(userCreated.getEmail())
                .build();
    }

    @Override
    public void deleteUserById(Integer id) {
        if(!userRepository.existsById(id)) throw new EntityNotFoundException("id not exist");
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.getListUserFromDB();
    }

    @Override
    public UserResponse updateUser(UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userUpdateRequest.getId()).orElseThrow(() -> new EntityNotFoundException("username not found !"));

        user.setFirstName(userUpdateRequest.getFirstName());
        user.setLastName(userUpdateRequest.getLastName());
        user.setPhone(userUpdateRequest.getPhone());
        user.setEmail(userUpdateRequest.getEmail());

        User userUpdated = userRepository.save(user);

        return UserResponse.builder()
                .id(userUpdated.getId())
                .firstName(userUpdated.getFirstName())
                .lastName(userUpdated.getLastName())
                .phone(userUpdated.getPhone())
                .email(userUpdated.getEmail())
                .build();
    }

    @Override
    public UserDetailsService getUserDetailService() {
        return username -> userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("Tài khoản hoặc mật khẩu không chính xác"));
    }
}
