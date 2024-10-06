package com.hotelproject.hotelserver.services.auth;

import com.hotelproject.hotelserver.dto.SignupRequest;
import com.hotelproject.hotelserver.dto.UserDto;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);
}
