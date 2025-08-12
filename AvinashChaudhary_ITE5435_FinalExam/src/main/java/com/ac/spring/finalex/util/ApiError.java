package com.ac.spring.finalex.util;

import java.time.LocalDateTime;

public class ApiError {
    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String error;
    private String message;
    private String path;

    public ApiError() {}

    public ApiError(int status, String error, String message, String path) {
        this.status = status; this.error = error; this.message = message; this.path = path;
    }

    public LocalDateTime getTimestamp(){return timestamp;}
    public void setTimestamp(LocalDateTime t){this.timestamp=t;}

    public int getStatus(){return status;}
    public void setStatus(int s){this.status=s;}

    public String getError(){return error;}
    public void setError(String e){this.error=e;}

    public String getMessage(){return message;}
    public void setMessage(String m){this.message=m;}

    public String getPath(){return path;}
    public void setPath(String p){this.path=p;}
}
