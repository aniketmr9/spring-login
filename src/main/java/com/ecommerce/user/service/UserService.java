package com.ecommerce.user.service;

import com.ecommerce.login.model.Role;
import com.ecommerce.login.model.User;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.webservice.EcommerceRestServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService {
   
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

	/*
	 * public User findUserByEmail(String email) { return
	 * userRepository.findByEmail(email); }
	 * 
	 * public User findUserByUserName(String userName) { return
	 * userRepository.findByUserName(userName); }
	 */

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        EcommerceRestServiceImpl<Role> ws = new EcommerceRestServiceImpl<Role>(new Role());
        Role userRole = ws.postEntity("/user/getRole", user.getRole().getRole());
        //Role userRole = roleRepository.findByRole(user.getRole().getRole());
        user.setRole(userRole);
        EcommerceRestServiceImpl<User> ws1 = new EcommerceRestServiceImpl<User>(new User());
        User u = ws1.postEntity("/user/create", user);
        return u;
    }

}