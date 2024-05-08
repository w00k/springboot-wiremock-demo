package com.javaschool.demo.exception.interceptor;

import com.google.gson.Gson;
import com.javaschool.demo.exception.CommunicationException;
import com.javaschool.demo.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class RickAndMortyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public final ResponseEntity<Object> handleObjectNotFoundException(ObjectNotFoundException ex, WebRequest request) {
        RickAndMortyExceptionResponse exceptionResponse = new RickAndMortyExceptionResponse(new Date(),HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(), request.getDescription(false));
        RickAndMortyExceptionResponse result = null;
        try {
            result = new Gson().fromJson(ex.getMessage(), RickAndMortyExceptionResponse.class) ;
        } catch (Exception e) {
            result= null;
        }
        return new ResponseEntity<Object>(result==null?exceptionResponse:result, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CommunicationException.class)
    public final ResponseEntity<Object> handleMessageException(CommunicationException ex, WebRequest request) {
        RickAndMortyExceptionResponse exceptionResponse = new RickAndMortyExceptionResponse(new Date(),HttpStatus.BAD_GATEWAY.value(), HttpStatus.BAD_GATEWAY.getReasonPhrase(),
                ex.getMessage(), request.getDescription(false));

        RickAndMortyExceptionResponse result = null;
        try {
            result = new Gson().fromJson(ex.getMessage(), RickAndMortyExceptionResponse.class) ;
        } catch (Exception e) {
            result= null;
        }

        return new ResponseEntity<Object>(result==null?exceptionResponse:result, HttpStatus.BAD_GATEWAY);
    }
}
