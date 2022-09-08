package com.example.knowledge.advice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.knowledge.exception.ErrorMessage;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = BadRequestAlertException.class)
	public ResponseEntity<ErrorMessage> badRequestException(BadRequestAlertException ex, WebRequest webRequest) {
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(),
				ex.getMessageCode().getKey());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleInvalidArgument(MethodArgumentNotValidException ex) {
        
		Map<String, String> errorMap = new HashMap<>();
        
		ex.getBindingResult().getFieldErrors().forEach(error -> { 
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
		
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), errorMap);
		
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
        
    }

}
