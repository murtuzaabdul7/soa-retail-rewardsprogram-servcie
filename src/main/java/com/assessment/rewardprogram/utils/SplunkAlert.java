package com.assessment.rewardprogram.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Murtuza
 */
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SplunkAlert {
	
	@JsonProperty
	private String errorCode;
	@JsonProperty
	private String errorDescription;
	@JsonProperty
	private String serviceName;
	@JsonProperty
	private String systemMessage;
	@JsonProperty
	private String headerInfo;
	
	public static SplunkAlert generateSplunkAlert(String errorCode, String errorDescription, String serviceName,
												  String systemMessage, String headerInfo) {
		return new SplunkAlert(errorCode, errorDescription, serviceName, systemMessage, headerInfo);
	}
}