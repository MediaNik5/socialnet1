package org.twelvenik.socialnet.serverside.sql;

public interface Database {
    public static final String separator = "::";
    boolean containsUserByPhoneNumber(String phoneNumber);
    String getValue(String key);
    String setValue(String key);
}
