package com.atz.pmd.security;

public class AuthenticationBean {

    @Override
    public String toString() {
        return "ProjectBean{" +
                "message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message="";

    public AuthenticationBean(String message){
        this.message= message;
    }
}
