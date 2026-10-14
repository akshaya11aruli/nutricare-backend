package com.nutricare.ai.controller;

import com.nutricare.ai.dto.AuthRequest;
import com.nutricare.ai.dto.AuthResponse;
import com.nutricare.ai.dto.ChangePasswordRequest;
import com.nutricare.ai.dto.RegisterRequest;
import com.nutricare.ai.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @Valid @RequestBody RegisterRequest request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().getFirst().getDefaultMessage();
            return ResponseEntity.badRequest().body(AuthResponse.error(errorMessage));
        }

        AuthResponse response = authService.register(request);
        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody AuthRequest request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().getFirst().getDefaultMessage();
            return ResponseEntity.badRequest().body(AuthResponse.error(errorMessage));
        }

        AuthResponse response = authService.login(request);
        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me/{userId}")
    public ResponseEntity<?> getCurrentUser(@PathVariable Long userId) {
        AuthResponse.UserInfo userInfo = authService.getUserById(userId);
        if (userInfo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(AuthResponse.error("User not found"));
        }
        return ResponseEntity.ok(userInfo);
    }

    @PostMapping("/change-password/{userId}")
    public ResponseEntity<AuthResponse> changePassword(
            @PathVariable Long userId,
            @Valid @RequestBody ChangePasswordRequest request,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = bindingResult.getAllErrors().getFirst().getDefaultMessage();
            return ResponseEntity.badRequest().body(AuthResponse.error(errorMessage));
        }

        AuthResponse response = authService.changePassword(userId, request);
        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
