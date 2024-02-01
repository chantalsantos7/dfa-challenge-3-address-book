package com.challenges.helpers;

import com.challenges.addressbook.ContactDetailType;

public class ValidatorHelpers {

    public static String PHONE_REGEX;
    public static String EMAIL_REGEX;

    //Should be called in the user interface to check if num/email matches the proper format for that data field
    //Catch those errors before calling the Contact constructor
    //That way, validators in the constructor only have to check if it is null or empty or whitespace

    public static boolean isInputNullOrEmpty(String input)
    {
        if (input == null) return true;
        return input.trim().isEmpty();
    }

    public static String validateContactInput(String contactDetail, ContactDetailType contactDetailType)
    {
        return null;
    }
}
