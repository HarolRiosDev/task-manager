package com.hrv.taskmanager.web.rest;

import com.hrv.taskmanager.service.AuthService;
import com.hrv.taskmanager.web.rest.api.AuthenticationAPI;
import com.hrv.taskmanager.web.rest.dto.ChangePasswordRequestDTO;
import com.hrv.taskmanager.web.rest.dto.ForgotPasswordRequestDTO;
import com.hrv.taskmanager.web.rest.dto.LoginRequestDTO;
import com.hrv.taskmanager.web.rest.dto.LoginResponseDTO;
import com.hrv.taskmanager.web.rest.dto.RegisterRequestDTO;
import com.hrv.taskmanager.web.rest.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthenticationAPI {

    private final AuthService authService;


    @Override
    public ResponseEntity<UserDTO> authMe(String authorization) {
        return null;
    }

    @Override
    public ResponseEntity<Void> changePassword(ChangePasswordRequestDTO changePasswordRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<Void> forgotPassword(ForgotPasswordRequestDTO forgotPasswordRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<LoginResponseDTO> login(LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @Override
    public ResponseEntity<Void> register(RegisterRequestDTO registerRequestDTO) {
        authService.register(registerRequestDTO);
        return ResponseEntity.ok().build();
    }
}
