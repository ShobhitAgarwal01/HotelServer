package com.hotelproject.hotelserver.dto;

import com.hotelproject.hotelserver.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String email;

    private String name;

    private UserRole userRole;
}
