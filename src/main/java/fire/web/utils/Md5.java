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
	public static String getSha1(String str){
        if (null == str || 0 == str.length())
            return null;
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
