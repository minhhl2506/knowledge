package com.example.knowledge.configuration;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;

import com.example.knowledge.advice.DecryptErrorException;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.snowflake.client.jdbc.internal.org.bouncycastle.asn1.ASN1EncodableVector;
import net.snowflake.client.jdbc.internal.org.bouncycastle.asn1.ASN1Integer;
import net.snowflake.client.jdbc.internal.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import net.snowflake.client.jdbc.internal.org.bouncycastle.asn1.ASN1Sequence;
import net.snowflake.client.jdbc.internal.org.bouncycastle.asn1.DERNull;
import net.snowflake.client.jdbc.internal.org.bouncycastle.asn1.DEROctetString;
import net.snowflake.client.jdbc.internal.org.bouncycastle.asn1.DERSequence;
import net.snowflake.client.jdbc.internal.org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;

@Slf4j
@Getter
@Setter
public class RsaProvider implements Serializable {
	private static final long serialVersionUID = -5731397764249705937L;

	private String algorithm;
	
	public enum ModeEnum {
		PKCS1, OAEP
	}

	public enum DataTypeEnum {
		HEX, BASE64
	}

	private DataTypeEnum dataType = DataTypeEnum.BASE64;
	private ModeEnum mode = ModeEnum.PKCS1;
	private PrivateKey privateKey;
	private PublicKey publicKey;

	public RsaProvider(String algorithm) {
	    this.algorithm = algorithm;
	}

	public RsaProvider(int keySize) {
		try {
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
			
			keyGen.initialize(keySize);
			
			KeyPair pair = keyGen.generateKeyPair();
			
			privateKey = pair.getPrivate();
			
			publicKey = pair.getPublic();
		} catch (Exception e) {
//			_log.error(e.getMessage());
		}
	}

	public static String getBase64PublicKey(PublicKey publicKey) {
		return toBase64(publicKey.getEncoded());
	}

	public static String getBase64PrivateKey(PrivateKey privateKey) {
		return toBase64(privateKey.getEncoded());
	}

	public static PublicKey getPublicKey(String base64PublicKey) {
		try {
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(fromBase64(base64PublicKey));
			
			return KeyFactory.getInstance("RSA").generatePublic(keySpec);
		} catch (Exception e) {
//			_log.error(e.getMessage());
		}
		
		return null;
	}

	public static PrivateKey getPrivateKey(String base64PrivateKey) {
		try {
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(fromBase64(base64PrivateKey));
			
			return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
		} catch (Exception e) {
//			_log.error(e.getMessage());
		}
		
		return null;
	}

	public byte[] encrypt(String plainText, PublicKey publicKey) throws Exception {
		Cipher cipher = getCipher();
		
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		
		return cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
	}

	public byte[] decrypt(byte[] cipherText, PrivateKey privateKey) throws Exception {
		Cipher cipher = getCipher();
		
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		
		return cipher.doFinal(cipherText);
	}

    public String encrypt(String plainText, String base64PublicKey) {
        try {
            byte[] cipherText = encrypt(plainText, getPublicKey(base64PublicKey));

            if (DataTypeEnum.BASE64.equals(dataType)) {
                return toBase64(cipherText);
            } else {
                return toHex(cipherText);
            }
        } catch (Exception e) {
        	return null;
//            throw new EncryptErrorException();
        }
    }

    public String decrypt(String cipherText, String base64PrivateKey) {
        try {
            byte[] cipherBytes;

            if (DataTypeEnum.BASE64.equals(dataType)) {
                cipherBytes = fromBase64(cipherText);
            } else {
                cipherBytes = fromHex(cipherText);
            }

            return new String(decrypt(cipherBytes, getPrivateKey(base64PrivateKey)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new DecryptErrorException();
        }
    }

    public String encrypt(String plainText) {
        return encrypt(plainText, getBase64PublicKey(publicKey));
    }

    public String decrypt(String cipherText) {
        return decrypt(cipherText, getBase64PrivateKey(privateKey));
    }

	public static RsaProvider fromPrivateKey(String algorithm, String textPrivateKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		RsaProvider provider = new RsaProvider(algorithm);

		PKCS8EncodedKeySpec keySpec = getPKCS8EncodedKeySpec(textPrivateKey);

		if (keySpec != null) {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");

			PrivateKey priKey = keyFactory.generatePrivate(keySpec);

			provider.setPrivateKey(priKey);
		}

		return provider;
	}

	public static RsaProvider fromPublicKey(String algorithm, String textPublicKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		RsaProvider provider = new RsaProvider(algorithm);

		X509EncodedKeySpec keySpec = getX509EncodedKeySpec(textPublicKey);

		if (keySpec != null) {
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");

			PublicKey pubKey = keyFactory.generatePublic(keySpec);

			provider.setPublicKey(pubKey);
		}

		return provider;
	}
	
	private static X509EncodedKeySpec getX509EncodedKeySpec(String textKey) throws IOException {
		byte[] decoded = Base64.decodeBase64(textKey);

		return new X509EncodedKeySpec(decoded);

	}
	
	private static PKCS8EncodedKeySpec getPKCS8EncodedKeySpec(String textKey) throws IOException {
		byte[] keyDecoded = Base64.decodeBase64(textKey);

		ASN1EncodableVector v = new ASN1EncodableVector();

		v.add(new ASN1Integer(0));

		ASN1EncodableVector v2 = new ASN1EncodableVector();

		v2.add(new ASN1ObjectIdentifier(PKCSObjectIdentifiers.rsaEncryption.getId()));
		v2.add(DERNull.INSTANCE);

		v.add(new DERSequence(v2));
		v.add(new DEROctetString(keyDecoded));

		ASN1Sequence seq = new DERSequence(v);

		byte[] keyByte = seq.getEncoded("DER");

		return new PKCS8EncodedKeySpec(keyByte);
	}
	
	private Cipher getCipher() throws Exception {
	    return Cipher.getInstance(this.algorithm);
	}

	private static byte[] fromBase64(String str) {
		return DatatypeConverter.parseBase64Binary(str);
	}

	private static String toBase64(byte[] ba) {
		return DatatypeConverter.printBase64Binary(ba);
	}

	private static byte[] fromHex(String str) {
		return DatatypeConverter.parseHexBinary(str);
	}

	private static String toHex(byte[] ba) {
		return DatatypeConverter.printHexBinary(ba);
	}
}
