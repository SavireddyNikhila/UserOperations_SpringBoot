package com.spring.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.User;
import com.spring.exception.UserException;
import com.spring.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/users")
	public User saveUser(@RequestBody User user) throws UserException {
		logger.info("This is save method to save single user");
		return userService.saveUser(user);
	}
	
	@PostMapping("/batchusers")
	public List<User> saveListOfUsers(@RequestBody List<User> users) throws UserException {
		logger.info("This is save method to save batch users");
		return userService.saveListOfUsers(users);
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable("id") String userId, @RequestBody User user) throws UserException {
		logger.info("This is update method to update a particular user");
		return userService.updateUser(userId, user);
	}
	
	@GetMapping("/usersByIdorEmailorPhone")
	public User fetchUsersByIdOrEmailOrPhone(@RequestParam(required = false) String userId,
			@RequestParam(required = false) String emailId, @RequestParam(required = false) String phoneNumber)
			throws UserException {
		return userService.fetchUserByIdOrEmailOrPhone(userId, emailId, phoneNumber);
	}
	
	@GetMapping("/users")
	public List<User> ListUsers() throws UserException {
		logger.info("This is fetch method to fetch all the users inside User Controller");
		return userService.ListUsers();
	}

	@GetMapping("/usersByDate/{date1}/{date2}")
	public List<User> fetchUsersCreateDateBetween(@PathVariable("date1") LocalDate Date1,
			@PathVariable("date2") LocalDate Date2) throws UserException {
		return userService.fetchUsersCreateDateBetween(Date1, Date2);
	}

	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable("id") String userId) throws UserException {
		userService.deleteUser(userId);
		return "Deleted Successfully";
	}
}