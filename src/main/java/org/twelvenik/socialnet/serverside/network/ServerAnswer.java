package org.twelvenik.socialnet.serverside.network;

import java.io.Serializable;

public class ServerAnswer  implements Serializable{

	boolean isSucceed;
	String message;

	public ServerAnswer(){ }
	public ServerAnswer(String message){
		if(message != null){
			this.message = message;
			isSucceed = true;
		}
	}
	public ServerAnswer(String message, boolean isSucceed){
		this.isSucceed = isSucceed;
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public boolean isSucceed(){
		return isSucceed;
	}
}
