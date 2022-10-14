package com.assessment.rewardprogram.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

/**
 * @author MBhaiji1
 */

@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"stackTrace", "cause", "localizedMessage", "suppressed", "message"})
public class InvalidHeaderException extends RuntimeException {
	private final String code;
	private final String userMessage;
	private final String systemMessage;
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	public String toString() {
		return MessageFormat.format("Error (code={0}, userMessage={1}, systemMessage={2})", code, userMessage, systemMessage);
	}
}
