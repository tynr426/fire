package fire.web.ops;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fire.common.entity.DeviceResult;
import fire.sdk.utils.ConvertUtils;
import fire.web.service.DeviceQRService;
import fire.web.utils.JsonResult;
import fire.web.utils.Utils;

public class DeviceQRDistribute extends Distribute {

	private DeviceQRService deviceQRService;
	public DeviceQRDistribute(SysParameter sp,ServletContext context) {
		super(sp,context);
		if( deviceQRService==null){
			deviceQRService=getServiceIml("deviceQRService");
		}
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(sp.Action.equals("getDeviceQRByCode")){
			getDeviceQRByCode(req,resp);
		}
	}

	private void getDeviceQRByCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String code = req.getParameter("Code");
		resp.setContentType("text/javascript; charset=utf-8"); 
		String str=Utils.objectToJson(new JsonResult(deviceQRService.getDeviceQRByCode(code)));
		resp.getWriter().write(str);
	}

}
