package com.example.knowledge.advice;

import com.example.knowledge.label.LabelKey;
import com.example.knowledge.label.Labels;

import lombok.Getter;

@Getter
public class UnauthorizedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -575802147652849853L;
    
    public UnauthorizedException() {
        super(Labels.getLabels(LabelKey.ERROR_CANNOT_DECRYPT_DATA));
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable t) {
        super(message, t);
    }
	
}
