package com.hrv.taskmanager.service.util;

import com.hrv.taskmanager.repository.entity.UserEntity;
import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.util.UUID;

@UtilityClass
public class OTP {

    public static String generateSalt() {
        return KeyGenerators.string().generateKey();
    }
}
