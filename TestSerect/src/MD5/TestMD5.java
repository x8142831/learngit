package MD5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestMD5 {
	private static final char[] hexDigits={'0', '1', '2', '3', '4', '5', '6', '7', '8',
        '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	private	String prepare() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s=br.readLine();
		return s;
	} 
	private String byteToHexDigits(byte[] s){
		int k=0;
		char[] halfresult=new char[32];
		for(int i=0;i<16;i++){
			byte p=s[i];
			halfresult[k++]=hexDigits[p >>> 4 & 0xf];
			halfresult[k++]=hexDigits[p&0xf];
		}
		return new String(halfresult);
	}
	public String getMD5first(String s) throws NoSuchAlgorithmException{
		MessageDigest md=MessageDigest.getInstance("MD5");
		byte[] mdd=md.digest(s.getBytes());
		return byteToHexDigits(mdd);//数据处理
	}
	public String getMD5second(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		MessageDigest md=MessageDigest.getInstance("MD5");//建立messagedigest对象
		md.update(s.getBytes("UTF-8"));//更新数据
		byte[] half=md.digest();//完成计算，返回值
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<half.length;i++){
			sb.append(Integer.toHexString(0xff&half[i]));//计算结果
		}
		return sb.toString() ;
	}
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException{
		TestMD5 tm=new TestMD5();
		String input=tm.prepare();
		String s1=tm.getMD5first(input);
		String s2=tm.getMD5second(input);
		System.out.println(s1);
		System.out.println(s2);
	}
}
