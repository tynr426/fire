package fire.web.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import fire.web.entity.Manager;

public class Company {
	private static Manager manager;
	public static Manager getManager(){
		if(manager==null){	
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			manager =  (Manager) request.getSession().getAttribute("companyManager");
		}
		return manager;
	}
	private static int companyId;
	public static int getCompanyId(){
		if(getManager()!=null){			
			companyId = manager.getCompanyId();
		}
		return companyId;
		
	}
}
