package org.twelvenik.socialnet.serverside.exception;

public class BadMessageRequestException extends BadRequestException{

	public static final String badReceiverException = "Receiver is invalid or null exception.";
	public static final String badSenderException = "Sender is invalid or null exception.";

	public BadMessageRequestException(String message){
		super(message);
	}
	public static BadMessageRequestException badReceiverException(){
		return new BadMessageRequestException(badReceiverException);
	}
	public static BadMessageRequestException badSenderException(){
		return new BadMessageRequestException(badSenderException);
	}
}
