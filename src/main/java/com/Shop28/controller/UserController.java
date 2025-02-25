package com.Shop28.controller;

import com.Shop28.dto.request.UserRequest;
import com.Shop28.dto.request.UserUpdateRequest;
import com.Shop28.dto.response.ResponseDTO;
import com.Shop28.dto.response.UserResponse;
import com.Shop28.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create-user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO<UserResponse> createUser(@RequestBody UserRequest userRequest) {

        UserResponse userResponse = userService.createUser(userRequest);

        return ResponseDTO.<UserResponse>builder()
                .status(HttpStatus.CREATED.value())
                .message("Success !")
                .data(userResponse)
                .build();
    }

    @GetMapping("/get-user")
    public ResponseDTO<List<UserResponse>> getListUser() {
        List<UserResponse> listUser = userService.findAll();

        return ResponseDTO.<List<UserResponse>>builder()
                .status(HttpStatus.OK.value())
                .message("Success !")
                .data(listUser)
                .build();
    }

    @DeleteMapping("/delete-user")
    public ResponseDTO<String> deleteUser(@RequestParam Integer id) {
        userService.deleteUserById(id);

        return ResponseDTO.<String>builder()
                .status(HttpStatus.OK.value())
                .message("Success !")
                .data("successfully deleted user with id: " + id)
                .build();
    }

    @PutMapping("/update-user")
    public ResponseDTO<UserResponse> updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        UserResponse userResponse = userService.updateUser(userUpdateRequest);

        return ResponseDTO.<UserResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Success !")
                .data(userResponse)
                .build();
    }
}
