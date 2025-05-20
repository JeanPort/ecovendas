package com.ppsolution.ecovendas.controller;

import com.ppsolution.ecovendas.dto.request.UserRequest;
import com.ppsolution.ecovendas.dto.response.UserResponse;
import com.ppsolution.ecovendas.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {

    private final UserService service;

    public UserRestController(UserService service) {
        this.service = service;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getUser(){
        var user = service.getUserAuthenticate();
        return ResponseEntity.ok(user);
    }

    @PutMapping("/profile")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest request){
        var user = service.updateUserAuthenticate(request);
        return ResponseEntity.ok(user);
    }

}
