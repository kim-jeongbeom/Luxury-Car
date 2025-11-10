package com.example.autoversebackend.auth.service;

import com.example.autoversebackend.auth.dto.AuthResponse;
import com.example.autoversebackend.auth.dto.LoginRequest;
import com.example.autoversebackend.auth.dto.SignupRequest;
import com.example.autoversebackend.security.JwtUtil;
import com.example.autoversebackend.user.entity.Role;
import com.example.autoversebackend.user.entity.UserEntity;
import com.example.autoversebackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse signup(SignupRequest request) {
        // Check if userId already exists
        if (userRepository.existsByUserId(request.getUserId())) {
            throw new RuntimeException("아이디가 이미 존재합니다");
        }

        // Check if nickname already exists
        if (userRepository.existsByNickname(request.getNickname())) {
            throw new RuntimeException("닉네임이 이미 존재합니다");
        }

        // Create new user
        UserEntity user = UserEntity.builder()
                .userId(request.getUserId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .nickname(request.getNickname())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        // Generate token
        String token = jwtUtil.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .userId(user.getUserId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .nickname(user.getNickname())
                .role(user.getRole().name())
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserId(),
                        request.getPassword()
                )
        );

        UserEntity user = (UserEntity) authentication.getPrincipal();

        // Generate token
        String token = jwtUtil.generateToken(user);

        return AuthResponse.builder()
                .token(token)
                .userId(user.getUserId())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .nickname(user.getNickname())
                .role(user.getRole().name())
                .build();
    }
}
