package org.twelvenik.socialnet.serverside.messaging;

import org.twelvenik.socialnet.serverside.user.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Chat extends Userable implements Serializable{

    protected final List<User> receivers;
    protected final boolean single;

    public Chat(User sender, Collection<User> receivers){
        super(sender);
        this.receivers = new ArrayList<>(receivers);
        single = receivers.size() == 1;
    }

    public boolean isSingle(){
        return single;
    }

    public List<User> getReceivers(){
        return new ArrayList<>(receivers);
    }

    public boolean containsUser(User user){
        return receivers.contains(user);
    }

    public boolean containsUser(String username){
        return receivers.stream().anyMatch(user -> user.getUsername().equals(username));
    }
}
