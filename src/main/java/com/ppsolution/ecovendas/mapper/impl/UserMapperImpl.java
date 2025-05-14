package com.ppsolution.ecovendas.mapper.impl;

import com.ppsolution.ecovendas.dto.request.UserRequest;
import com.ppsolution.ecovendas.dto.response.UserResponse;
import com.ppsolution.ecovendas.mapper.UserMapper;
import com.ppsolution.ecovendas.model.User;
import com.ppsolution.ecovendas.model.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class UserMapperImpl implements UserMapper {


    @Override
    public User toUser(UserRequest userRequest) {
        if (userRequest == null) return null;
        return User.builder()
                .password(userRequest.password())
                .phone(userRequest.phone())
                .email(userRequest.email())
                .name(userRequest.name())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .role(userRequest.role() == null ? Role.CUSTOMER : userRequest.role())
                .build();
    }


    @Override
    public UserResponse toUserResponse(User user) {
        if (user == null) return null;
        return new UserResponse(user.getName(), user.getEmail(), user.getPassword(), user.getPhone(), user.getRole(), user.getCreatedAt(), user.getUpdatedAt());
    }
}
