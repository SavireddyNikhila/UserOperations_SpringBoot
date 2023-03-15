package com.spring.service;

import java.time.LocalDate;
import java.util.List;

import com.spring.entity.User;
import com.spring.exception.UserException;

public interface UserService {

	public User saveUser(User user) throws UserException;

	public List<User> saveListOfUsers(List<User> users) throws UserException;

	public User updateUser(String userId, User user) throws UserException;

	public User fetchUserByIdOrEmailOrPhone(String userId, String emailId, String phoneNumber) throws UserException;

	public List<User> ListUsers() throws UserException;

	public List<User> fetchUsersCreateDateBetween(LocalDate date1, LocalDate date2) throws UserException;

	public void deleteUser(String userId) throws UserException;

}
