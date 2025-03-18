package com.hrv.taskmanager.service;

import com.hrv.taskmanager.repository.UserRepository;
import com.hrv.taskmanager.repository.entity.UserEntity;
import com.hrv.taskmanager.service.mapper.UserMapper;
import com.hrv.taskmanager.service.util.OTP;
import com.hrv.taskmanager.web.rest.dto.RegisterRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;
    /**
     * Service method to register a new user
     */
    public void register(RegisterRequestDTO registerRequestDTO) {
        UserEntity entity = userMapper.toUserEntity(registerRequestDTO);
        entity.setSeed(OTP.generateSalt());
    }

}
