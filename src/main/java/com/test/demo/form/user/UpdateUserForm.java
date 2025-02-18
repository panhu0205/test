package com.test.demo.form.user;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UpdateUserForm {

    @NotNull(message = "Name cannot be null")
    private String updateUserName;

    @NotNull(message = "Phone cannot be null")
    private String updateUserPhone;

    @NotNull(message = "Email cannot be null")
    private String updateUserEmail;

    @NotNull(message = "Address cannot be null")
    private String updateUserAddress;
}
