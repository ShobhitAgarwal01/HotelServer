package com.hotelproject.hotelserver.dto;

import com.hotelproject.hotelserver.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {

    private String jwt;

    private Long userId;

    private UserRole userRole;
}
