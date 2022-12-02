package com.techno.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techno.userservice.dto.ResponseDto;
import com.techno.userservice.dto.UserDto;
import com.techno.userservice.model.User;
import com.techno.userservice.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/register")
	public ResponseEntity<ResponseDto> register(@RequestBody UserDto userDto) {
		return new ResponseEntity<>(new ResponseDto(false, "added successfully", service.getRegistered(userDto)),
				HttpStatus.OK);

	}

	@GetMapping("/getById/{userId}")
	public ResponseEntity<ResponseDto> getById(@PathVariable Integer userId) {
		return new ResponseEntity<ResponseDto>(new ResponseDto(false, "found successfully", service.getById(userId)),
				HttpStatus.OK);

	}

	@GetMapping("/getAll")
	public ResponseEntity<ResponseDto> getAll() {
		List<User> list = service.getAll();

		return new ResponseEntity<>(new ResponseDto(false, "all data found", list), HttpStatus.OK);

	}
	
	@DeleteMapping("/deleteById/{userId}")
	public ResponseEntity<ResponseDto> deleteById(@PathVariable Integer userId) {
		return new ResponseEntity<ResponseDto>(new ResponseDto(false, "deleted successfully", service.deleteById(userId)),
				HttpStatus.OK);

	}
}
