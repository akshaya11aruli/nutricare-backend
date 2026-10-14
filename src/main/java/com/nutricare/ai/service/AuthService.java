package com.nutricare.ai.service;

import com.nutricare.ai.config.JwtTokenProvider;
import com.nutricare.ai.dto.AuthRequest;
import com.nutricare.ai.dto.AuthResponse;
import com.nutricare.ai.dto.ChangePasswordRequest;
import com.nutricare.ai.dto.RegisterRequest;
import com.nutricare.ai.entity.User;
import com.nutricare.ai.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Transactional
    public AuthResponse register(RegisterRequest request) {
        String normalizedEmail = request.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmail(normalizedEmail)) {
            return AuthResponse.error("Email is already registered");
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getName().trim(), normalizedEmail, hashedPassword);
        User savedUser = userRepository.save(user);

        String token = jwtTokenProvider.generateToken(savedUser.getId(), savedUser.getEmail());
        AuthResponse.UserInfo userInfo = new AuthResponse.UserInfo(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail()
        );

        return AuthResponse.ok("Registration successful!", token, userInfo);
    }

    @Transactional(readOnly = true)
    public AuthResponse login(AuthRequest request) {
        String normalizedEmail = request.getEmail().trim().toLowerCase();

        User user = userRepository.findByEmail(normalizedEmail).orElse(null);
        if (user == null) {
            return AuthResponse.error("Invalid email or password");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return AuthResponse.error("Invalid email or password");
        }

        String token = jwtTokenProvider.generateToken(user.getId(), user.getEmail());
        AuthResponse.UserInfo userInfo = new AuthResponse.UserInfo(
                user.getId(),
                user.getName(),
                user.getEmail()
        );

        return AuthResponse.ok("Login successful!", token, userInfo);
    }

    @Transactional(readOnly = true)
    public AuthResponse.UserInfo getUserById(Long userId) {
        return userRepository.findById(userId)
                .map(u -> new AuthResponse.UserInfo(u.getId(), u.getName(), u.getEmail()))
                .orElse(null);
    }

    @Transactional
    public AuthResponse changePassword(Long userId, ChangePasswordRequest request) {
        if (userId == null) {
            return AuthResponse.error("User ID is required");
        }
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return AuthResponse.error("User not found");
        }
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            return AuthResponse.error("Current password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        return AuthResponse.ok("Password updated successfully!", null, new AuthResponse.UserInfo(user.getId(), user.getName(), user.getEmail()));
    }
}
