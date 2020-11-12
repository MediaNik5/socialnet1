package org.twelvenik.socialnet.serverside.util;

import org.twelvenik.socialnet.serverside.exception.BadRequestException;
import org.twelvenik.socialnet.serverside.network.ServerRequest;

public class UserAuthorizationHandler implements IRequestHandler{


	@Override
	public void handle(ServerRequest request) throws BadRequestException{
		assert request.getRequestType() == ServerRequest.RequestType.userPhoneAuthorization ||
			   request.getRequestType() == ServerRequest.RequestType.userSmsAuthorization;

	}
}
