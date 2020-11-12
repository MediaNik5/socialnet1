package org.twelvenik.socialnet.serverside.network;


import org.twelvenik.socialnet.serverside.exception.BadRequestException;
import org.twelvenik.socialnet.serverside.util.MessageHandler;
import org.twelvenik.socialnet.serverside.util.UserAuthorizationHandler;

public class RequestHandler {

	private final MessageHandler messageHandler;
	private final UserAuthorizationHandler userAuthorizationHandler;
	public RequestHandler(){
		messageHandler = new MessageHandler();
		userAuthorizationHandler = new UserAuthorizationHandler();
	}

	public void handleRequest(ServerRequest request) throws BadRequestException{

		switch(request.getRequestType()){
			case userPhoneAuthorization, userSmsAuthorization ->
					getUserAuthorizationHandler().handle(request);
			case sendingOfMessage, updatingMessages ->
					getMessageHandler().handle(request);
		}
	}

	public UserAuthorizationHandler getUserAuthorizationHandler(){
		return userAuthorizationHandler;
	}

	public MessageHandler getMessageHandler(){
		return messageHandler;
	}
}
