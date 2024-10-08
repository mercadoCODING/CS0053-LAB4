package com.feu.lab4.utils;

public class Validate {

    public static boolean validatePasswordLength(String password){
        return password.length() == 8;
    }

    public static boolean validateConfirmPassword(String password,String confirmPassword){
        return password.equals(confirmPassword);
    }

    public static boolean validatePasswordPattern(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$";
        return password.matches(regex);
    }
}
