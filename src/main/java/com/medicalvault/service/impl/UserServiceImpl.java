package com.medicalvault.service.impl;

import com.medicalvault.domain.User;
import com.medicalvault.repository.UserRepository;
import com.medicalvault.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserServiceImpl implements UserService {

  @Autowired UserRepository userRepository;

  @Override
  public User getUserByName(String username) {

    return userRepository.findByUsername(username);
  }

  @Override
  public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
  }
}
