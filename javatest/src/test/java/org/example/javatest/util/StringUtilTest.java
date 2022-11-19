package org.example.javatest.util;


import junit.framework.TestCase;

public class StringUtilTest extends TestCase {
    public static void main(String[] args) {
        String result = StringUtil.repeat("hola", 3);
        System.out.println(result);
    }
}