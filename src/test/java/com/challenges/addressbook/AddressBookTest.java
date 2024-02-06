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

        @Test
        @DisplayName("RemoveContact should throw an IllegalArgumentException if its contact is null")
        public void testRemoveContactThrowsIllegalArgumentExceptionIfContactIsNull()
        {
            assertThrows(IllegalArgumentException.class, () ->
                    addressBook.removeContact(null));
        }

        @Test
        @DisplayName("RemoveContact should return false if it tries to remove a contact from an empty contactsList")
        public void testRemoveContactReturnsFalseIfContactsListWasAlreadyEmpty()
        {
            AddressBook newAddressBook = new AddressBook();
            assertFalse(newAddressBook.removeContact(contactToRemove));
        }
    }

    @Nested
    @DisplayName("FindContact Tests")
    public class FindContactTests {

        AddressBook addressBook;
        Contact[] contactsToFind;

        @BeforeEach
        public void testInitialisation()
        {
            addressBook = new AddressBook();
            contactsToFind = new Contact[5];

            for (int i = 0; i < contactsToFind.length; i++)
            {
                contactsToFind[i] = mock(Contact.class);
                when(contactsToFind[i].getName()).thenReturn("mockContact" + i);
                when(contactsToFind[i].getEmailAddress()).thenReturn("mock" + i + "@email.com");
                when(contactsToFind[i].getPhoneNumber()).thenReturn(i + "5555678910");
                addressBook.addContact(contactsToFind[i]);
            }
        }

        @Test
        @DisplayName("FindContact should return the first Contact with a name that correctly matches the search criteria")
        public void testFindContactReturnsContactWithNameMatchingSearchCriteria()
        {
            for (Contact contact : contactsToFind)
            {
                assertEquals(contact, addressBook.findContact(contact.getName()));
            }
        }

        @Test
        @DisplayName("FindContact should return null if it can't find a contact that matches the search criteria")
        public void testFindContactReturnsNullIfItCannotFindMatchingContact()
        {
            assertNull(addressBook.findContact("not in list"));
        }

        @Test
        @DisplayName("FindContact should return the first Contact with an email that correctly matches the search criteria")
        public void testFindContactReturnsContactWithEmailMatchingSearchCriteria()
        {
            for (Contact contact : contactsToFind)
            {
                assertEquals(contact, addressBook.findContact(contact.getEmailAddress(), ContactDetailType.EMAIL_ADDRESS));
            }
        }

        @Test
        @DisplayName("FindContact should return the first Contact with a phone number that correctly matches the search criteria")
        public void testFindContactReturnsContactWithPhoneNumberMatchingSearchCriteria()
        {
            for (Contact contact : contactsToFind)
            {
                assertEquals(contact, addressBook.findContact(contact.getPhoneNumber(), ContactDetailType.PHONE_NUMBER));
            }
        }

        @Test
        @DisplayName("FindContact should return null if it can't find a contact that matches the search criteria")
        public void testFindContactReturnsNullIfItCannotFindMatchingEmailOrPhoneNumber()
        {
            //Code generated by Copilot
            assertNull(addressBook.findContact("not in list", ContactDetailType.EMAIL_ADDRESS));
            assertNull(addressBook.findContact("not in list", ContactDetailType.PHONE_NUMBER));
        }
    }
}
