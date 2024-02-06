package com.challenges.addressbook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Nested
    @DisplayName("Contact Constructor Tests")
    class ContactConstructorTests {

        @Test
        public void testConstructorReturnsExpectedValues() {
            //Arrange
            Contact contact = new Contact("Test Contact", "17809955555", "example@email.com");

            //Act
            //Assert
            assertAll(
                    () -> assertEquals(1, contact.getId()),
                    () -> assertEquals("Test Contact", contact.getName()),
                    () -> assertEquals("17809955555", contact.getPhoneNumber()),
                    () -> assertEquals("example@email.com", contact.getEmailAddress())
            );
        }

        @Test
        public void testPhoneNumberConstructorReturnsExpectedValues() {
            Contact contact = new Contact("TestWPhone", "3123555553", ContactDetailType.PHONE_NUMBER);

            assertAll(
                    () -> assertEquals("TestWPhone", contact.getName()),
                    () -> assertEquals("3123555553", contact.getPhoneNumber()),
                    () -> assertEquals("", contact.getEmailAddress())
            );
        }

        @Test
        public void testEmailAddressConstructorReturnsExpectedValues() {
            Contact contact = new Contact("TestWEmail", "testEmail@email.com", ContactDetailType.EMAIL_ADDRESS);
            assertAll(
                    () -> assertEquals("TestWEmail", contact.getName()),
                    () -> assertEquals("testEmail@email.com", contact.getEmailAddress()),
                    () -> assertEquals("", contact.getPhoneNumber())
            );
        }

        @Nested
        @DisplayName("Constructor Throwing IllegalArgumentException Tests")
        class ConstructorInputValidationTests {

            @Test
            @DisplayName("Test that the standard Contact constructor nullOrEmpty checks its arguments")
            /*The constructor doesn't compile if a null value is the last argument, so testing the email address in this unit is unnecessary*/
            public void testRegularContactConstructorThrowsIllegalArgumentExceptionWhenContactDetailsAreNullOrEmpty() {
                assertAll(
                        () -> assertThrows(IllegalArgumentException.class, () ->
                                new Contact(null, "1290340344", "dfdfdfdf")),
                        () -> assertThrows(IllegalArgumentException.class, () ->
                                new Contact("name", null, "dfdfdfdf")),
                        () -> assertThrows(IllegalArgumentException.class, () ->
                                new Contact("", "1290340344", "dfdfdfdf"))
                );

            }

            @Test
            public void testPhoneConstructorThrowsIllegalArgumentExceptionWhenNameOrPhoneNumberIsNullOrEmpty() {
                assertAll(
                        () -> assertThrows(IllegalArgumentException.class, () ->
                                new Contact(null, "not null", ContactDetailType.PHONE_NUMBER)),
                        () -> assertThrows(IllegalArgumentException.class, () ->
                                new Contact("", "12323", ContactDetailType.PHONE_NUMBER)),
                        () -> assertThrows(IllegalArgumentException.class, () ->
                                new Contact("Test", null, ContactDetailType.PHONE_NUMBER)),
                        () -> assertThrows(IllegalArgumentException.class, () ->
                                new Contact("Test", "", ContactDetailType.PHONE_NUMBER))
                );
            }

            @Test
            public void testEmailConstructorThrowsIllegalArgumentExceptionWhenEmailIsNullOrEmpty() {
                assertAll(
                        () -> assertThrows(IllegalArgumentException.class, () ->
                                new Contact(null, "not null", ContactDetailType.EMAIL_ADDRESS)),
                        () -> assertThrows(IllegalArgumentException.class, () ->
                                new Contact("", "not null", ContactDetailType.EMAIL_ADDRESS)),
                        () -> assertThrows(IllegalArgumentException.class, () ->
                                new Contact("Test", null, ContactDetailType.EMAIL_ADDRESS)),
                        () -> assertThrows(IllegalArgumentException.class, () ->
                                new Contact("Test", "", ContactDetailType.EMAIL_ADDRESS))
                );
            }
        }

//
    }

}
