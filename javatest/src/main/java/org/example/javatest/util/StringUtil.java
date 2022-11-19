package org.example.javatest.util;


public class StringUtil {

    public static String repeat(String cadena, int times) {
        String result = "";
        for (int i = 0; i < times; ++i) {
            result += cadena;
        }
        return result;
    }

}

