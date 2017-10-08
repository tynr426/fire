package fire.web.ops;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import fire.web.service.ManagerService;



public class Distribute extends HttpServlet {
	private WebApplicationContext wac=null;
	protected SysParameter sp;
	public Distribute(SysParameter sp,ServletContext context){
		this.sp=sp;
		wac =WebApplicationContextUtils.getWebApplicationContext(context); 
	}
	public <E> E getServiceIml(String name){
		if(wac!=null){
			return (E)wac.getBean(name);
		}
		return null;
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

		@Override  
    public void init(ServletConfig config) throws ServletException  
    {  
        
    } 

}
