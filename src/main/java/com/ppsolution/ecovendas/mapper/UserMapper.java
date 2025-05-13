package com.ppsolution.ecovendas.mapper;

import com.ppsolution.ecovendas.dto.request.UserRequest;
import com.ppsolution.ecovendas.dto.response.UserResponse;
import com.ppsolution.ecovendas.model.User;

public interface UserMapper {

    User toUser(UserRequest userRequest);
    UserResponse toUserResponse(User user);
}
