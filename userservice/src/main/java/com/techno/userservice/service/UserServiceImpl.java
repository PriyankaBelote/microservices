package com.techno.userservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techno.userservice.dto.UserDto;
import com.techno.userservice.exception.ResourceNotFoundException;
import com.techno.userservice.model.User;
import com.techno.userservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto getRegistered(UserDto userDto) {
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		userRepository.save(user);
		BeanUtils.copyProperties(user, userDto);
		return userDto;
	}

	@Override
	public UserDto getById(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("not found"));
		return new UserDto(user.getUserId(), user.getEmailId(), user.getName(), user.getPassword());

	}

	@Override
	public List<User> getAll() {
		User user = new User();

		List<User> arrayList = new ArrayList<>();

		userRepository.findAll().forEach(arrayList::add);

		return arrayList;
	}

	@Override
	public User deleteById(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("not present"));
		userRepository.delete(user);
		return null;
	}
}
