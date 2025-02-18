package com.test.demo.form.user;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateUserForm {

    @NotNull(message = "Name cannot be null")
    private String createUserName;

    @NotNull(message = "Phone cannot be null")
    private String createUserPhone;

    @NotNull(message = "Email cannot be null")
    private String createUserEmail;

    @NotNull(message = "Address cannot be null")
    private String createUserAddress;
}
