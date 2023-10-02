package com.assessment.rewardprogram.controller;


import com.assessment.rewardprogram.error.CustomError;
import com.assessment.rewardprogram.exception.InputValidationException;
import com.assessment.rewardprogram.exception.InvalidInputException;
import com.assessment.rewardprogram.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(MockitoExtension.class)
class RewardPointsDetailsErrorControllerTest {

    @InjectMocks
    private RewardPointsDetailsErrorController pointsDetailsErrorController;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpHeaders headers;

    @Test
    void handleExceptionTest() {
        CustomError errorResponse = pointsDetailsErrorController.handleException(request, response,
                new Exception("Some generic exception message"));
        log.info("errorResponse: " + errorResponse);
        assertEquals("500 INTERNAL_SERVER_ERROR", errorResponse.getCode());
        assertEquals("java.lang.Exception: Some generic exception message", errorResponse.getSystemMessage());
        assertEquals("Some generic exception message", errorResponse.getUserMessage());
    }

    @Test
    void inputExceptionHandlerTest() {
        CustomError errorResponse = pointsDetailsErrorController.inputExceptionHandler(request, response,
                new InvalidInputException("400", "Some InvalidInputException message",
                        "Some InvalidInputException message"));
        log.info("errorResponse: " + errorResponse);
        assertEquals("400", errorResponse.getCode());
        assertEquals("Some InvalidInputException message", errorResponse.getUserMessage());
        assertEquals("Some InvalidInputException message", errorResponse.getSystemMessage());
    }

    @Test
    void resourceNotFoundExceptionHandlerTest() {
        CustomError errorResponse = pointsDetailsErrorController.resourceNotFoundExceptionHandler(request, response,
                new ResourceNotFoundException("204", "Some ResourceNotFoundException message",
                        "Some ResourceNotFoundException message"));
        log.info("errorResponse: " + errorResponse);
        assertEquals("204", errorResponse.getCode());
        assertEquals("Some ResourceNotFoundException message", errorResponse.getUserMessage());
        assertEquals("Some ResourceNotFoundException message", errorResponse.getSystemMessage());
    }

    @Test
    void inputValidationExceptionTest() {
        CustomError errorResponse = pointsDetailsErrorController.inputValidationException(request, response,
                new InputValidationException("400", "Some InputValidationException message",
                        "Some InputValidationException message"));
        log.info("errorResponse: " + errorResponse);
        assertEquals("400", errorResponse.getCode());
        assertEquals("Some InputValidationException message", errorResponse.getUserMessage());
        assertEquals("Some InputValidationException message", errorResponse.getSystemMessage());
    }

    @Test
    void noHandlerFoundExceptionTest() {
        CustomError errorResponse = pointsDetailsErrorController.noHandlerFoundException(request, response,
                new NoHandlerFoundException("404", "The resource not found", headers));
        log.info("errorResponse: " + errorResponse);
        assertEquals("404", errorResponse.getCode());
        assertEquals("The resource not found", errorResponse.getUserMessage());
        assertEquals("The resource not found", errorResponse.getSystemMessage());
    }

    @Test
    void mediaTypeNotSupportedExceptionTest() {
        CustomError errorResponse = pointsDetailsErrorController.mediaTypeNotSupportedException(request, response,
                new HttpMediaTypeNotSupportedException("MediaType Not Supported"));
        log.info("errorResponse: " + errorResponse);
        assertEquals("415", errorResponse.getCode());
        assertEquals("MediaType Not Supported", errorResponse.getUserMessage());
        assertEquals("MediaType Not Supported", errorResponse.getSystemMessage());
    }

    @Test
    void methodNotSupportedExceptionTest() {
        CustomError errorResponse = pointsDetailsErrorController.methodNotSupportedException(request, response,
                new HttpRequestMethodNotSupportedException("Method Not Allowed"));
        log.info("errorResponse: " + errorResponse);
        assertEquals("405", errorResponse.getCode());
        assertEquals("Method Not Allowed", errorResponse.getUserMessage());
        assertEquals("Method Not Allowed", errorResponse.getSystemMessage());
    }


    @Test
    void httpMessageNotReadableExceptionTest() {
        CustomError errorResponse = pointsDetailsErrorController.httpMessageNotReadableException(request, response,
                new HttpMessageNotReadableException("MediaType Not Supported"));
        log.info("errorResponse: " + errorResponse);
        assertEquals("415", errorResponse.getCode());
        assertEquals("Your request was not correct", errorResponse.getUserMessage());
        assertEquals("Bad Request", errorResponse.getSystemMessage());
    }

}
