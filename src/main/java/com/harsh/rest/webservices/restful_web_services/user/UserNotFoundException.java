package com.harsh.rest.webservices.restful_web_services.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)            //this will show status 404
public class UserNotFoundException extends RuntimeException 
{
    public UserNotFoundException(String message)
    {
        super(message);               //this will display UserNotFoundException -{id}
    }
}
