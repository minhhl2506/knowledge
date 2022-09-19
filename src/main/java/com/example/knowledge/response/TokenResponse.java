package com.example.knowledge.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class TokenResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 266221715297793820L;
	
	private String type;

	private String token;

}
