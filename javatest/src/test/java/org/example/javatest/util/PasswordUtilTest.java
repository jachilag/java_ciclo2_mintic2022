package org.example.javatest.util;

import static org.junit.Assert.*;
import static org.example.javatest.util.PasswordUtil.SecurityLevel.*;
import org.junit.Test;

public class PasswordUtilTest {


    @Test
    public void weak_when_has_less_than_8_letters() {
        assertEquals(WEAK, PasswordUtil.assessPassword("1234567"));
    }

    @Test
    public void weak_when_has_only_letters() {
        assertEquals(WEAK, PasswordUtil.assessPassword("abcdefgh"));
    }
   
    @Test
    public void medium_when_has_letters_and_numbers() {
        assertEquals(MEDIUM, PasswordUtil.assessPassword("abcd1234"));
    }

    @Test
    public void medium_when_has_letters_numbers_and_symbols() {
        assertEquals(STRONG, PasswordUtil.assessPassword("abcd1234!"));
    }



}
