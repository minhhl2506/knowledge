package com.example.knowledge.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DecryptDataRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5817328909524333447L;
	
	private String data;

}
