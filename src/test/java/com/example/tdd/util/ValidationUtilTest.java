package com.example.tdd.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidationUtilTest {

    @Test
    void is_Email_테스트1() {
        //given
        String email = "test@test.com";

        //when
        boolean result = ValidationUtil.isEmail(email);

        //then
        assertTrue(result);
    }

    @Test
    void is_Email_테스트2() {
        //given
        String email = "test.com";

        //when
        boolean result = ValidationUtil.isEmail(email);

        //then
        assertFalse(result);
    }

}