package org.twelvenik.socialnet.serverside.network;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.twelvenik.socialnet.serverside.SocialNetwork;
import org.twelvenik.socialnet.serverside.messaging.Chat;
import org.twelvenik.socialnet.serverside.user.User;

import java.util.Objects;
import java.util.Optional;

public class ServerRequest{

	String message;
	Optional<Chat> receiver;
	User sender;
	RequestType requestType;

	public ServerRequest(RequestType requestType, User sender, String message){
		this.message = Objects.requireNonNull(message);
		this.requestType = Objects.requireNonNull(requestType);
		this.sender = Objects.requireNonNull(sender);
		this.receiver = Optional.empty();
	}
	public ServerRequest(RequestType requestType, User sender, Chat receiver, String message){
		this.message = Objects.requireNonNull(message);
		this.requestType = Objects.requireNonNull(requestType);
		this.sender = Objects.requireNonNull(sender);
		this.receiver = Optional.ofNullable(receiver);
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

	public Optional<Chat> getReceiver(){ return receiver; }
	public String getMessage(){ return message; }
	public User getSender(){ return sender;}
	public RequestType getRequestType(){ return requestType; }
}
