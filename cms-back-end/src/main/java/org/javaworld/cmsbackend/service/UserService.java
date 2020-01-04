package org.javaworld.cmsbackend.service;

import java.util.List;

import org.javaworld.cmsbackend.entity.User;

public interface UserService {

	public List<User> findAll();

	public void save(User user);

	public User findById(int userId);

	public void update(User user);

}