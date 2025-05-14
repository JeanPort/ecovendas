package com.ppsolution.ecovendas.service;

import com.ppsolution.ecovendas.dto.request.UserLoginRequest;
import com.ppsolution.ecovendas.dto.request.UserRequest;
import com.ppsolution.ecovendas.dto.response.LoginResponse;
import com.ppsolution.ecovendas.dto.response.UserResponse;

public interface AuthService {

    UserResponse register(UserRequest userRequest);
    LoginResponse login(UserLoginRequest loginRequest);
}
