package com.springbanking.easymoney.config;

import com.springbanking.easymoney.model.Customer;
import com.springbanking.easymoney.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EasyMoneyUserDetails implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName = null;
        String password = null;
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Customer> customer = customerRepository.findByEmail(username);
        if (customer.isEmpty()) {
            throw new UsernameNotFoundException("User Details not found by the user: " + username);
        } else {
            userName = customer.getFirst().getEmail();
            password = customer.getFirst().getPassword();
            authorities.add(new SimpleGrantedAuthority(customer.getFirst().getRole()));
        }
        return new User(userName, password, authorities);
    }
}
