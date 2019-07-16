package com.falanadamian.krim.schedule.security.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class SignInForm {

    @NotNull
    @Size(min = 1, max = 30)
    private String username;

    @NotNull
    @Size(min = 4, max = 30)
    private String password;

    private Boolean rememberMe;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignInForm signInForm = (SignInForm) o;
        return Objects.equals(username, signInForm.username) &&
                Objects.equals(password, signInForm.password) &&
                Objects.equals(rememberMe, signInForm.rememberMe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, rememberMe);
    }

    @Override
    public String toString() {
        return "SignInForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rememberMe=" + rememberMe +
                '}';
    }
}
