package com.assessment.rewardprogram.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.text.MessageFormat;

@AllArgsConstructor
@Getter
public class CustomError {
	
	private static final long serialVersionUID = 1L;
	private String code;
	private String userMessage;
	private String systemMessage;
	
	@Override
	public String toString() {
		return MessageFormat.format("Error (code={0}, userMessage={1}, systemMessage={2})", code, userMessage, systemMessage);
	}
}
