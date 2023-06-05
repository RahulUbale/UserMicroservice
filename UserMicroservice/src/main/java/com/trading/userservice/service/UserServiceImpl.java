package com.trading.userservice.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trading.userservice.dao.IUserRepository;
import com.trading.userservice.pojo.User;


@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	IUserRepository repo;

	public UserServiceImpl() {
		System.out.println("In default constructor : " + getClass().getName());
	}

	@Override
	public String saveUser(User u) {
		return repo.saveUser(u);
	}

	@Override
	public User validateUser(String email) {
		return repo.validateUser(email);
	}

	@Override
	public User getUserById(int userId) {
		return repo.getUserById(userId);
	}

}
