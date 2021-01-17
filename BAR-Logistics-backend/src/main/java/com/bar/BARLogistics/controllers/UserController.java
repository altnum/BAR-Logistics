package com.bar.BARLogistics.controllers;

import com.bar.BARLogistics.entities.Capitals;
import com.bar.BARLogistics.models.User;
import com.bar.BARLogistics.payload.request.EditProfileRequest;
import com.bar.BARLogistics.payload.response.MessageResponse;
import com.bar.BARLogistics.repositories.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
    private UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostMapping("/user/editProfile/save")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody EditProfileRequest editProfileRequest) {

        if(!userRepository.findById(Long.valueOf(editProfileRequest.getId())).get().getUsername().equals(editProfileRequest.getUsername())) {
            if (userRepository.existsByUsername(editProfileRequest.getUsername())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Username is already taken!"));
            } else {
                userRepository.editUsername(Long.valueOf(editProfileRequest.getId()), editProfileRequest.getUsername());
            }
        }
        if(!userRepository.findById(Long.valueOf(editProfileRequest.getId())).get().getEmail().equals(editProfileRequest.getEmail())) {
            if (userRepository.existsByEmail(editProfileRequest.getEmail())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Email is already in use!"));
            } else {
                userRepository.editUserEmail(Long.valueOf(editProfileRequest.getId()), editProfileRequest.getEmail());
            }
        }
        userRepository.editUserInformation(Long.valueOf(editProfileRequest.getId()), editProfileRequest.getEmail(), editProfileRequest.getFirst_name(), editProfileRequest.getLast_name());
        return ResponseEntity.ok("User is edited successfully!");
    }
}
