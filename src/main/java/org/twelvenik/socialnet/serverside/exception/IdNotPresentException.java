package org.twelvenik.socialnet.serverside.exception;

public class IdNotPresentException extends Exception{

	public IdNotPresentException(){
		super("Id for message was not present.");
	}
}
