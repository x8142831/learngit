package RSA;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 * 非对称加密算法
 * @author XuXiang
 *
 */
public class TestRSA {
	private static HashMap<String,Key>realkey;
	private static String prepare() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	} 
	private static void generateKey() throws NoSuchAlgorithmException, FileNotFoundException, IOException{
		SecureRandom sr=new SecureRandom();
		KeyPairGenerator keyPair=KeyPairGenerator.getInstance("RSA");
		keyPair.initialize(1024, sr);
		KeyPair key= keyPair.generateKeyPair();
		realkey=new HashMap<String, Key>();
		realkey.put("PublicKey", key.getPublic());
		realkey.put("PrivateKey", key.getPrivate());
		/*ObjectOutputStream oos1=new ObjectOutputStream(new FileOutputStream(new File("")));
		ObjectOutputStream oos2=new ObjectOutputStream(new FileOutputStream("PrivateKey"));
		oos1.writeObject(publicKey);
		oos2.writeObject(privateKey);
		oos1.close();
		oos2.close();*/
	}
	private String encryptRSA(String s) throws FileNotFoundException, IOException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		//ObjectInputStream ois=new ObjectInputStream(new FileInputStream("PublicKey"));
		//Key key=(Key)ois.readObject();
		//ois.close();
		Cipher cipher=Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, realkey.get("PublicKey"));
		BASE64Encoder bs64=new BASE64Encoder();
		return bs64.encode(cipher.doFinal(s.getBytes()));
		
	}
	private String decryptRSA(String s) throws FileNotFoundException, IOException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		//ObjectInputStream ois=new ObjectInputStream(new FileInputStream("PrivateKey"));
		//Key key=(Key)ois.readObject();
		Cipher cipher=Cipher.getInstance("RSA");
		//cipher.init(cipher.DECRYPT_MODE, key);
		cipher.init(Cipher.DECRYPT_MODE,realkey.get("PrivateKey"));
		BASE64Decoder bd=new BASE64Decoder();
		return new String(cipher.doFinal(bd.decodeBuffer(s)));
	}
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, ClassNotFoundException {
		// TODO Auto-generated method stub
		TestRSA te=new TestRSA();
		String input=te.prepare();
		te.generateKey();
		String output=te.encryptRSA(input);
		System.out.println(output);
		System.out.println(te.decryptRSA(output));	
	}

}
