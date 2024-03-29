package com.challenges.addressbook;

import com.challenges.helpers.DisplayHelpers;
import com.challenges.helpers.ValidatorHelpers;

import java.util.ArrayList;
import java.util.function.Function;

public class AddressBook {
    static int nextId = 1;
    protected int id;
    private ArrayList<Contact> contactsList;

    public AddressBook()
    {
        id = nextId++;
        contactsList = new ArrayList<>();
    }

    public ArrayList<Contact> getContactsList() {
        return contactsList;
    }


    /**
     * Adds a contact to the address book's contacts list. The adding will fail if the contact has a duplicate email address or phone number to an existing contact in the address book.
     *
     * @param contact the contact to be added to the list. Should be an instance of Contact.
     * @throws IllegalArgumentException if contact is null, or if contact has a duplicate email address or phone number to an existing contact within the address book.
     * */
    public void addContact(Contact contact) throws IllegalArgumentException
    {
        if (contact == null) throw new IllegalArgumentException();
        if (checkDuplicate(contact, Contact::getEmailAddress)) throw new IllegalArgumentException("This contact has a duplicate email address to an existing contact.");
        if (checkDuplicate(contact, Contact::getPhoneNumber)) throw new IllegalArgumentException("This contact has a duplicate phone number to an existing contact.");

        contactsList.add(contact);
    }

   /* checkDuplicate initially refactored into two separate functions: checkDuplicatePhoneNumber() & checkDuplicateEmail()
   Original Code commented out
   private boolean checkDuplicateEmail(Contact contact)
    {
        for (Contact existingContact : contactsList)
        {
            if (!existingContact.getEmailAddress().isEmpty()
                    && existingContact.getEmailAddress().equals(contact.getEmailAddress())) return true;
        }
        return false;
    }

    private boolean checkDuplicatePhoneNumber(Contact contact)
    {
        for (Contact existingContact : contactsList)
        {
            if (!existingContact.getPhoneNumber().isEmpty()
                    && existingContact.getPhoneNumber().equals(contact.getPhoneNumber())) return true;
        }
        return false;
    }*/

    //checkDuplicate refactored by Copilot from checkDuplicateEmail and checkDuplicatePhoneNumber above
    /**
     * Checks if a given contact has a duplicate in the contact list based on either its email address or phone number.
     *  This Javadoc was generated by GitHub Copilot.
     * @param contact The contact to check for duplicates. Should be an instance of Contact.
     * @param attributeGetter A function that takes a Contact and returns the attribute to check for duplicates.
     * @return true if a duplicate is found, false otherwise.
     */
    private boolean checkDuplicate(Contact contact, Function<Contact, String> attributeGetter) {
        for (Contact existingContact : contactsList) {
            String attribute = attributeGetter.apply(existingContact);
            if (!attribute.isEmpty() && attribute.equals(attributeGetter.apply(contact))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the specified contact from the address book's contact list.
     *
     * @param contact the contact to be removed. Should be an instance of Contact.
     * @return true if removal was successful, false if the contact could not be found (and thus removed).
     * @throws IllegalArgumentException if contact is null.
     * */
    public boolean removeContact(Contact contact) throws IllegalArgumentException
    {
        if (contact == null) throw new IllegalArgumentException();
        return contactsList.remove(contact);
    }

    /**
     * Searches for a contact in the contacts list with the given contact name.
     *
     * @param searchCriteria the name to search for within the address book.
     * @return Contact with name that matches searchCriteria.
     * @throws IllegalArgumentException if searchCriteria is null or empty.
     * * */
    public Contact findContact(String searchCriteria) throws IllegalArgumentException
    {
        if (ValidatorHelpers.isInputNullOrEmpty(searchCriteria)) throw new IllegalArgumentException();
        for (Contact contact : contactsList) {
            if (contact.getName().contains(searchCriteria)) return contact;
        }
        return null;
    }

    /**
     * Searches for a contact in the contacts list with a given attribute, either phone number or email address.
     *
     * @param searchCriteria the contact detail to search for within the address book.
     * @param contactDetailType The type of the contact detail to search for. This should be PHONE_NUMBER or EMAIL_ADDRESS.
     * @return Contact with contact detail that matches searchCriteria.
     * @throws IllegalArgumentException if searchCriteria is null or empty.
     * * */
    public Contact findContact(String searchCriteria, ContactDetailType contactDetailType)
    {
        //code generated by Copilot
        if (ValidatorHelpers.isInputNullOrEmpty(searchCriteria)) throw new IllegalArgumentException();
        for (Contact contact : contactsList)
        {
            switch (contactDetailType)
            {
                case PHONE_NUMBER -> {
                    if (contact.getPhoneNumber().contains(searchCriteria)) return contact;
                }
                case EMAIL_ADDRESS -> {
                    if (contact.getEmailAddress().contains(searchCriteria)) return contact;
                }
            }
        }
        return null;
    }

    /**
     * Returns a string representation of all contacts in the address book.
     * If the address book is empty, it returns a specific message. Otherwise, it uses the DisplayHelpers.formatContactForDisplay
     * method to format each contact for display, and then concatenates these formatted strings together. This Javadoc was generated by GitHub Copilot.
     *
     * @return A string representing all contacts in the address book, or a message indicating that the address book is empty.
     */
    public String viewAllContacts()
    {
        if (contactsList.isEmpty()) return "No contacts in address book";
        StringBuilder allContacts = new StringBuilder();
        for (Contact contact : contactsList)
        {
            allContacts.append(DisplayHelpers.formatContactForDisplay(contact));
        }

        return allContacts.toString();
    }
}
