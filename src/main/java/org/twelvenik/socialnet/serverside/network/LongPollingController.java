package org.twelvenik.socialnet.serverside.network;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.twelvenik.socialnet.serverside.SocialNetwork;
import org.twelvenik.socialnet.serverside.exception.BadRequestException;
import org.twelvenik.socialnet.serverside.messaging.Message;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootApplication
@RestController
public class LongPollingController {


	private static final List<Message> messageStore = new ArrayList<>();

	@GetMapping("/request")
	public ResponseEntity<Boolean> saveMessage(@RequestParam(value = "request") String requestString) {
		System.out.println(requestString);

		var request = ServerRequest.parseRequest(requestString);

		var socialNetwork = SocialNetwork.getInstance().getRequestHandler();
		try{
			Objects.requireNonNull(request);
			socialNetwork.handleRequest(request);
		}catch(BadRequestException | NullPointerException e){
			e.printStackTrace();
		}
		return ResponseEntity.ok(true);
	}



	@GetMapping("/getMessages")
	public ResponseEntity<List<Message>> getMessage(Message input) throws InterruptedException {
		List<Message> output = new ArrayList<>();
		if (lastStoredMessage().isPresent() && lastStoredMessage().get().getId() > input.getId()) {

			for (int index = (int)input.getId(); index < messageStore.size(); index++)
				output.add(messageStore.get(index));

			return ResponseEntity.ok(output);
		}

		return keepPolling(input);
	}

	private ResponseEntity<List<Message>> keepPolling(Message input) throws InterruptedException {
		Thread.sleep(5000);
		var headers = new HttpHeaders();
		headers.setLocation(URI.create("/getMessages?id=" + input.getId() + "&to=" + input.getChat().getId()));
		return new ResponseEntity<>(headers, HttpStatus.TEMPORARY_REDIRECT);
	}

	private Optional<Message> lastStoredMessage() {
		return messageStore.isEmpty() ? Optional.empty() : Optional.of(messageStore.get(messageStore.size()-1));
	}
}