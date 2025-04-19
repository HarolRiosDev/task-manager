package com.hrv.taskmanager.web.rest;

import com.hrv.taskmanager.service.AuthService;
import com.hrv.taskmanager.service.JwtService;
import com.hrv.taskmanager.service.util.Validation;
import com.hrv.taskmanager.web.rest.api.AuthenticationAPI;
import com.hrv.taskmanager.web.rest.dto.ChangePasswordRequestDTO;
import com.hrv.taskmanager.web.rest.dto.ForgotPasswordRequestDTO;
import com.hrv.taskmanager.web.rest.dto.LoginRequestDTO;
import com.hrv.taskmanager.web.rest.dto.LoginResponseDTO;
import com.hrv.taskmanager.web.rest.dto.RegisterRequestDTO;
import com.hrv.taskmanager.web.rest.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthenticationAPI {

    private final AuthService authService;

    private final JwtService jwtService;

    @Override
    public ResponseEntity<UserDTO> authMe(String authorization) {
        jwtService.validateAndVerifyToken(authorization);
        return ResponseEntity.ok(authService.authMe(authorization));
    }

    @Override
    public ResponseEntity<Void> changePassword(String authorization, ChangePasswordRequestDTO changePasswordRequestDTO) {
        authService.changePassword(authorization, changePasswordRequestDTO);
        return ResponseEntity.noContent().build();
    }


    @Override
    public ResponseEntity<Void> forgotPassword(ForgotPasswordRequestDTO forgotPasswordRequestDTO) {
        authService.forgotPassword(forgotPasswordRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @Override
    public ResponseEntity<Void> register(RegisterRequestDTO registerRequestDTO) {
        Validation.validateRegisterRequest(registerRequestDTO);
        authService.register(registerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
