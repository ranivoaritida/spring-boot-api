package com.codesand.store.controllers;

import com.codesand.store.dtos.LoginRequest;
import com.codesand.store.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> authentication(@Valid @RequestBody LoginRequest request){
        var user = userRepository.findByEmail(request.getEmail()).orElse(null);
        if(user == null){
            return  ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        /* * if(!user.getEmail().equalsIgnoreCase(request.getEmail())){
            return ResponseEntity.badRequest().body(
                    Map.of("email", "This email doesn't exist.")
            );
        }*/
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok().build();

    }
}
