package com.vaultsystem.user.service;

import com.vaultsystem.user.config.UserConfig;
import com.vaultsystem.user.dto.UserDTO;
import com.vaultsystem.user.exceptions.UserAlreadyExistsException;
import com.vaultsystem.user.model.User;
import com.vaultsystem.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserDTO userDTO) {
        User user = new User(userDTO.getUserId(), userDTO.getEmail(), userDTO.getDwollaCustomerUrl(),
                userDTO.getDwollaCustomerId(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getAddress(),
                userDTO.getCity(), userDTO.getPostalCode(), userDTO.getDateOfBirth());
        String hashedPassword = passwordEncoder.encode(userDTO.getPassword());
        user.setPassword(hashedPassword);
        String ssn = userDTO.getSsn() == null ? null : UserConfig.encrypt(userDTO.getSsn());
        user.setSsn(ssn);
        User savedUser = userRepository.save(user);
        if (savedUser == null) {
            throw new UserAlreadyExistsException("User is already registered with given email" + user.getEmail());
        }
    }
}
