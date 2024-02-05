package com.challenges.addressbook;

public class Contact {
    private static int nextId = 1;
    private int id;
    private String name;
    private String phoneNumber;
    private String emailAddress;

    //Standard constructor for Contact, to fill in all details
    public Contact(String name, String phoneNumber, String emailAddress)
    {
        this.id = nextId++;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    //This Constructor can be used when the user can only supply either a phone number or an email for a contact
    public Contact(String name, String contactDetail, ContactDetailType contactDetailType)
    {
        this.id = nextId++;
        this.name = name;
        switch (contactDetailType)
        {
            case PHONE_NUMBER -> this.phoneNumber = contactDetail;
            case EMAIL_ADDRESS -> this.emailAddress = contactDetail;
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

//    public boolean validatePhoneNumber() throws IllegalArgumentException
//    {
//        return false;
//    }
//
//    public boolean validateEmailAddress()
//    {
//        return false;
//    }





}
