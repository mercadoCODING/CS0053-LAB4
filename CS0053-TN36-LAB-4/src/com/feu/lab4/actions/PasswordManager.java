package com.feu.lab4.actions;

import com.feu.lab4.exceptions.PasswordException;
import com.feu.lab4.model.Password;
import com.feu.lab4.utils.Validate;

import java.util.HashSet;
import java.util.Set;

public class PasswordManager {

    private final static Set<String> usedPasswords = new HashSet<>();

    public boolean addPassword(Password password) throws PasswordException {
        validate(password.getPassword(), password.getConfirmPassword());
        usedPasswords.add(password.getPassword());
        return true;
    }

    public static void validate(String password, String confirmPassword) throws PasswordException {
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            throw new PasswordException("Fill out the fields");
        }

        if (isPasswordUsed(password)) {
            throw new PasswordException("New Password must be different");
        }

        if (!Validate.validatePasswordLength(password)) {
            throw new PasswordException("Password Length must be 8");
        }

        if (!Validate.validatePasswordPattern(password)) {
            throw new PasswordException("Password must contain at least one lowercase, one uppercase, one number, and one special character");
        }

        if (!Validate.validateConfirmPassword(password, confirmPassword)) {
            throw new PasswordException("Password doesn't match");
        }
    }

    private static boolean isPasswordUsed(String password) {
        return usedPasswords.contains(password);
    }
}
