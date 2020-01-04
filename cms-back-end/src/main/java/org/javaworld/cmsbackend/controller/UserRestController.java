package org.javaworld.cmsbackend.controller;

import java.util.List;

import org.javaworld.cmsbackend.entity.User;
import org.javaworld.cmsbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserRestController {

	@Autowired
	private UserService userService;

	@GetMapping("/users/all")
	public List<User> getAllUsers() {
		return userService.findAll();
	}
	
	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable int userId) {
		return userService.findById(userId);
	}
	
	@PostMapping("/users")
	public User addUser(@RequestBody User user) {
		userService.save(user);
		return user;
	}
	
	@PutMapping("/users")
	public User updateUser(@RequestBody User user) {
		userService.update(user);
		return user;
	}

}