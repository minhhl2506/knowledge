package com.example.knowledge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntityStatus {
	DELETED(-1),

	INACTIVE(0),
	
	NEW(0),

	ACTIVE(1);
	
	private int status;
}
