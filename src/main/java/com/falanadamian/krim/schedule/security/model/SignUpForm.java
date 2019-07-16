package com.falanadamian.krim.schedule.security.model;

import com.falanadamian.krim.schedule.domain.dto.UserDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class SignUpForm extends UserDTO {

    @NotBlank
    @Size(min = 6, max = 50)
    private String password;

    public SignUpForm(){}

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SignUpForm that = (SignUpForm) o;
        return Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), password);
    }

    @Override
    public String toString() {
        return "SignUpForm{" +
                "password='" + password + '\'' +
                '}';
    }
}
