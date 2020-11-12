package org.twelvenik.socialnet.serverside;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.twelvenik.socialnet.serverside.network.LongPollingController;
import org.twelvenik.socialnet.serverside.network.RequestHandler;
import org.twelvenik.socialnet.serverside.network.SMSSender;
import org.twelvenik.socialnet.serverside.network.ServerRequest;
import org.twelvenik.socialnet.serverside.sql.Database;

public class SocialNetwork {

    private static final SocialNetwork socialNetwork = new SocialNetwork();
	private final Database database;
	private final SMSSender smsSender;
	private final ObjectMapper JSONMapper;
	private final RequestHandler requestHandler;

    public static void main(String... args){
        SpringApplication.run(LongPollingController.class, args);
    }

    public static SocialNetwork getInstance(){ return socialNetwork; }

    public Database getDatabase() { return database; }
    public SMSSender getSmsSender() { return smsSender; }
	public ObjectMapper getJSONMapper(){ return JSONMapper; }
	public RequestHandler getRequestHandler(){ return requestHandler; }

	private SocialNetwork(){
        database = null;
        smsSender = new SMSSender();
	    JSONMapper = new ObjectMapper();
		requestHandler = new RequestHandler();
	}
}
