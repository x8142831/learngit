package DES;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 对称加密算法，
 * @author XuXiang
 *
 */
public class testDES {
	private  HashMap<String, SecretKey> realkey;
	private  String prepare() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}
	private  void generateKey() throws NoSuchAlgorithmException{
		SecureRandom sr=new SecureRandom();
		realkey=new HashMap<String, SecretKey>();
		KeyGenerator kg=KeyGenerator.getInstance("DES");//des密钥生成器
		kg.init(sr);//初始化密钥
		SecretKey key=kg.generateKey();//产生密钥
		realkey.put("Key", key);
	}
	public String entrypt(String s) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		Cipher cipher=Cipher.getInstance("DES");//获取des的加密器
		cipher.init(Cipher.ENCRYPT_MODE, realkey.get("Key"));//通过密钥在加密模式下进行加密器初始化
		byte[] temp=cipher.doFinal(s.getBytes());//获取密文
		BASE64Encoder be=new BASE64Encoder();//进行base64加密
		return new String(be.encode(temp));
		}
	public String decrypt(String s) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException{
		Cipher cipher=Cipher.getInstance("des");
		cipher.init(Cipher.DECRYPT_MODE, realkey.get("Key"));
		BASE64Decoder de=new BASE64Decoder();
		return new String(cipher.doFinal(de.decodeBuffer(s)));
	}
	/**
	 * @param args
	 * @throws IOException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		// TODO Auto-generated method stub
		testDES te=new testDES();
		String input=te.prepare();
		te.generateKey();
		String output=te.entrypt(input);
		System.out.println(output);
		System.out.println(te.decrypt(output));
	}

}
