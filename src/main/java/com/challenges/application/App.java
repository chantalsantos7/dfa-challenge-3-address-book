package com.challenges.application;

import com.challenges.addressbook.AddressBook;
import com.challenges.addressbook.Contact;
import com.challenges.addressbook.ContactDetailType;
import com.challenges.helpers.DisplayHelpers;
import com.challenges.helpers.ValidatorHelpers;

import java.util.Scanner;

public class App {
    private static AddressBook addressBook = new AddressBook();

    public static void main(String[] args) {

        int option;
        System.out.println("Welcome to your DFCorp Address Book \n");
        do {
            System.out.println("Would you like to: ");
            System.out.println("1. Add a new contact,");
            System.out.println("2. Find a contact,");
            System.out.println("3. Edit a contact,");
            System.out.println("4. View all current contacts,");
            System.out.println("5. Delete a contact,");
            System.out.println("6. Delete all contacts");
            System.out.println("7. Exit program.\n");

            System.out.println("Enter an option from 1-7: ");
            Scanner in = new Scanner(System.in);
            option = in.nextInt();

            System.out.println("You selected " + option);
            menuSelector(option);
        } while (option != 7);


    }

    private static void menuSelector(int chosenOption)
    {
        switch (chosenOption)
        {
            case 1:
                addingContactWithInputValidation();
                break;
            case 2:
                findContact();
                break;
            case 3:
                break;
            case 4:
                System.out.println(addressBook.viewAllContacts());
                break;
            case 5:
                break;
            case 6:
                break;
            default:
        }
    }


    private static void addingContactWithInputValidation()
    {
        System.out.println("Please enter the details of the new contact.\nYou can leave either the phone number or email address empty, but not both.");
        Contact newContact;

        System.out.print("Name: ");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine().trim();
        name = validateNameInput(name);

        System.out.println("\nOnly UK phone numbers are currently valid.");
        System.out.print("Phone Number: ");
        String phoneNumber = in.nextLine().trim();
        phoneNumber = validatePhoneInput(phoneNumber);

        System.out.println("\nCorrect format for email - [user]@[domainName]");
        System.out.print("Email Address: ");
        String email = in.nextLine().trim();
        email = validateEmailInput(email);

        newContact = new Contact(name, phoneNumber, email);
        addressBook.addContact(newContact);
    }

    private static void findContact()
    {
        System.out.println("Do you want to find the contact by,\n 1. Name\n 2. Phone Number\n 3. Email Address");
        System.out.print("Enter your selection: ");
        Scanner in = new Scanner(System.in);
        int option = in.nextInt();

        switch (option) {
            case 1:
                System.out.print("Enter name: ");
                String name = in.nextLine().trim();
                name = validateNameInput(name);
                DisplayHelpers.formatContactForDisplay(addressBook.findContact(name));
                break;
            case 2:
                System.out.println("Enter phone number: ");
                String phoneNumber = in.nextLine().trim();
                phoneNumber = validatePhoneInput(phoneNumber);
                DisplayHelpers.formatContactForDisplay(addressBook.findContact(phoneNumber, ContactDetailType.PHONE_NUMBER));
                break;
            case 3:
                System.out.println("Enter email: ");
                String email = in.nextLine().trim();
                email = validateEmailInput(email);
                DisplayHelpers.formatContactForDisplay(addressBook.findContact(email, ContactDetailType.EMAIL_ADDRESS));
                break;
            default:
                break;
        }
    }

    private static void editContact()
    {

    }

    private static String validateNameInput(String name)
    {
        while (name.isEmpty())
        {
            Scanner in = new Scanner(System.in);
            System.out.print("The name is not allowed to be blank.\nPlease enter name again: ");
            name = in.nextLine().trim();
        }
        return name;
    }

    private static String validatePhoneInput(String phoneNumber)
    {
        boolean contactValid = ValidatorHelpers.validateContactInput(phoneNumber, ContactDetailType.PHONE_NUMBER);
        while(!contactValid)
        {
            Scanner in = new Scanner(System.in);
            System.out.print("That phone number input was invalid.\nPlease enter the phone number again: ");
            phoneNumber = in.nextLine().trim();
            contactValid = ValidatorHelpers.validateContactInput(phoneNumber, ContactDetailType.PHONE_NUMBER);
        }
        return phoneNumber;
    }

    private static String validateEmailInput(String email)
    {
        boolean contactValid = ValidatorHelpers.validateContactInput(email, ContactDetailType.EMAIL_ADDRESS);
        while(!contactValid)
        {
            Scanner in = new Scanner(System.in);
            System.out.print("That email input was invalid.\nPlease enter the email address again: ");
            email = in.nextLine().trim();
            contactValid = ValidatorHelpers.validateContactInput(email, ContactDetailType.EMAIL_ADDRESS);
        }
        return email;
    }


}
