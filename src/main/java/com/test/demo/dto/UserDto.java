package com.test.demo.dto;

import java.util.Date;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UserDto {

    private Long dtoId;
    private String dtoName;
    private String dtoEmail;
    private String dtoPhone;
    private String dtoAddress;
    private Date dtoCreatedDate;
}
