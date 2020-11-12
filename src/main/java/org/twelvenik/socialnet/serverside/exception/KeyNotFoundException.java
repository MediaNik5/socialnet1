package org.twelvenik.socialnet.serverside.exception;

public class KeyNotFoundException extends Exception{

    public KeyNotFoundException(){
        super("Unknown key in database not found exception.");
    }
    public KeyNotFoundException(String key){
        super(String.format("Key %s in database not found exception.", key));
    }
}
