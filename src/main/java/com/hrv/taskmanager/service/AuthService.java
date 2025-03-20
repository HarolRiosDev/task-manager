package com.hrv.taskmanager.service;

import com.hrv.taskmanager.repository.UserRepository;
import com.hrv.taskmanager.repository.entity.UserEntity;
import com.hrv.taskmanager.service.mapper.UserMapper;
import com.hrv.taskmanager.web.rest.dto.ChangePasswordRequestDTO;
import com.hrv.taskmanager.web.rest.dto.ForgotPasswordRequestDTO;
import com.hrv.taskmanager.web.rest.dto.LoginRequestDTO;
import com.hrv.taskmanager.web.rest.dto.LoginResponseDTO;
import com.hrv.taskmanager.web.rest.dto.RegisterRequestDTO;
import com.hrv.taskmanager.web.rest.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for authentication related operations
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    /**** Dependencies ***/
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    /**
     * Service method to register a new user
     * @param requestDTO register request data. @see RegisterRequestDTO
     */
    public void register(RegisterRequestDTO requestDTO) {
        UserEntity entity = userMapper.toUserEntity(requestDTO);
        entity.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        userRepository.save(entity);
    }

    /**
     * Service method to log in a user
     * @param loginRequestDTO login request data. @see LoginRequestDTO
     * @return login response data. @see LoginResponseDTO
     */
    public LoginResponseDTO login(LoginRequestDTO requestDTO) {
        UserEntity user = userRepository.findByEmail(requestDTO.getEmail());
        if (user != null && passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
            LoginResponseDTO responseDTO = new LoginResponseDTO();
            responseDTO.setToken(jwtService.generateToken(user.getEmail()));
            return responseDTO;
        }
        throw new BadCredentialsException("Invalid credentials");
    }

    /**
     * Service method to authenticate a user
     * @param authorization the authorization header
     * @return the authenticated user data. @see UserDTO
     */
    public UserDTO authMe(String authorization) {
        String token = jwtService.getRawToken(authorization);
        String email = jwtService.extractEmail(token);
        UserEntity user = userRepository.findByEmail(email);
        return userMapper.toUserDTO(user);
    }

    /**
     * Service method to change the password of a user
     * @param authorization the authorization header
     * @param requestDTO the change password request data. @see ChangePasswordRequestDTO
     */
    public void changePassword(String authorization, ChangePasswordRequestDTO requestDTO) {
        String token = jwtService.getRawToken(authorization);
        String email = jwtService.extractEmail(token);
        UserEntity user = userRepository.findByEmail(email);
        if (passwordEncoder.matches(requestDTO.getPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(requestDTO.getNewPassword()));
            userRepository.save(user);
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }

    public void forgotPassword(ForgotPasswordRequestDTO requestDTO) {
        UserEntity user = userRepository.findByEmailAndName(requestDTO.getEmail(), requestDTO.getName());
        if (user != null) {
            // send email with password reset link
        }
    }

}
