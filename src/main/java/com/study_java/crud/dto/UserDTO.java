package com.study_java.crud.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String email;
    private String phone;
    private String address;
    private String fullName;
    private boolean isActive;
}
