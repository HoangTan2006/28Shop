package com.Shop28.service;

import com.Shop28.dto.request.UserRequest;
import com.Shop28.dto.request.UserUpdateRequest;
import com.Shop28.dto.response.UserResponse;
import com.Shop28.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    UserResponse createUser(UserRequest userRequest);

    void deleteUserById(Integer id);

    List<UserResponse> findAll();

    UserResponse updateUser(UserUpdateRequest userUpdateRequest);

    UserDetailsService getUserDetailService();
}
