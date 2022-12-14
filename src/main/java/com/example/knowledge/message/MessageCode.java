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
	
	MSG1002(LabelKey.ERROR_CAR_NOT_FOUND),
	
	MSG1003(LabelKey.ERROR_CAR_NAME_MAX_LENGTH_IS_INVALID),
	
	MSG1004(LabelKey.ERROR_CAR_NAME_IS_REQUIRED), 
	
	MSG1005(LabelKey.ERROR_USERNAME_IS_REQUIRED), 
	
	MSG1006(LabelKey.ERROR_PASSWORD_IS_REQUIRED), 
	
	MSG1007(LabelKey.USER_DOESNT_EXIST),
	
	MSG1008(LabelKey.ERROR_USERNAME_OR_PASSWORD_INCORRECT)
	
	;
	
	private String key;
}
