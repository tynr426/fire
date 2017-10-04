package fire.web.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import fire.web.entity.Manager;

public class Company {
	private static Manager manager;
	public static Manager getManager(){
		if(manager==null||manager.getId()==0){	
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			manager =  (Manager) request.getSession().getAttribute("companyManager");
		}
		if(manager==null){
			manager=new Manager();
		}
		return manager;
	}
	public static int getCompanyId(){

		return getManager().getCompanyId();

	}
}
