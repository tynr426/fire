package fire.web.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Md5 {
	public static String getMd5(String pwd) {  
	    MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("md5");
			byte[] cipherData = md5.digest(pwd.getBytes());  
		    StringBuilder builder = new StringBuilder();  
		    for(byte cipher : cipherData) {  
		        String toHexStr = Integer.toHexString(cipher & 0xff);  
		        builder.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr);  
		        System.out.println(builder.toString());
		    }  
		    return builder.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";  
	    
	      
	}
	public static String createID(){
		String id = UUID.randomUUID().toString();
		return id.replace("-", "");
	}
	public static void main(String[] args) {
		System.out.println(getMd5("admin"));
		System.out.println(createID().substring(0,3));
	}
	
}
