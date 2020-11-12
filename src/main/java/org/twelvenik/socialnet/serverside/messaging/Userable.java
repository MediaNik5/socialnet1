package org.twelvenik.socialnet.serverside.messaging;

import org.twelvenik.socialnet.serverside.exception.IdNotPresentException;
import org.twelvenik.socialnet.serverside.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.OptionalLong;

public class Userable {


    protected final User sender;
    protected long id;

    public Userable(User sender){
        this.sender = sender;
    }

    public Userable setId(long id){
        this.id = id;
        return this;
    }

    public long getId(){
        return id;
    }

    public User getSender(){
        return sender;
    }
}
