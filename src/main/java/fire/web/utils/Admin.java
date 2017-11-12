package fire.web.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import fire.common.entity.User;

public class Admin {
	private static User user;
	public static User getUser(){
		if(user==null||user.getId()==0){	
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			user =  (User) request.getSession().getAttribute(Constants.AdminPre+Constants.AdminLoginCacheKey);
		}
		if(user==null){
			user=new User();
		}
		return user;
	}
	public static int getUserId(){

		return getUser().getId();

	}
	public static void setCookie(User result){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		request.getSession().setAttribute(Constants.AdminPre+Constants.AdminLoginCacheKey, result);
		
		user=null;
	}
}
