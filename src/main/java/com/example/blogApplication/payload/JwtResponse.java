package com.example.blogApplication.payload;

import lombok.Builder;

public class JwtResponse {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
