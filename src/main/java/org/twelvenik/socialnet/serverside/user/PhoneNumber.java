package org.twelvenik.socialnet.serverside.user;

import org.twelvenik.socialnet.serverside.exception.WrongNumberException;

import java.io.Serializable;
import java.util.Objects;

public class PhoneNumber  implements Serializable{
    private static final int lengthOfMainNumber = 10;
    private static final int minLengthOfCountryCode = 1;
    private static final int maxLengthOfCountryCode = 3;

    final int countryCode;
    final String mainNumber;

    public PhoneNumber(int countryCode, String mainNumber)
            throws WrongNumberException{
        Objects.requireNonNull(mainNumber);

        this.countryCode = countryCode;
        this.mainNumber = mainNumber;

        checkIfPhoneNumberValid(toString());
    }

    public PhoneNumber(String phoneNumber)
            throws WrongNumberException {
        phoneNumber = normalisePhoneNumber(phoneNumber);

        checkIfPhoneNumberValid(phoneNumber);

        mainNumber = extractMainNumber(phoneNumber);
        countryCode = extractCountryCode(phoneNumber);
    }

    protected static String extractMainNumber(String phoneNumber)
            throws WrongNumberException {

        String mainNumber = phoneNumber.substring(
                phoneNumber.length() - lengthOfMainNumber //Was: +19876543210, became 9876543210
        );

        checkIfMainNumberValid(mainNumber, phoneNumber);

        return mainNumber;
    }


    protected static int extractCountryCode(String phoneNumber)
            throws WrongNumberException {
        String countryCode = phoneNumber.substring(
                1,
                phoneNumber.length() - lengthOfMainNumber //Was: +19876543210, became 1
        );
        checkIfCountryCodeValid(phoneNumber, countryCode);

        return Integer.parseInt(countryCode);
    }


    @Override
    public String toString() {
        return "+" + countryCode + mainNumber;
    }
    public String toPrettyString(){
        return "+" + countryCode
                + '(' + mainNumber.substring(0, 3) + ") "
                + mainNumber.substring(3, 6) + ' '
                + mainNumber.substring(6, 8) + '-'
                + mainNumber.substring(8, 10);
    }

    public static String normalisePhoneNumber(String phoneNumber) throws NullPointerException {
        Objects.requireNonNull(phoneNumber, WrongNumberException.nullException);

        String normalisedPhoneNumber = phoneNumber //Removes all the non-digits
                .replaceAll("\\D+", "");

        normalisedPhoneNumber = '+' + normalisedPhoneNumber;

        return normalisedPhoneNumber;
    }

    public static void checkIfPhoneNumberValid(String phoneNumber) throws WrongNumberException, NullPointerException {
        Objects.requireNonNull(phoneNumber, WrongNumberException.nullException);

        if(phoneNumber.isBlank())
            throw new WrongNumberException(WrongNumberException.emptyException);

        if(phoneNumber.charAt(0) != '+'){
            throw new WrongNumberException(
                String.format(
                        WrongNumberException.noPlusSignException,
                        phoneNumber
                )
            );
        }

        if(  phoneNumber.length() < lengthOfMainNumber + minLengthOfCountryCode + 1
           | phoneNumber.length() > lengthOfMainNumber + /**/maxLengthOfCountryCode + 1){
            throw new WrongNumberException(
                String.format(
                        WrongNumberException.invalidLengthException,
                        phoneNumber, phoneNumber.length()
                )
            );
        }

        phoneNumber = phoneNumber.substring(1); //Ignore plus sign

        if(phoneNumber.matches("(.*)\\D(.*)")){
            throw new WrongNumberException(
                String.format(
                        WrongNumberException.phoneNumberContainsNotADigitException,
                        phoneNumber
                )
            );
        }
    }

    public static void checkIfMainNumberValid(String mainNumber, String fromPhoneNumber) throws WrongNumberException {
        if(mainNumber.matches("(.*)\\D(.*)")){
            throw new WrongNumberException(
                String.format(
                        WrongNumberException.mainNumberContainsNotADigitException,
                        mainNumber, fromPhoneNumber
                )
            );
        }

        if(mainNumber.length() != lengthOfMainNumber) {
            throw new WrongNumberException(
                String.format(
                        WrongNumberException.mainNumberInvalidLength,
                        mainNumber, fromPhoneNumber, mainNumber.length()
                )
            );
        }
    }

    public static void checkIfCountryCodeValid(String phoneNumber, String countryCode) throws WrongNumberException {
        if(countryCode.isBlank() || countryCode.length() > maxLengthOfCountryCode) {
            throw new WrongNumberException(
                String.format(
                        WrongNumberException.invalidCountryCodeException,
                        countryCode, phoneNumber
                )
            );
        }
        if(countryCode.matches("(.*)\\D(.*)")){
            throw new WrongNumberException(
                    String.format(
                            WrongNumberException.countryCodeContainsNotADigitException,
                            countryCode, phoneNumber
                    )
            );
        }
    }


    @Override
    public boolean equals(Object o){
        if (this == o) return true;

        if (!(o instanceof PhoneNumber)) return false;

        var that = (PhoneNumber) o;
        return countryCode == that.countryCode &&
                mainNumber.equals(that.mainNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryCode, mainNumber);
    }

    public int getCountryCode(){
        return countryCode;
    }

    public String getMainNumber(){
        return mainNumber;
    }
}
