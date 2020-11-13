package org.twelvenik.socialnet.serverside.network;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.twelvenik.socialnet.serverside.SocialNetwork;
import org.twelvenik.socialnet.serverside.messaging.Chat;
import org.twelvenik.socialnet.serverside.user.User;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

public class ServerRequest  implements Serializable{

	long id;
	String message;
	Chat receiver;
	User sender;
	RequestType requestType;

	public ServerRequest(){ }

	public ServerRequest(long id, RequestType requestType, User sender, String message){
		this.id = id;
		this.message = Objects.requireNonNull(message);
		this.requestType = Objects.requireNonNull(requestType);
		this.sender = Objects.requireNonNull(sender);
	}
	public ServerRequest(long id, RequestType requestType, User sender, Chat receiver, String message){
		this.id = id;
		this.message = Objects.requireNonNull(message);
		this.requestType = Objects.requireNonNull(requestType);
		this.sender = Objects.requireNonNull(sender);
		this.receiver = receiver;
	}


	public enum RequestType{
		userPhoneAuthorization,
		userSmsAuthorization,
		sendingOfMessage,
		updatingMessages,
	}
	static ServerRequest parseRequest(String from){
		var mapper = SocialNetwork.getInstance().getJSONMapper();
		ServerRequest request = null;
		try{
			request = mapper.readValue(from, ServerRequest.class);
		}catch(JsonProcessingException e){
			e.printStackTrace();
		}
		return Objects.requireNonNull(request);
	}

	public Chat getReceiver(){ return receiver; }
	public String getMessage(){ return message; }
	public User getSender(){ return sender;}
	public RequestType getRequestType(){ return requestType; }
}
