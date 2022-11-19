package org.example.javatest.util;

public class PasswordUtil {

    public enum SecurityLevel{
        WEAK,MEDIUM,STRONG
    }

    public static SecurityLevel assessPassword(String password){
        SecurityLevel salida = SecurityLevel.STRONG;

        if (password.length()<8) {
            salida = SecurityLevel.WEAK;
        } else if (password.matches("[a-zA-Z]+")) {
            salida = SecurityLevel.WEAK;
        } else if (password.matches("[a-zA-Z0-9]+")) {
            salida =  SecurityLevel.MEDIUM;
        }
        return salida;
    }
}
