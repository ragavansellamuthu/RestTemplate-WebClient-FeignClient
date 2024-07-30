package com.techietact.tutorial.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.techietact.tutorial.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception e) {		
		ErrorResponse response = new ErrorResponse();		
		HttpStatusCode statusCode ;
		if (e instanceof HttpClientErrorException) {
			HttpClientErrorException clientException = (HttpClientErrorException) e;
			response.setErrorMessage(clientException.getMessage());
			statusCode = clientException.getStatusCode();
		} else if (e instanceof HttpServerErrorException) {
			HttpServerErrorException serverException = (HttpServerErrorException) e;
			response.setErrorMessage(serverException.getMessage());
			statusCode = serverException.getStatusCode();
		} else {
			response.setErrorMessage(e.getMessage());
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(response,statusCode);
	}

}
