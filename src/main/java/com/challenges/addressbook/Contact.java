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

    //This Constructor can be used when the user can only supply either a phone number or an email for a contact
    //It will set the contact detail that hasn't been supplied to an empty string, but will not allow an empty string to be passed to it as an argument
    public Contact(String name, String contactDetail, ContactDetailType contactDetailType) throws IllegalArgumentException
    {
        if (ValidatorHelpers.isInputNullOrEmpty(name) || ValidatorHelpers.isInputNullOrEmpty(contactDetail)) throw new IllegalArgumentException();
        this.id = nextId++;
        this.name = name;
        switch (contactDetailType)
        {
            case PHONE_NUMBER -> {
                this.phoneNumber = contactDetail;
                this.emailAddress = "";
            }
            case EMAIL_ADDRESS -> {
                this.emailAddress = contactDetail;
                this.phoneNumber = "";
            }
        }
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
