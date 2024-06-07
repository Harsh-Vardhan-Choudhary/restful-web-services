package com.harsh.rest.webservices.restful_web_services.exception;

import java.time.LocalDateTime;

public class ErrorDetails
{
    //timestamp
    //message
    //details    

    private LocalDateTime timeStamp;
    private String message;
    private String details;
    
    public ErrorDetails(LocalDateTime timeStamp, String message, String details) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

}
