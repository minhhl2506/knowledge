package com.example.knowledge.configuration;

import java.io.Serializable;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;
import org.springframework.context.annotation.Configuration;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Configuration
public class RsaProvider implements Serializable {

	/** The Constant serialVersionUID */
	private static final long serialVersionUID = 4466836413614444390L;

	private PrivateKey privateKey;
	private PublicKey publicKey;

	public RsaProvider() {
		try {

			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			generator.initialize(1024);
			KeyPair pair = generator.generateKeyPair();
			privateKey = pair.getPrivate();
			publicKey = pair.getPublic();

		} catch (Exception ignored) {
		}
	}

	public String encrypt(String message) throws Exception {

		byte[] messageToBytes = message.getBytes();
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] encryptedBytes = cipher.doFinal(messageToBytes);
		return encode(encryptedBytes);

	}

	private String encode(byte[] data) {
		return Base64.getEncoder().encodeToString(data);
	}

	public String decrypt(String encryptedMessage) throws Exception {

		byte[] encryptedBytes = decode(encryptedMessage);
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
		return new String(decryptedMessage, "UTF8");
	}

	private byte[] decode(String data) {
		return Base64.getDecoder().decode(data);
	}

	public static void main(String[] args) {
		RsaProvider rsa = new RsaProvider();
		try {
			String encryptedMessage = rsa.encrypt("minhmomi2000");
			String decryptedMessage = rsa.decrypt(encryptedMessage);

			System.out.println("Encrypted:" + encryptedMessage);
			System.out.println("Decrypted:" + decryptedMessage);
		} catch (Exception ingored) {
		}
	}
}
