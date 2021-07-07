package com.lambdaschool.school.handlers;

import com.lambdaschool.school.model.ResourceNotFound;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
//ResponseEntityExceptionHandler makes a HUGE difference...it means that the Override methods I can
    //generate here will all be related to exceptions & edge cases.

    public RestExceptionHandler() {
        super();
    }

    // 0:44:00
    //Line 27, directly below, lists the exceptions that will throw this particular error msg.
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<?> handleResourceNotFoundException(Exception rnfe,
                                                             //the actual request sent from the client
                                                             HttpServletRequest request) {
        ResourceNotFound resourceNotFound = new ResourceNotFound();

        resourceNotFound.setTimestamp(new Date().getTime());
        //Don't need the full HTTP status...just the value.
        resourceNotFound.setStatus(HttpStatus.NOT_FOUND.value());
        resourceNotFound.setTitle("Resource was not found.");
        resourceNotFound.setDetail(rnfe.getMessage());
        resourceNotFound.setDevelopermessage(rnfe.getClass().getName());


        return new ResponseEntity<>(resourceNotFound, null,
                HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResourceNotFound resourceNotFound = new ResourceNotFound();

        resourceNotFound.setTimestamp(new Date().getTime());
        //Don't need the full HTTP status...just the value.
        resourceNotFound.setStatus(HttpStatus.BAD_REQUEST.value());
        resourceNotFound.setTitle(ex.getPropertyName() + " is the incorrect data type. Please try again.");
        resourceNotFound.setDetail(ex.getMessage());
        resourceNotFound.setDevelopermessage(request.getDescription(true));


        return new ResponseEntity<>(resourceNotFound, headers,
                HttpStatus.BAD_REQUEST);
    }


    //Remember...I NEED to turn off Spring's default handler for FoundExceptions
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResourceNotFound resourceNotFound = new ResourceNotFound();

        resourceNotFound.setTimestamp(new Date().getTime());
        //Don't need the full HTTP status ...just the value
        resourceNotFound.setStatus(HttpStatus.NOT_FOUND.value());
        resourceNotFound.setTitle(ex.getRequestURL());
        resourceNotFound.setDetail(request.getDescription(true));
        resourceNotFound.setDevelopermessage("Rest Handler not found, check for valid URL ");


        return new ResponseEntity<>(resourceNotFound, headers,
                HttpStatus.NOT_FOUND);
    }
}
