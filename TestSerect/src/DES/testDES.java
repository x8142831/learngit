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
 * �ԳƼ����㷨��
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
		KeyGenerator kg=KeyGenerator.getInstance("DES");//des��Կ������
		kg.init(sr);//��ʼ����Կ
		SecretKey key=kg.generateKey();//������Կ
		realkey.put("Key", key);
	}
	public String entrypt(String s) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		Cipher cipher=Cipher.getInstance("DES");//��ȡdes�ļ�����
		cipher.init(Cipher.ENCRYPT_MODE, realkey.get("Key"));//ͨ����Կ�ڼ���ģʽ�½��м�������ʼ��
		byte[] temp=cipher.doFinal(s.getBytes());//��ȡ����
		BASE64Encoder be=new BASE64Encoder();//����base64����
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
