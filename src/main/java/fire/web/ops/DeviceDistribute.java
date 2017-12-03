package fire.web.ops;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fire.common.entity.DeviceResult;
import fire.sdk.utils.ConvertUtils;
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
		}else if(sp.Action.equals("getDevice")){
			getDevice(req,resp);
		}else if(sp.Action.equals("showDevice")){
			showDevice(req,resp);
		}else if(sp.Action.equals("updateDevice")){
			updateDevice(req,resp);
		}
	}
	private void addDevice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		DeviceResult device=null;
		try {
			ObjectInputStream ois = new ObjectInputStream(req.getInputStream());
			device= (DeviceResult) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//DeviceResult device=(DeviceResult)DTOBeanUtils.getDTO(req, DeviceResult.class);
		String str=Utils.objectToJson(new JsonResult(deviceService.addDevice(device)));
		resp.getWriter().write(str);
	}
	private void getDevice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int id=ConvertUtils.toInt(req.getParameter("Id"));
		resp.setContentType("text/javascript; charset=utf-8"); 
		String str=Utils.objectToJson(new JsonResult(deviceService.getDevice(id)));
		resp.getWriter().write(str);
	}
	private void showDevice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int companyId=ConvertUtils.toInt(req.getParameter("CompanyId"));
		int index=ConvertUtils.toInt(req.getParameter("Index"));
		int size=ConvertUtils.toInt(req.getParameter("Size"));
		resp.setContentType("text/javascript; charset=utf-8"); 
		String str=Utils.objectToJson(new JsonResult(deviceService.getDevicePage(companyId, index, size)));
		resp.getWriter().write(str);
	}
	private void updateDevice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		DeviceResult device=null;
		try {
			ObjectInputStream ois = new ObjectInputStream(req.getInputStream());
			device= (DeviceResult) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//DeviceResult device=(DeviceResult)DTOBeanUtils.getDTO(req, DeviceResult.class);
		String str=Utils.objectToJson(new JsonResult(deviceService.updateDevice(device)));
		resp.getWriter().write(str);
	}
}
