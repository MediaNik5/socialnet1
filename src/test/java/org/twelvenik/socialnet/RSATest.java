package org.twelvenik.socialnet;

import de.saly.kafka.crypto.RsaKeyGen;

public class RSATest{

	public static void main(String[] args){
		try{
			RsaKeyGen.main(new String[]{});
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
