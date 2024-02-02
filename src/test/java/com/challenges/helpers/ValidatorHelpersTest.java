package com.challenges.helpers;

import com.challenges.addressbook.ContactDetailType;
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

        @Test
        public void testValidateContactInputReturnsTrueIfPhoneContactDetailMatchesPhonePattern()
        {
            String[] validPhoneNumbers = new String[] {
                    "+44 5551 123555", "05552234555", "(0722) 5555555 #2222"
            };

            for (String phoneNumber : validPhoneNumbers)
            {
                assertTrue(ValidatorHelpers.validateContactInput(phoneNumber, ContactDetailType.PHONE_NUMBER));
            }
        }

        @Test
        public void testValidateContactInputReturnsFalseIfPhoneContactDetailsDoNotMatchPhonePattern()
        {
            String[] invalidPhoneNumbers = new String[] {
                    "-44 5551 123555", "0555ewew2234555", "(0722) 5555555555555 #2222", "invalidIsNotANumber"
            };

            for (String phoneNumber : invalidPhoneNumbers)
            {
                assertFalse(ValidatorHelpers.validateContactInput(phoneNumber, ContactDetailType.PHONE_NUMBER));
            }
        }
    }
}
