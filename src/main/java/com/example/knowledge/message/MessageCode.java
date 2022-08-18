package com.example.knowledge.message;

import com.example.knowledge.label.LabelKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum MessageCode {
	
	MSG1001("Id cannot be null"),
	MSG1002(LabelKey.ERROR_CAR_NOT_FOUND);
	
	private String key;
}
