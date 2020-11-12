package org.twelvenik.socialnet.serverside.util;

import org.twelvenik.socialnet.serverside.exception.BadRequestException;
import org.twelvenik.socialnet.serverside.network.ServerRequest;

public interface IRequestHandler{
	void handle(ServerRequest request) throws BadRequestException;
}
