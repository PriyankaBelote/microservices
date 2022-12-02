package com.techno.employeeservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.techno.employeeservice.dto.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ResponseDto> handlerResourceNotFoundException(ResourceNotFoundException ex) {

		String message = ex.getMessage();
		ResponseDto response = ResponseDto.builder().error(true).message(message).build();
	
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
}
