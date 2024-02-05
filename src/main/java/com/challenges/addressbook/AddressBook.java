package com.challenges.addressbook;

import java.util.ArrayList;

public class AddressBook {
    static int nextId = 1;
    protected int id;
    private ArrayList<Contact> contactsList;

    public AddressBook()
    {
        id = nextId++;
        contactsList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Contact> getContactsList() {
        return contactsList;
    }

    public void addContact(Contact contact) throws IllegalArgumentException
    {
        if (contact == null) throw new IllegalArgumentException();
        if (checkDuplicatePhoneNumber(contact)) throw new IllegalArgumentException();
        if (checkDuplicateEmail(contact)) throw new IllegalArgumentException();

        contactsList.add(contact);
    }

//    checkDuplicate refactored into two separate functions: checkDuplicatePhoneNumber() & checkDuplicateEmail()
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
    }

    public boolean removeContact(Contact contact)
    {
        return contactsList.remove(contact);
    }
}
