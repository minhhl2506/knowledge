package com.example.knowledge.advice;

import com.example.knowledge.message.MessageCode;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BadRequestAlertException extends RuntimeException {

	private static final long serialVersionUID = -1948444135162167110L;

	private MessageCode messageCode;

	public BadRequestAlertException(MessageCode messageCode) {
		this.messageCode = messageCode;
	}

}
