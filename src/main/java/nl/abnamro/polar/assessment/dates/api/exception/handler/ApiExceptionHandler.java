package nl.abnamro.polar.assessment.dates.api.exception.handler;

import nl.abnamro.polar.assessment.dates.api.exception.DateFormatException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {DateFormatException.class})
    @ResponseStatus(value = BAD_REQUEST)
    public String dateFormatException(DateFormatException ex, WebRequest request) {
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    public String globalExceptionHandler(Exception ex, WebRequest request) {
        return ex.getMessage();
    }
}
