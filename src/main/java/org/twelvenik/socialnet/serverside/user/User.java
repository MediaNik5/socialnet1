package org.twelvenik.socialnet.serverside.user;

import org.twelvenik.socialnet.serverside.SocialNetwork;
import org.twelvenik.socialnet.serverside.exception.WrongNumberException;
import org.twelvenik.socialnet.serverside.messaging.Chat;
import org.twelvenik.socialnet.serverside.sql.Database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User  implements Serializable{

    PhoneNumber phoneNumber;
    String username;
    String name;
    String bio;
    List<Chat> chats = new ArrayList<>();

    public String getUsername() { return username; }
    public String getName() { return name; }
    public String getBio() { return bio; }
    public PhoneNumber getPhoneNumber(){ return phoneNumber; }
    public List<Chat> getChats(){ return chats; }

    public static boolean initializeUserByPhoneNumber(PhoneNumber phoneNumber) {

        var database = SocialNetwork.getInstance().getDatabase();
        if(!database.containsUserByPhoneNumber(phoneNumber.toString()))
            return createNewUser(phoneNumber);


        return SocialNetwork.getInstance().getSmsSender().authoriseUserByPhoneNumber(phoneNumber);
    }

    private static boolean createNewUser(PhoneNumber phoneNumber) {
        //todo user creating with phone number by sending sms
        return false;
    }

    public User(PhoneNumber phoneNumber, Database database){
        this.phoneNumber = phoneNumber;
        loadMainInfo(database);
    }

    protected void loadMainInfo(Database database){

        String usernameKey = DatabaseKey.getUsernameKeyByPhoneNumber(phoneNumber.toString());
        this.username = database.getValue(usernameKey);

        String nameKey = DatabaseKey.getNameKeyByUserName(this.getUsername());
        this.name = database.getValue(nameKey);

        String bioKey  = DatabaseKey.getBioKeyByUserName(this.getUsername());
        this.bio = database.getValue(bioKey);
    }

    private User(){
        try{
            this.phoneNumber = new PhoneNumber("+79876543210");
        }catch(WrongNumberException e){
            e.printStackTrace();
        }
        this.username = "MediaNikk";
        this.bio = "Privet";
        this.name = "MediaNikk";
    }
    private static final User invalidUser = new User();
    @Deprecated
    public static User getInvalidUserInstance(){
        return invalidUser;
    }


    private static class DatabaseKey{
        private static final String USERNAME = "username";
        private static final String NAME = "name";
        private static final String BIO = "bio";

        private static String getUsernameKeyByPhoneNumber(String phoneNumber){
            return phoneNumber + Database.separator + USERNAME;
        }
        private static String getNameKeyByUserName(String userName){
            return userName + Database.separator + NAME;
        }
        private static String getBioKeyByUserName(String userName){
            return userName + Database.separator + BIO;
        }
    }
}
