package com.challenges.addressbook;

public class Contact {
    private static int nextId = 1;
    private int id;
    private String name;
    private String phoneNumber;

    public Contact(String name, String phoneNumber, String emailAddress)
    {
        this.id = nextId++;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

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

    private String emailAddress;




}
