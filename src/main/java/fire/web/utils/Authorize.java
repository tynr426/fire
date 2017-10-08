package fire.web.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fire.common.entity.Manager;
import fire.sdk.utils.AES;

public class Authorize {
	public static String getCompanyToken(Manager entity,String code,int day){
		String url=Utils.getHost();
		Format f = new SimpleDateFormat("yyyy-MM-dd");  

		Date today = new Date();  
		Calendar c = Calendar.getInstance();  
		c.setTime(today);  
		c.add(Calendar.DAY_OF_MONTH, day);

		try {
			return AES.aesEncrypt("fire|"+url+"|"+code+"|"+entity.getId()+"|"+entity.getUserName()+"|"+entity.getPassword()+"|"+f.format(c.getTime()),"fire");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
