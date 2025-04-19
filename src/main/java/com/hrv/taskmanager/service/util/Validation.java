package com.hrv.taskmanager.service.util;

import com.hrv.taskmanager.web.rest.dto.RegisterRequestDTO;
import lombok.experimental.UtilityClass;

/**
 * Utility class for validation related operations
 */
@UtilityClass
public class Validation {

    private static final String NAME_REGEX = "^[A-ZÁÉÍÓÚÜÑ]+( [A-ZÁÉÍÓÚÜÑ]+)*";
    /**
     * Validates an email
     * @param email the email to validate
     */
    public static void isValidEmail(String email) {
        if(email != null && !email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")){
            throw new IllegalArgumentException("Invalid email");
        }
    }
    /**
     * Validates a password
     * @param password the password to validate
     */
    public static void isValidPassword(String password) {
        if(password != null && (password.length() < 12 || password.length() > 20) && password.contains("/")){
            throw new IllegalArgumentException("Invalid password");
        }
    }
    /**
     * Validates a name
     * @param name the name to validate
     */
    public static void isValidName(String name) {
        if (name != null && (!name.matches(NAME_REGEX) || name.length() < 2 || name.length() > 50)) {
            throw new IllegalArgumentException("Invalid name");
        }
    }
    /**
     * Validates a surname
     * @param surname the surname to validate
     */
    public static void isValidSurname(String surname) {
        if(surname != null && (!surname.matches(NAME_REGEX) || surname.length() < 2 || surname.length() > 50)){
            throw new IllegalArgumentException("Invalid surname");
        }
    }
    /**
     * Validates a register request
     * @param requestDTO the register request to validate
     */
    public static void validateRegisterRequest(RegisterRequestDTO requestDTO) {
        isValidEmail(requestDTO.getEmail());
        isValidPassword(requestDTO.getPassword());
        isValidName(requestDTO.getName());
        isValidSurname(requestDTO.getSurname());
    }
}
