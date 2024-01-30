package com.challenges.addressbook;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AddressBookTest {

    @Nested
    @DisplayName("Address Book Constructor Tests")
    class AddressBookConstructorTests {

        @Test
        public void testConstructorReturnsExpectedValues() {
            //Arrange
            AddressBook addressBook = new AddressBook();

            //Act
            //Assert
            assertAll(
                    () -> assertEquals(1, addressBook.getId()),
                    () -> assertEquals(0, addressBook.getContactsList().size())
            );
        }
    }

}
