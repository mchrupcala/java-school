package com.lambdaschool.school.handlers;

import com.lambdaschool.school.model.ResourceNotFound;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    public RestExceptionHandler() {
        super();
    }

    // 0:44:00
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

}
