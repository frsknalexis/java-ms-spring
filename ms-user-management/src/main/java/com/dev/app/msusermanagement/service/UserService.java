package com.dev.app.msusermanagement.service;

import java.util.List;

import com.dev.app.msusermanagement.entity.User;

public interface UserService {

	User save(User user);
	
	User findByUsername(String username);
	
	List<String> findUsers(List<Long> userIdList);
}
