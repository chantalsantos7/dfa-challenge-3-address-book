package com.challenges.addressbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


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
                    () -> assertEquals(0, addressBook.getContactsList().size())
            );
        }
    }

    @Nested
    @DisplayName("AddContact Tests")
    class AddContactTests {

        private AddressBook addressBook;
        private Contact contact;

        @BeforeEach
        public void testInitialisation()
        {
            addressBook = new AddressBook();
            contact = mock(Contact.class);
        }

        @Test
        @DisplayName("Add Contact should increase addressBook.Contacts.length by 1")
        public void testAddContactIncreasesContactsListLengthBy1()
        {
            addressBook.addContact(contact);
            assertEquals(1, addressBook.getContactsList().size());
        }

        @Test
        public void testAddContactThrowsIllegalArgumentExceptionIfContactIsNull()
        {
           contact = null;
           assertThrows(IllegalArgumentException.class, () ->
                   addressBook.addContact(contact));
        }

        @Test
        @DisplayName("AddContact should throw IllegalArgumentException when trying to add duplicate phone number")
        public void testAddContactThrowsIllegalArgumentExceptionIfPhoneNumberIsADuplicateOfExistingContact()
        {
            //Arrange
            Contact duplicateContact = mock(Contact.class);
            when(contact.getPhoneNumber()).thenReturn("12345678910");
            when(duplicateContact.getPhoneNumber()).thenReturn("12345678910");
            when(contact.getEmailAddress()).thenReturn("");
            when(duplicateContact.getEmailAddress()).thenReturn("");
            addressBook.addContact(contact);

            //Act & Assert
            assertThrows(IllegalArgumentException.class, () ->
                    addressBook.addContact(duplicateContact));

        }

        @Test
        @DisplayName("AddContact should throw IllegalArgumentException when trying to add duplicate email address")
        public void testAddContactDoesNotAddContactIfEmailAddressIsADuplicateOfExistingContact()
        {
            //Arrange
            Contact duplicateContact = mock(Contact.class);
            when(contact.getEmailAddress()).thenReturn("test@email.com");
            when(duplicateContact.getEmailAddress()).thenReturn("test@email.com");
            when(contact.getPhoneNumber()).thenReturn("");
            when(duplicateContact.getPhoneNumber()).thenReturn("");
            addressBook.addContact(contact);

            //Act & Assert
            assertThrows(IllegalArgumentException.class, () ->
                    addressBook.addContact(duplicateContact));
        }
    }
}
