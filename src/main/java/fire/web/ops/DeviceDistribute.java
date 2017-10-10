package fire.web.ops;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fire.common.entity.DeviceResult;
import fire.sdk.utils.DTOBeanUtils;
import fire.web.service.DeviceService;
import fire.web.utils.JsonResult;
import fire.web.utils.Utils;

public class DeviceDistribute extends Distribute {

	private DeviceService deviceService;
	public DeviceDistribute(SysParameter sp,ServletContext context) {
		super(sp,context);
		if( deviceService==null){
			deviceService=getServiceIml("deviceService");
		}
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(sp.Action.equals("addDevice")){
			addDevice(req,resp);
		}
	}
	private void addDevice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{ 
		DeviceResult device=(DeviceResult)DTOBeanUtils.getDTO(req, DeviceResult.class);
		String str=Utils.objectToJson(new JsonResult(deviceService.addDevice(device)));
		resp.getWriter().write(str);
	}
	
}
