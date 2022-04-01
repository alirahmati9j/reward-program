package com.ali.rewardprogram.errorhandler;

import com.ali.rewardprogram.common.exceptions.RewardException;
import com.ali.rewardprogram.common.exceptions.UserException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class RewardResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(RewardResponseEntityExceptionHandler.class);

    @ExceptionHandler(UserException.class)
    public final ResponseEntity<ErrorResponse> handleUserException(UserException ex, WebRequest request) throws Exception {
        LOGGER.error("Error Type :: {}", UserException.class.getSimpleName());
        LOGGER.error("Handling Error :: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(false, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RewardException.class)
    public final ResponseEntity<ErrorResponse> handleRewardException(RewardException ex, WebRequest request) throws Exception {
        LOGGER.error("Error Type :: {}", RewardException.class.getSimpleName());
        LOGGER.error("Handling Error :: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(false, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

}
