package com.challenges.helpers;

import com.challenges.addressbook.Contact;

public class DisplayHelpers {

    /*Responsibility of this function should just be to format the contact's details for display, then return the new String
    * ViewAllContacts will concatenate the strings for each contact, then return the new string to be printed by the console app
    * */
    public static String formatContactForDisplay(Contact contact)
    {
        /*
        * Name
        * Phone:
        * Email:
        * */
        String contactDisplay = String.format("%s%n" +
                "Phone: %s%n" +
                "Email: %s%n", contact.getName(), contact.getPhoneNumber(), contact.getEmailAddress());
        return contactDisplay;
    }
}
