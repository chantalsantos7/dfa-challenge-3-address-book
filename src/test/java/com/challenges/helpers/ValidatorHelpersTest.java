package com.challenges.helpers;

import com.challenges.addressbook.ContactDetailType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorHelpersTest {

    @Nested
    @DisplayName("isInputNullOrEmpty Tests")
    public class IsInputNullOrEmptyTests
    {
        @Test
        public void testIsInputNullOrEmptyReturnsTrueWhenInputIsNull()
        {
            assertTrue(ValidatorHelpers.isInputNullOrEmpty(null));
        }

        @Test
        public void testIsInputNullOrEmptyReturnsTrueWhenInputIsEmpty()
        {
            assertTrue(ValidatorHelpers.isInputNullOrEmpty(""));
        }

        @Test
        public void testIsInputNullOrEmptyReturnsTrueWhenInputIsWhitespace()
        {
            assertAll(
                    () -> assertTrue(ValidatorHelpers.isInputNullOrEmpty(" ")),
                    () -> assertTrue(ValidatorHelpers.isInputNullOrEmpty("    "))
            );
        }

        @Test
        public void testIsInputNullOrEmptyReturnsFalseWhenInputIsValidString()
        {
            assertFalse(ValidatorHelpers.isInputNullOrEmpty("valid"));
        }
    }

    @Nested
    @DisplayName("validateContactInput Tests")
    public class ValidateContactInputTests {

        @Test
        public void testValidateContactInputThrowsIllegalArgumentExceptionWhenContactDetailIsNull()
        {
            assertThrows(IllegalArgumentException.class, () -> ValidatorHelpers.validateContactInput(null, ContactDetailType.PHONE_NUMBER));
        }


    }
}
