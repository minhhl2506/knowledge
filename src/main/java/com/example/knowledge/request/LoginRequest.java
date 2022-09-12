package com.example.knowledge.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRequest implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -5841927409134605576L;

	private String username;
	
	private String password;
	
}
