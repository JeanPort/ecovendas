package com.ppsolution.ecovendas.service;

import com.ppsolution.ecovendas.dto.request.UserRequest;
import com.ppsolution.ecovendas.dto.response.UserResponse;
import com.ppsolution.ecovendas.model.User;

import java.util.List;

public interface UserService {

    UserResponse getUserAuthenticate();
    UserResponse findUserById(Long id);
    void deleteUserById(Long id);
    List<UserResponse> findAllUser();
    UserResponse updateUser(Long id, UserRequest request);
    UserResponse updateUserAuthenticate(UserRequest request);

}
