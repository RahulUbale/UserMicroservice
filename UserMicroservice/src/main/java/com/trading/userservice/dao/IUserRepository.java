package com.trading.userservice.dao;

import com.trading.userservice.pojo.User;

public interface IUserRepository {
	public String saveUser(User u);

	public User validateUser(String email);
	
	public User getUserById(int userId);

}
