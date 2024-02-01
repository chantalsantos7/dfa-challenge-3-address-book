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
    }
}
