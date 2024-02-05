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
    public class AddContactTests {

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

        @Nested
        @DisplayName("checkDuplicate tests within addContact")
        public class CheckDuplicateTests {

            Contact duplicateContact;
            @BeforeEach
            public void testInitialisation()
            {
                duplicateContact = mock(Contact.class);

            }
            @Test
            @DisplayName("AddContact should throw IllegalArgumentException when trying to add duplicate phone number")
            public void testAddContactThrowsIllegalArgumentExceptionIfPhoneNumberIsADuplicateOfExistingContact()
            {
                //Arrange
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
                when(contact.getEmailAddress()).thenReturn("test@email.com");
                when(duplicateContact.getEmailAddress()).thenReturn("test@email.com");
                when(contact.getPhoneNumber()).thenReturn("");
                when(duplicateContact.getPhoneNumber()).thenReturn("");
                addressBook.addContact(contact);

                //Act & Assert
                assertThrows(IllegalArgumentException.class, () ->
                        addressBook.addContact(duplicateContact));
            }

            /*When only a single contact detail is passed to a constructor, the other detail will be set to an empty string
             * AddContact should not be allowed to throw an error if the contacts have different emails but empty phone numbers, and vice versa */
            @Test
            @DisplayName("AddContact shouldn't throw IllegalArgumentException if the 'duplicate' contact details are actually empty strings")

            public void testAddContactDoesNotThrowIllegalArgumentExceptionIfDuplicateContactDetailsAreActuallyEmpty()
            {
                //Arrange
                Contact notDuplicateContact = mock(Contact.class);
                when(contact.getPhoneNumber()).thenReturn("12345678910");
                when(notDuplicateContact.getPhoneNumber()).thenReturn("5437678910");
                when(contact.getEmailAddress()).thenReturn("");
                when(notDuplicateContact.getEmailAddress()).thenReturn("");

                //Act
                addressBook.addContact(contact);
                addressBook.addContact(notDuplicateContact);

                //Assert
                assertEquals(2, addressBook.getContactsList().size());
            }
        }


    }

    @Nested
    @DisplayName("RemoveContact Tests")
    public class RemoveContactTests {

        AddressBook addressBook;
        Contact contactToRemove;

        @BeforeEach
        public void testInitialisation()
        {
            addressBook = new AddressBook();
            contactToRemove = mock(Contact.class);
            addressBook.addContact(contactToRemove);
        }

        @Test
        @DisplayName("RemoveContact should decrease ContactsList size() by 1")
        public void testRemoveContactDecreasesContactsListLengthBy1()
        {
            //Arrange
            //Act
            addressBook.removeContact(contactToRemove);
            //Assert
            assertEquals(0, addressBook.getContactsList().size());
        }

        @Test
        @DisplayName("RemoveContact should return true if the contact was successfully removed")
        public void testRemoveContactReturnsTrueIfRemovalWasSuccessful()
        {
            assertTrue(addressBook.removeContact(contactToRemove));
        }

        @Test
        @DisplayName("RemoveContact should return false if the contact could not be found in the list")
        public void testRemoveContactReturnsFalseIfRemovalWasNotSuccessful()
        {
            Contact contact = mock(Contact.class);
            assertFalse(addressBook.removeContact(contact));
        }
    }
}
