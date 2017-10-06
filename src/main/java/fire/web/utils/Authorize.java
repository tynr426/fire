package fire.web.utils;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import fire.web.entity.Manager;

public class Authorize {
	public static String getCompanyToken(Manager entity,int day){
		String url=Utils.getHost();
		Format f = new SimpleDateFormat("yyyy-MM-dd");  

		Date today = new Date();  
		Calendar c = Calendar.getInstance();  
		c.setTime(today);  
		c.add(Calendar.DAY_OF_MONTH, day);

		return "fire|"+url+"|"+ entity.getCompanyId()+"|"+entity.getId()+"|"+entity.getUserName()+"|"+entity.getPassword()+"|"+f.format(c.getTime());
	}
}
