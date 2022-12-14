package com.example.knowledge.advice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.knowledge.exception.ErrorMessage;
import com.example.knowledge.label.LabelKey;
import com.example.knowledge.label.Labels;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(value = BadRequestAlertException.class)
	public ResponseEntity<ErrorMessage> handleBadRequestException(BadRequestAlertException ex, WebRequest webRequest) {
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(),
				Labels.getLabels(ex.getMessageCode().getKey()));
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = DecryptErrorException.class)
	public ResponseEntity<ErrorMessage> handleDecryptErrorException(DecryptErrorException ex,
			WebRequest webRequest) {
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(),
				Labels.getLabels(LabelKey.ERROR_CANNOT_DECRYPT_DATA));
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = UnauthorizedException.class)
	public ResponseEntity<ErrorMessage> handleUnauthorizedException(UnauthorizedException ex,
			WebRequest webRequest) {
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), new Date(),
				Labels.getLabels(LabelKey.ERROR_ACCESS_DENIED_EXCEPTION));
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleInvalidArgument(MethodArgumentNotValidException ex) {

		Map<String, String> errorMap = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), Labels.getLabels(error.getDefaultMessage()));
		});

		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), errorMap);

		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(value = AccessDeniedException.class)
	public ResponseEntity<ErrorMessage> handleAccessDeniedException(AccessDeniedException ex) {

		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.FORBIDDEN.value(), new Date(), Labels.getLabels(LabelKey.ERROR_ACCESS_DENIED_EXCEPTION));

		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.FORBIDDEN);

	}
	
	@ExceptionHandler(value = ExpiredJwtException.class)
	public ResponseEntity<ErrorMessage> handleExpiredJwtException(ExpiredJwtException ex) {

		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), new Date(), Labels.getLabels(LabelKey.ERROR_TOKEN_EXPIRED));

		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.UNAUTHORIZED);

	}

}
