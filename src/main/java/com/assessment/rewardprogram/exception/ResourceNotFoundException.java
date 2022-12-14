package com.assessment.rewardprogram.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;

@AllArgsConstructor
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {

    private final String code;
    private final String userMessage;
    private final String systemMessage;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return MessageFormat.format("Error (code={0}, userMessage={1}, systemMessage={2})", code, userMessage, systemMessage);
    }
}
