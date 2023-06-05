package com.trading.userservice.service;

import com.trading.userservice.pojo.User;

public interface IUserService {

	public String saveUser(User u);
	
	public User validateUser(String email);
	
	public User getUserById(int userId);
	
}
