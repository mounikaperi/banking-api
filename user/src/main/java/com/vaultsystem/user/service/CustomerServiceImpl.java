package com.vaultsystem.user.service;

import com.vaultsystem.user.config.UserConfig;
import com.vaultsystem.user.dto.CustomerDTO;
import com.vaultsystem.user.dto.UserDTO;
import com.vaultsystem.user.exceptions.UserAlreadyExistsException;
import com.vaultsystem.user.model.Customer;
import com.vaultsystem.user.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository userRepository;

    @Transactional
    public void registerCustomer(CustomerDTO userDTO) {
        Customer user = new Customer(userDTO.getCustomerId(), userDTO.getUserId(), userDTO.getPassword(), userDTO.getEmailAddress(),
                userDTO.getFirstName(), userDTO.getLastName(), userDTO.getAddress(),
                userDTO.getCity(), userDTO.getPostalCode(), userDTO.getDateOfBirth());
        Customer savedUser = userRepository.save(user);
        if (savedUser == null) {
            throw new UserAlreadyExistsException("User is already registered with given email" + user.getEmailAddress());
        }
    }
}
