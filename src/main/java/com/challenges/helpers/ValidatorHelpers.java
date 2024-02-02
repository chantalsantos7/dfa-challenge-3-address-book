package com.challenges.helpers;

import com.challenges.addressbook.ContactDetailType;

public class ValidatorHelpers {

    /* regex pattern from: https://regexpattern.com/phone-number/#uk*/
    /* pattern currently only works for UK phone numbers*/
    public static String PHONE_REGEX = "^(((\\+44\\s?\\d{4}|\\(?0\\d{4}\\)?)\\s?\\d{3}\\s?\\d{3})|((\\+44\\s?\\d{3}|\\(?0\\d{3}\\)?)\\s?\\d{3}\\s?\\d{4})|((\\+44\\s?\\d{2}|\\(?0\\d{2}\\)?)\\s?\\d{4}\\s?\\d{4}))(\\s?#(\\d{4}|\\d{3}))?$";

    public static String EMAIL_REGEX;

    //Should be called in the user interface to check if num/email matches the proper format for that data field
    //Catch those errors before calling the Contact constructor
    //That way, validators in the constructor only have to check if it is null or empty or whitespace

    public static boolean isInputNullOrEmpty(String input)
    {
        if (input == null) return true;
        return input.trim().isEmpty();
    }

    //Validates whether the given contact detail, based on the provided contact detail type, is in an acceptable format for the address book
    public static boolean validateContactInput(String contactDetail, ContactDetailType contactDetailType) throws IllegalArgumentException
    {
        if (isInputNullOrEmpty(contactDetail)) throw new IllegalArgumentException();
        switch (contactDetailType) {
            case PHONE_NUMBER -> {
                return contactDetail.matches(PHONE_REGEX);
            }
            case EMAIL_ADDRESS -> {
                return contactDetail.matches(EMAIL_REGEX);
            }
            default -> {
                return false;
            }
        }
    }
}
