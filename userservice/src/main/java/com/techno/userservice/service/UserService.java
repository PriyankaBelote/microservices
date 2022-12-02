package com.techno.userservice.service;

import java.util.List;

import com.techno.userservice.dto.UserDto;
import com.techno.userservice.model.User;


public interface UserService {

	UserDto getRegistered(UserDto userDto);

	UserDto getById(Integer userId);

	List<User> getAll();

	User deleteById(Integer userId);

}
