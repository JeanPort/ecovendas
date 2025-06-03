package com.ppsolution.ecovendas.service.impl;

import com.ppsolution.ecovendas.dto.request.UserRequest;
import com.ppsolution.ecovendas.dto.response.UserResponse;
import com.ppsolution.ecovendas.exception.EmailAlreadyInUseException;
import com.ppsolution.ecovendas.exception.UserNotFoundException;
import com.ppsolution.ecovendas.mapper.UserMapper;
import com.ppsolution.ecovendas.repository.UserRepository;
import com.ppsolution.ecovendas.service.UserService;
import com.ppsolution.ecovendas.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponse getUserAuthenticate() {
        var user = SecurityUtil.getUserAuthenticated();
        return userMapper.toUserResponse(user);
    }


    @Override
    public UserResponse findUserById(Long id) {
        var user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteUserById(Long id) {
        var user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    @Override
    public List<UserResponse> findAllUser() {
        var users = userRepository.findAll();
        return users.stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {
        var user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        var userToUpdate = userMapper.toUser(request);
        userToUpdate.setId(user.getId());
        userToUpdate.setCreatedAt(user.getCreatedAt());
        existsId(userToUpdate.getEmail(), userToUpdate.getId());
        userToUpdate = userRepository.save(userToUpdate);
        return userMapper.toUserResponse(userToUpdate);
    }


    @Override
    public UserResponse updateUserAuthenticate(UserRequest request) {
        var user = SecurityUtil.getUserAuthenticated();
        var userToUpdate = userMapper.toUser(request);
        userToUpdate.setId(user.getId());
        userToUpdate.setCreatedAt(user.getCreatedAt());
        existsId(userToUpdate.getEmail(), userToUpdate.getId());
        userToUpdate = userRepository.save(userToUpdate);
        return userMapper.toUserResponse(userToUpdate);
    }


    private void existsId(String email, Long id) {
        if (userRepository.existsByEmailAndIdNot(email, id)){
            throw new EmailAlreadyInUseException();
        }
    }
}
