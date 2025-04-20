package com.vaultsystem.user.controller;

import com.vaultsystem.user.constants.UserConstants;
import com.vaultsystem.user.dto.ResponseDTO;
import com.vaultsystem.user.dto.UserDTO;
import com.vaultsystem.user.model.User;
import com.vaultsystem.user.repository.UserRepository;
import com.vaultsystem.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@Valid @RequestBody UserDTO user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(UserConstants.STATUS_201, UserConstants.MESSAGE_201));

        } catch (Exception ex) {

        }
    }
}
