package com.coforge.training.agribid.farmer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	//private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
		@ExceptionHandler(ResourceNotFound.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFound ex) {
	    	//logger.error("Resource not found: {}", ex.getMessage());
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	    }

}
