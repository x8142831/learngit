package SHA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestSHA {
	private static final char[] toHex={'0', '1', '2', '3', '4', '5', '6', '7', '8',
        '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	private String prepare() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s=br.readLine();
		return s;
	}
	public String testSHA1first(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest md=MessageDigest.getInstance("SHA");
		byte[] half=md.digest(s.getBytes("utf-8"));
		StringBuffer bf=new StringBuffer();
		for(int i=0;i<half.length;i++){
			bf.append(Integer.toHexString(half[i]));
		}
		return bf.toString();
	}
	public String testSHAsecond(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest md=MessageDigest.getInstance("SHA");
		byte[] half=md.digest(s.getBytes("utf-8"));
		return toHexdigest(half);
	}
	private String toHexdigest(byte[] s){
		char[] str=new char[40];
		int k=0;
		for(int i=0;i<20;i++){
			byte temp=s[i];
			str[k++]=toHex[temp>>>4 &0xf];
			str[k++]=toHex[temp& 0xf];
		}
		return new String(str);
	}
	/**
	 * @param args
	 * @throws IOException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		TestSHA te=new TestSHA();
		String s=te.prepare();
		System.out.println(te.testSHA1first(s));
		System.out.println(te.testSHAsecond(s));

	}

}
