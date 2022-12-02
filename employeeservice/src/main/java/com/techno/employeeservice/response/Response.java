package com.techno.employeeservice.response;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response implements Serializable {

	private String message;

	private Object data;

}
