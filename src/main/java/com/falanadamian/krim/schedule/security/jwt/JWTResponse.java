package com.falanadamian.krim.schedule.security.jwt;

import java.util.Objects;

public class JWTResponse {

    private String JWT;
    private static final String type = "Bearer ";

    public JWTResponse(String JWT) {
        this.JWT = JWT;
    }

    public String getJWT() {
        return JWT;
    }

    public void setJWT(String JWT) {
        this.JWT = JWT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JWTResponse that = (JWTResponse) o;
        return Objects.equals(JWT, that.JWT);
    }

    @Override
    public int hashCode() {
        return Objects.hash(JWT);
    }

    @Override
    public String toString() {
        return "JWTResponse{" +
                "JWT='" + JWT + '\'' +
                '}';
    }
}
