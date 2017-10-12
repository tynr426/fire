package fire.web.ops;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fire.common.entity.DeviceTypeResult;
import fire.sdk.utils.DTOBeanUtils;
import fire.web.service.DeviceTypeService;
import fire.web.utils.JsonResult;
import fire.web.utils.Utils;

public class DeviceTypeDistribute extends Distribute {

	private DeviceTypeService deviceTypeService; 
	public DeviceTypeDistribute(SysParameter sp,ServletContext context) {
		super(sp,context);
		if( deviceTypeService==null){
			deviceTypeService=getServiceIml("deviceTypeService");
		}
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(sp.Action.equals("findAll")){
			findAll(req,resp);
		}
		
	}
	private void findAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String str=Utils.objectToJson(new JsonResult(deviceTypeService.findDeviceTypeResult()));
		resp.getWriter().write(str);
	}
	
}
