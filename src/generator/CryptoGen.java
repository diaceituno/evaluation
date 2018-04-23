package generator;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class CryptoGen {
	
	private final SecretKeySpec KEY;
	private final String ENCODING = "UTF-8";
	
	public CryptoGen() throws UnsupportedEncodingException {
		byte[] keyBytes = "nokela.miqr.gmbh".getBytes(ENCODING);
		KEY = new SecretKeySpec(keyBytes,"AES");
	}
	
	public String encrypt(String plainText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, KEY);
		byte[] plainBytes = plainText.getBytes(ENCODING);
		String encrypted = Base64.getEncoder().withoutPadding().encodeToString(cipher.doFinal(plainBytes));
		
		return encrypted;
	}
	
	public String decrypt(String encoded) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, KEY);
		String plainText = new String(cipher.doFinal(Base64.getDecoder().
				decode(encoded)));
		
		return plainText;
	}
}
