package com.ppsolution.ecovendas.service.impl;

import com.ppsolution.ecovendas.dto.request.UserLoginRequest;
import com.ppsolution.ecovendas.dto.request.UserRequest;
import com.ppsolution.ecovendas.dto.response.LoginResponse;
import com.ppsolution.ecovendas.dto.response.UserResponse;
import com.ppsolution.ecovendas.exception.EmailAlreadyInUseException;
import com.ppsolution.ecovendas.exception.PasswordValidationException;
import com.ppsolution.ecovendas.mapper.UserMapper;
import com.ppsolution.ecovendas.repository.UserRepository;
import com.ppsolution.ecovendas.service.AuthService;
import com.ppsolution.ecovendas.service.token.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthServiceImpl(PasswordEncoder encoder, UserRepository userRepository, UserMapper mapper, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    public UserResponse register(UserRequest userRequest) {
        passwordIsValid(userRequest);
        existsEmailValidation(userRequest);

        var user = mapper.toUser(userRequest);
        user.setPassword(encoder.encode(user.getPassword()));
        user = userRepository.save(user);

        return mapper.toUserResponse(user);
    }


    @Override
    public LoginResponse login(UserLoginRequest loginRequest) {
        var username = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        authenticationManager.authenticate(username);
        var token = tokenService.gerarAccessToken(loginRequest.email());
        var refreshToken = tokenService.gerarRefreshToken(loginRequest.email());

        return new LoginResponse(token, refreshToken);
    }

    private void existsEmailValidation(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.email())){
            throw new EmailAlreadyInUseException();
        }
    }

    private static void passwordIsValid(UserRequest userRequest) {
        if (!userRequest.password().equals(userRequest.passwordConfirmation())){
            throw new PasswordValidationException();
        }
    }
}
