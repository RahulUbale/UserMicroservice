package com.trading.userservice.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trading.userservice.pojo.User;

@Repository
public class UserRepositoryImpl implements IUserRepository {
	
	@Autowired
	private EntityManager mgr;

	@Override
	public String saveUser(User u) {
		String message = "Signup completed successfully";
		mgr.persist(u);
		return message;
	}

	@Override
	public User validateUser(String email) throws NoResultException{
		String jpql = "select u from User u where u.email=:em";
		return mgr.createQuery(jpql, User.class).setParameter("em", email).getSingleResult();
	}

	@Override
	public User getUserById(int userId) {
		String jpql = "select u from User where u.id=:userId";
		User user = mgr.createQuery(jpql, User.class).setParameter("userId", userId).getSingleResult();
		return user;
	}

}
