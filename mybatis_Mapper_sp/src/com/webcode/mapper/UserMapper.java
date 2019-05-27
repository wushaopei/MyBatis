package com.webcode.mapper;

import java.util.List;

import com.webcode.pojo.User;

public interface UserMapper {
	
	public User queryUserById(Integer id);

	public List<User> queryUsers();

	public int saveUser(User user);

	public int deleteUserById(Integer id);

	public int updateUser(User user);
}
