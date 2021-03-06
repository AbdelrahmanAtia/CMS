package org.javaworld.cmsbackend.controller;

import java.util.List;

import org.javaworld.cmsbackend.entity.User;
import org.javaworld.cmsbackend.model.Response;
import org.javaworld.cmsbackend.service.UserService;
import org.javaworld.cmsbackend.validator.OnCreate;
import org.javaworld.cmsbackend.validator.OnUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("/users")
	public List<User> getUsers(@RequestParam String searchTerm, @RequestParam String userStatus,
			@RequestParam int pageNumber, @RequestParam int pageSize) {
		return userService.getUsers(searchTerm, pageNumber, pageSize, userStatus);
	}

	@GetMapping("/users/{userId}")
	public User getUser(@PathVariable int userId) {
		return userService.findById(userId);
	}

	@PostMapping("/users")
	public User addUser(@Validated(value = { OnCreate.class }) 
	                    @RequestBody User user) {
		return userService.save(user);
	}

	@PutMapping("/users")
	public User updateUser(@Validated(value = { OnUpdate.class }) 
	                       @RequestBody User user) {
		return userService.update(user);
	}

	@DeleteMapping("/users/{userId}")
	public Response deleteUser(@PathVariable int userId) {
		return userService.deleteById(userId);
	}

	@GetMapping("/users/isUniqueEmail/{email}/{userId}")
	public boolean isUniqueEmail(@PathVariable String email, @PathVariable int userId) {
		return userService.isUniqueEmail(email, userId);
	}
	
}
