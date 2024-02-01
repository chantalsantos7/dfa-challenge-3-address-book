package com.challenges.helpers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidatorHelpersTest {

    @Nested
    @DisplayName("isInputNullOrEmpty")
    public class IsInputNullOrEmptyTests
    {
        @Test
        public void testIsInputNullOrEmptyReturnsTrueWhenInputIsNull()
        {
            assertEquals(true, ValidatorHelpers.isInputNullOrEmpty(null));
        }

        @Test
        public void testIsInputNullOrEmptyReturnsTrueWhenInputIsEmpty()
        {
            assertEquals(true, ValidatorHelpers.isInputNullOrEmpty(""));
        }

        @Test
        public void testIsInputNullOrEmptyReturnsTrueWhenInputIsWhitespace()
        {
            assertAll(
                    () ->  assertEquals(true, ValidatorHelpers.isInputNullOrEmpty(" ")),
                    () ->  assertEquals(true, ValidatorHelpers.isInputNullOrEmpty("    "))
            );
        }

        @Test
        public void testIsInputNullOrEmptyReturnsFalseWhenInputIsValidString()
        {
            assertEquals(false, ValidatorHelpers.isInputNullOrEmpty("valid"));
        }


    }

}
