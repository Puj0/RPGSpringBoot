package com.Puj0.RPGSpringBoot.exception;

import com.Puj0.RPGSpringBoot.view.ErrorView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorView> handleResourceNotFoundException(
            HttpServletRequest request, ResourceNotFoundException exception) {

        log.error(request.getRequestURL() + " threw " + exception.getMessage());
        return new ResponseEntity<>(
                new ErrorView(exception.getMessage(), HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorView> handleMethodArgumentNotValidException(
            HttpServletRequest request, MethodArgumentNotValidException exception) {

        log.error(request.getRequestURL() + " threw " + exception.getMessage());
        return new ResponseEntity<>(
                new ErrorView(exception.getMessage(), HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }
}