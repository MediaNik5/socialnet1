package org.twelvenik.socialnet.serverside.exception;


public class WrongNumberException extends Exception{

    /*Exception list*/
    public static final String nullException = "Null is not a valid phone number";
    public static final String emptyException = "Phone number must not be empty";
    public static final String noPlusSignException = "Phone number %s must start with + sign";
    public static final String invalidLengthException = "Wrong length of phone number %s: %d";
    public static final String phoneNumberContainsNotADigitException
            = "Phone number %s must contain only digits";
    public static final String mainNumberContainsNotADigitException
            = "Main number %s(made from %s) must contain only digits.";
    public static final String countryCodeContainsNotADigitException
            = "Country code %s(made from %s) must contain only digits.";
    public static final String mainNumberInvalidLength
            = "Length of main number %s(made from %s) is %d, but has to be 10";
    public static final String invalidCountryCodeException
            = "Country code %s(made from %s) is wrong(empty) or contains more than three characters)";

    public WrongNumberException(String message) {
        super(message);
    }

}