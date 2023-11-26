package com.fenrir.auth.service;

import com.fenrir.auth.dto.request.ExchangeRequest;
import com.fenrir.auth.dto.request.RegisterRequest;
import com.fenrir.auth.entity.RoleEntity;
import com.fenrir.auth.entity.UserEntity;
import com.fenrir.auth.enums.Role;
import com.fenrir.auth.exception.exceptions.DuplicateCredentialsException;
import com.fenrir.auth.exception.exceptions.PasswordMismatchException;
import com.fenrir.auth.exception.exceptions.WrongCredentialsException;
import com.fenrir.auth.repository.RoleRepository;
import com.fenrir.auth.repository.UserRepository;
import com.fenrir.auth.security.AuthenticationFacade;
import com.fenrir.auth.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StandardAuthService {
    private static final String AUTH_TYPE_STANDARD = "STANDARD";

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationFacade authenticationFacade;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Transactional
    public void registerUser(RegisterRequest registerRequest) {
        authenticationFacade.assertNotAuthenticated();
        assertCredentialsValid(registerRequest);
        UserEntity user = fromRegisterRequest(registerRequest);
        userRepository.saveAndFlush(user);
    }

    private void assertCredentialsValid(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new DuplicateCredentialsException();
        }
        if (!registerRequest.getPassword().equals(registerRequest.getPasswordRepeat())) {
            throw new PasswordMismatchException();
        }
    }

    private UserEntity fromRegisterRequest(RegisterRequest registerRequest) {
        final UserEntity user = new UserEntity();
        user.setAuthType(AUTH_TYPE_STANDARD);
        user.setName(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(getUserRole());
        return user;
    }

    private RoleEntity getUserRole() {
        return roleRepository.getByName(Role.VIEWER.getName());
    }

    @Transactional
    public String exchangeCredentials(ExchangeRequest exchangeRequest) {
        Optional<UserEntity> user = userRepository.findByEmail(exchangeRequest.getEmail());
        if (user.isEmpty() || !isPasswordCorrect(exchangeRequest, user.get())) {
            throw new WrongCredentialsException();
        }
        return jwtUtils.generateAccessToken(user.get());
    }

    private boolean isPasswordCorrect(ExchangeRequest exchangeRequest, UserEntity user) {
        return passwordEncoder.matches(exchangeRequest.getPassword(), user.getPassword());
    }
}
