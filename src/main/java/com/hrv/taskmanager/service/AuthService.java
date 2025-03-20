package com.hrv.taskmanager.service;

import com.hrv.taskmanager.repository.UserRepository;
import com.hrv.taskmanager.repository.entity.UserEntity;
import com.hrv.taskmanager.service.mapper.UserMapper;
import com.hrv.taskmanager.web.rest.dto.LoginRequestDTO;
import com.hrv.taskmanager.web.rest.dto.LoginResponseDTO;
import com.hrv.taskmanager.web.rest.dto.RegisterRequestDTO;
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
     * @param registerRequestDTO register request data. @see RegisterRequestDTO
     */
    public void register(RegisterRequestDTO registerRequestDTO) {
        UserEntity entity = userMapper.toUserEntity(registerRequestDTO);
        entity.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));
        userRepository.save(entity);
    }

    /**
     * Service method to log in a user
     * @param loginRequestDTO login request data. @see LoginRequestDTO
     * @return login response data. @see LoginResponseDTO
     */
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        UserEntity user = userRepository.findByEmail(loginRequestDTO.getEmail());
        if (user != null && passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            LoginResponseDTO responseDTO = new LoginResponseDTO();
            responseDTO.setToken(jwtService.generateToken(user.getEmail()));
            return responseDTO;
        }
        throw new BadCredentialsException("Invalid credentials");
    }

}
