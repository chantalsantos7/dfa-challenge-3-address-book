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
}
