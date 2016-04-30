package Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 将6位转化为8位在前面两位添加0
 * @author XuXiang
 *
 */
public class TestBase64 {
	private String prepare() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s=br.readLine();
		return s; 
	}
	public String testBase64(String s) throws UnsupportedEncodingException{
		BASE64Encoder be=new BASE64Encoder();
		return be.encode(s.getBytes("utf-8"));
	}
	public String base64Decoder(String s) throws IOException{
		BASE64Decoder bd=new BASE64Decoder();
		byte[] half=bd.decodeBuffer(s);
		return new String(half);
	}
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		TestBase64 te=new TestBase64();
		String input=te.prepare();
		String output=te.testBase64(input);
		System.out.println(output);
		System.out.println(te.base64Decoder(output));
	}

}
