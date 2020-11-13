package org.twelvenik.socialnet.serverside.util;

import org.twelvenik.socialnet.serverside.exception.BadMessageRequestException;
import org.twelvenik.socialnet.serverside.exception.BadRequestException;
import org.twelvenik.socialnet.serverside.messaging.Chat;
import org.twelvenik.socialnet.serverside.messaging.Message;
import org.twelvenik.socialnet.serverside.network.ServerRequest;

public class MessageHandler implements IRequestHandler{

	@Override
	public void handle(ServerRequest request) throws BadRequestException{
		assert request.getRequestType() == ServerRequest.RequestType.sendingOfMessage ||
			   request.getRequestType() == ServerRequest.RequestType.updatingMessages;

		//if(request.getReceiver() == null)
		//	throw BadMessageRequestException.badReceiverException();
		if(request.getSender() == null || request.getSender().getUsername() == null)
			throw BadMessageRequestException.badSenderException();



		//Message message = new Message(request.getSender(), new Chat(request.getSender()))


	}
}
