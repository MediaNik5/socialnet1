package org.twelvenik.socialnet;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.twelvenik.socialnet.serverside.SocialNetwork;
import org.twelvenik.socialnet.serverside.messaging.Chat;
import org.twelvenik.socialnet.serverside.messaging.Message;
import org.twelvenik.socialnet.serverside.network.ServerRequest;
import org.twelvenik.socialnet.serverside.user.PhoneNumber;
import org.twelvenik.socialnet.serverside.user.User;

import java.util.Arrays;

public class MessageTest{
	public static void main(String[] args){
		User medianik = User.getInvalidUserInstance();
		Chat ch = new Chat(medianik, Arrays.asList(medianik));

		Message msg = new Message(medianik, ch, "Hi there, we're testing first message ever");
		var mapper = SocialNetwork.getInstance().getJSONMapper();

		String s = null;
		try{
			s = mapper.writeValueAsString(msg);
		}catch(JsonProcessingException e){
			e.printStackTrace();
		}
		ServerRequest req = new ServerRequest(123, ServerRequest.RequestType.sendingOfMessage, medianik, s);
		try{
			s = mapper.writeValueAsString(req);
		}catch(JsonProcessingException e){
			e.printStackTrace();
		}
		s = s.replaceAll("\\{", "%7B").replaceAll("}", "%7D")
				.replaceAll("\\[", "%5B").replaceAll("]", "%5D")
				.replaceAll("\\\\", "%5C");

		System.out.println(s);
	}
}
