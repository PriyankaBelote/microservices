package com.techno.employeeservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.techno.employeeservice.dto.ResponseDto;
import com.techno.employeeservice.dto.UserDto;
import com.techno.employeeservice.exception.ResourceNotFoundException;
import com.techno.employeeservice.util.FeignServiceUtil;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/demo")
public class FeignDemoController {

	private static final Logger logger = LoggerFactory.getLogger(FeignDemoController.class);

	@Autowired
	private FeignServiceUtil feignServiceUtil;

	@PostMapping("/register")
	public ResponseEntity<ResponseDto> addData(@RequestBody UserDto userDto) {
		logger.info("add method called");
		return feignServiceUtil.register(userDto);

	}
	
	

	@GetMapping("/getAll")
	@CircuitBreaker(name = "employee-service",fallbackMethod = "responseFallBack")
	public ResponseEntity<ResponseDto> getAllData() {
		logger.info("getAllData method called");
		return feignServiceUtil.getAll();

	}

	public ResponseEntity<ResponseDto> responseFallBack(Exception e) {
		return new ResponseEntity<ResponseDto>(new ResponseDto(true, "server unavailable", null),
				HttpStatus.SERVICE_UNAVAILABLE);
	}
	
	
	
	

	@GetMapping("/getById/{userId}")
	public ResponseEntity<ResponseDto> getAllById(@PathVariable Integer userId) {
		try {
			return feignServiceUtil.getById(userId);
		} catch (Exception e) {
			throw new ResourceNotFoundException("id not found");
		}
	}

	@DeleteMapping("/deleteById/{userId}")
	public ResponseEntity<ResponseDto> deleteById(@PathVariable Integer userId) {

		try {
			return feignServiceUtil.deleteById(userId);
		} catch (Exception e) {

			throw new ResourceNotFoundException("not found");
		}
	}

//	@RateLimiter(name = "employee-service", fallbackMethod = "response")
//	@Bulkhead(name = "employee-service", fallbackMethod = "fallBack")
//	@TimeLimiter(name = "employee-service")
}
