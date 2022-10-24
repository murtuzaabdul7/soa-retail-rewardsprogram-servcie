package com.assessment.rewardprogram.controller;

import com.assessment.rewardprogram.utils.RetailRewardsProgramServiceConstants;
import com.assessment.rewardprogram.error.CustomError;
import com.assessment.rewardprogram.exception.InputValidationException;
import com.assessment.rewardprogram.exception.InvalidInputException;
import com.assessment.rewardprogram.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
@RestController(RetailRewardsProgramServiceConstants.ERROR_MAPPING)
public class RewardPointsDetailsErrorController {

    @PostMapping(RetailRewardsProgramServiceConstants.ERROR_MAPPING)
    @ExceptionHandler(Exception.class)
    public CustomError handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        String systemMessage = ex.toString();
        String userMessage = ex.getMessage();
        log.error("Exception in RewardPointsDetailsDetailsErrorController - {} ", userMessage);
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new CustomError(HttpStatus.INTERNAL_SERVER_ERROR.toString(), userMessage,
                systemMessage);
    }

    @ExceptionHandler(InvalidInputException.class)
    public CustomError inputExceptionHandler(HttpServletRequest request, HttpServletResponse response,
                                             InvalidInputException invalidInputException) {
        CustomError errorData = new CustomError(String.valueOf(invalidInputException.getCode()),
                invalidInputException.getUserMessage(),
                (invalidInputException.getSystemMessage()));
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        String invalidInputExceptionMsg = invalidInputException.toString();
        log.error("InvalidInputException in RewardPointsDetailsDetailsErrorController- {} ", invalidInputExceptionMsg);
        return errorData;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public CustomError resourceNotFoundExceptionHandler(HttpServletRequest request, HttpServletResponse response,
                                                        ResourceNotFoundException resourceNotFoundException) {
        CustomError errorData = new CustomError(String.valueOf(resourceNotFoundException.getCode()),
                resourceNotFoundException.getUserMessage(),
                (resourceNotFoundException.getSystemMessage()));
        response.setStatus(HttpStatus.NO_CONTENT.value());
        String invalidInputExceptionMsg = resourceNotFoundException.toString();
        log.error("ResourceNotFoundException in RewardPointsDetailsDetailsErrorController- {} ", invalidInputExceptionMsg);
        return errorData;
    }

    @ExceptionHandler(InputValidationException.class)
    public CustomError inputValidationException(HttpServletRequest request, HttpServletResponse response,
                                                InputValidationException inputValidationException) {
        CustomError errorData = new CustomError(String.valueOf(inputValidationException.getCode()),
                inputValidationException.getUserMessage(),
                (inputValidationException.getSystemMessage()));
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        String inputValidationExceptionMsg = inputValidationException.toString();
        log.error("InputValidationException in RewardPointsDetailsDetailsErrorController - {} ", inputValidationExceptionMsg);
        return errorData;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public CustomError noHandlerFoundException(HttpServletRequest request,
                                               HttpServletResponse response, NoHandlerFoundException noHandlerFoundException) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        log.error("NoHandlerFoundException in RewardPointsDetailsDetailsErrorController");
        return new CustomError("404", "The resource not found", "The resource not found");
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    public CustomError mediaTypeNotSupportedException(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      HttpMediaTypeNotSupportedException httpRequestMethodNotSupportedException) {
        response.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        log.error("HttpRequestMethodNotSupportedException in RewardPointsDetailsDetailsErrorController");
        return new CustomError("415", "MediaType Not Supported", "MediaType Not Supported");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public CustomError methodNotSupportedException(HttpServletRequest request,
                                                   HttpServletResponse response,
                                                   HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
        response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
        log.error("HttpRequestMethodNotSupportedException in RewardPointsDetailsDetailsErrorController");
        return new CustomError("405", "Method Not Allowed", "Method Not Allowed");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public CustomError httpMessageNotReadableException(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       HttpMessageNotReadableException httpMessageNotReadableException) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        log.error("HttpMessageNotReadableException in RewardPointsDetailsDetailsErrorController");
        return new CustomError("415", "Your request was not correct",
                "Bad Request");
    }

}
