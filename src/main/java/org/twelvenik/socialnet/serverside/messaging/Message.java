package org.twelvenik.socialnet.serverside.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.twelvenik.socialnet.serverside.SocialNetwork;
import org.twelvenik.socialnet.serverside.exception.WrongNumberException;
import org.twelvenik.socialnet.serverside.user.PhoneNumber;
import org.twelvenik.socialnet.serverside.user.User;

import java.io.Serializable;
import java.util.Collections;
import java.util.OptionalInt;


public class Message extends Userable implements Serializable{

	final String text;
	final Chat chat;


	public Message(User sender, Chat receivers, String text){
		super(sender);
		this.chat = receivers;
		this.text = text;
		id = -1;
	}

	@Deprecated
	public static Message ofString(String s) throws JsonProcessingException{
		ObjectMapper mapper = SocialNetwork.getInstance().getJSONMapper();

		return mapper.readValue(s, Message.class);
	}

	public String getText(){
		return text;
	}

	public Chat getChat(){
		return chat;
	}
}
