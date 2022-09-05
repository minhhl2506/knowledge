package com.example.knowledge.exception;
import java.util.Date;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ErrorMessage {

	private int statusCode;
	
	private Date timestamp;
	
	private String message;
	
	
	private Map<String, String> listErrors;
	
	public ErrorMessage(int statusCode, Date timestamp, String message, Map<String, String> listErrors) {
		this.statusCode = statusCode;
		this.timestamp = timestamp;
		this.message = message;
		this.listErrors = listErrors;
	}
	
	public ErrorMessage(int statusCode, Date timestamp, String message) {
		this.statusCode = statusCode;
		this.timestamp = timestamp;
		this.message = message;
	}

//	public BadRequestAlertException(int value, Date date, String message, String description) {
//		// TODO Auto-generated constructor stub
//	}

//	@ExceptionHandler(value = BadRequest.class)
//    public ResponseEntity<ErrorMessage> resourceNotFoundException(BadRequest ex, WebRequest webRequest) {
//        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage(),
//                        webRequest.getDescription(false));
//        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
//    }
}
