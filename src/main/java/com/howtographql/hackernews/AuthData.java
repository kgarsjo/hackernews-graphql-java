package com.howtographql.hackernews;

public class AuthData {
    private String email;
    private String password;

    public AuthData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public AuthData setEmail(String email) {
        this.email = email;
        return this;
    }

    public AuthData setPassword(String password) {
        this.password = password;
        return this;
    }
}