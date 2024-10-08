package com.feu.lab4.model;

public class Password {
    private final String password;
    private final String confirmPassword;

    public Password(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
    
    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
