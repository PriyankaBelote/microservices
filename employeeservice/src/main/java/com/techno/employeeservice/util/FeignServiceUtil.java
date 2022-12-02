package com.techno.employeeservice.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.techno.employeeservice.dto.ResponseDto;
import com.techno.employeeservice.dto.UserDto;


@FeignClient(name="user-service")
public interface FeignServiceUtil {

	@PostMapping("user/register")
	ResponseEntity<ResponseDto> register(@RequestBody UserDto userDto);
	
	@GetMapping("user/getAll")
	public ResponseEntity<ResponseDto> getAll();
	
	
	@GetMapping("user/getById/{userId}")
	public ResponseEntity<ResponseDto> getById(@PathVariable Integer userId);
	
	
	@DeleteMapping("user/deleteById/{userId}")
	public ResponseEntity<ResponseDto> deleteById(@PathVariable Integer userId);
		
}
