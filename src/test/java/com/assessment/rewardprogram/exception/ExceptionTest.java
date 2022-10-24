package com.assessment.rewardprogram.exception;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author MBhaiji1
 */
public class ExceptionTest {

    @Test
    public void inputValidationExceptionTest() {
        InputValidationException inputValidationException = new InputValidationException("ER001",
                "Missing mandatory attributes",
                "Invalid input key:customerIds value: null");
        assertNotNull(inputValidationException.toString());
        assertNotNull(inputValidationException.getSystemMessage());
        assertNotNull(inputValidationException.getCode());
        assertNotNull(inputValidationException.getUserMessage());
    }

    @Test
    public void invalidInputExceptionTest() {
        InvalidInputException invalidInputException = new InvalidInputException("400", "Bad Request", "Bad Request");
        assertNotNull(invalidInputException.toString());
        assertNotNull(invalidInputException.getSystemMessage());
        assertNotNull(invalidInputException.getCode());
        assertNotNull(invalidInputException.getUserMessage());
    }

    @Test
    public void invalidHeaderExceptionTest() {
        InvalidHeaderException invalidHeaderException = new InvalidHeaderException("400", "Bad Request", "Bad Request");
        assertNotNull(invalidHeaderException.toString());
        assertNotNull(invalidHeaderException.getSystemMessage());
        assertNotNull(invalidHeaderException.getCode());
        assertNotNull(invalidHeaderException.getUserMessage());
    }


    @Test
    public void resourceNotFoundExceptionTest() {
        ResourceNotFoundException resourceNotFoundException = new ResourceNotFoundException("404", "Data not found", "Data not found");
        assertNotNull(resourceNotFoundException.toString());
        assertNotNull(resourceNotFoundException.getSystemMessage());
        assertNotNull(resourceNotFoundException.getCode());
        assertNotNull(resourceNotFoundException.getUserMessage());
    }

}
