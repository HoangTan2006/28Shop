package com.Shop28.repository;

import com.Shop28.dto.response.UserResponse;
import com.Shop28.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    @NativeQuery("SELECT id, first_name, last_name, phone, email FROM shop28.user;")
    List<UserResponse> getListUserFromDB();
}
