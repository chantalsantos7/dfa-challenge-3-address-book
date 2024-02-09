package com.challenges.addressbook;

import com.challenges.helpers.ValidatorHelpers;

public class Contact {
    private static int nextId = 1;
    private int id;
    private String name;
    private String phoneNumber;
    private String emailAddress;

    //Standard constructor for Contact, to fill in all details
    public Contact(String name, String phoneNumber, String emailAddress) throws IllegalArgumentException
    {
        if (ValidatorHelpers.isInputNullOrEmpty(name) || ValidatorHelpers.isInputNullOrEmpty(phoneNumber) || ValidatorHelpers.isInputNullOrEmpty(emailAddress))
        {
            throw new IllegalArgumentException();
        }
        this.id = nextId++;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
