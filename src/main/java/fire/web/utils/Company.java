package fire.web.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import fire.common.entity.CompanyResult;

public class Company {
	private static CompanyResult company;
	public static CompanyResult getCompany(){
		if(company==null||company.getId()==0){	
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			company =  (CompanyResult) request.getSession().getAttribute(Constants.CompanyPre+Constants.CompanyLoginCacheKey);
		}
		if(company==null){
			company=new CompanyResult();
		}
		return company;
	}
	public static int getCompanyId(){

		return getCompany().getId();

	}
	public static void setCookie(CompanyResult result){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		request.getSession().setAttribute(Constants.CompanyPre+Constants.CompanyLoginCacheKey, result);
		
		company=null;
	}
	public static void clearCompany(){
		company=null;
	}
}
