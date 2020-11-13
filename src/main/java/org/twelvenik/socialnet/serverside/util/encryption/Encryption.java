package org.twelvenik.socialnet.serverside.util.encryption;

import de.saly.kafka.crypto.EncryptingSerializer;
import de.saly.kafka.crypto.RsaKeyGen;

import java.util.UUID;

public class Encryption{


	public static String decipher(String s, String privateKey){

		//RsaKeyGen.main(new String[]{});

		return s;
	}

	public static String cipher(String s, String publicKey){

		return s;
	}
}
