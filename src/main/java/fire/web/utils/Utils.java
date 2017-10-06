package fire.web.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Utils {
	public static String getHost(){
		try {
			ServletRequestAttributes attri=(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = attri.getRequest();
			return request.getScheme()+"://"+ request.getServerName();
			
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}
}
