package com.trading.userservice.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 100)
	private String name;

	@Column(length = 100, unique = true, nullable = false)
	private String email;

	@Column(length = 20)
	private String phone;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String name, String email) {
		super();
		this.name = name;
		this.email = email;
		this.phone = "";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}


}
