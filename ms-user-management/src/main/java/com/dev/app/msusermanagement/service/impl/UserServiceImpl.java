package com.dev.app.msusermanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.app.msusermanagement.entity.User;
import com.dev.app.msusermanagement.repository.UserRepository;
import com.dev.app.msusermanagement.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	@Qualifier("userRepository")
	private UserRepository userRepository;

	//we will create bean for it in security config
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}

	@Override
	public List<String> findUsers(List<Long> userIdList) {
		return userRepository.findByIdList(userIdList);
	}
}
