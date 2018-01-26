package fire.web.ops;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fire.common.entity.CheckDevice;
import fire.common.entity.CheckDeviceResult;
import fire.sdk.utils.ConvertUtils;
import fire.web.service.CheckDeviceService;
import fire.web.service.DevicetypeparameterService;
import fire.web.utils.JsonResult;
import fire.web.utils.Utils;

public class CheckDeviceDistribute extends Distribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 605048233328426639L;
	private CheckDeviceService checkDeviceService;
	private DevicetypeparameterService devicetypeparameterService;
	public CheckDeviceDistribute(SysParameter sp,ServletContext context) {
		super(sp,context);
		if( checkDeviceService==null){
			checkDeviceService=getServiceIml("checkDeviceService");
		}
		if( devicetypeparameterService==null){
			devicetypeparameterService=getServiceIml("devicetypeparameterService");
		}
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(sp.Action.equals("addCD")){
			addCD(req,resp);
		}
		if(sp.Action.equals("getCD")){
			getCD(req,resp);
		}
		if(sp.Action.equals("showCDList")){
			showCDList(req,resp);
		}
		if(sp.Action.equals("getCheckParameter")){
			getCheckParameter(req,resp);
		}
		if(sp.Action.equals("getStatistics")){
			getStatistics(req,resp);
		}
	}
	private void addCD(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		CheckDeviceResult CD=null;
		try {
			ObjectInputStream ois = new ObjectInputStream(req.getInputStream());
			CD= (CheckDeviceResult) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//DeviceResult device=(DeviceResult)DTOBeanUtils.getDTO(req, DeviceResult.class);
		String str=Utils.objectToJson(new JsonResult(checkDeviceService.addCD(CD)));
		resp.getWriter().write(str);
	}
	private void getCD(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int id=ConvertUtils.toInt(req.getParameter("Id"));
		resp.setContentType("text/javascript; charset=utf-8"); 
		String str=Utils.objectToJson(new JsonResult(checkDeviceService.getCheckDevice(id)));
		resp.getWriter().write(str);
	}
	private void showCDList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int companyId=ConvertUtils.toInt(req.getParameter("CompanyId"));
		int index=ConvertUtils.toInt(req.getParameter("Index"));
		int size=ConvertUtils.toInt(req.getParameter("Size"));
		Integer status=null;
		if(req.getParameter("Status")!=null){
			status=ConvertUtils.toInt(req.getParameter("Status"));
		}
		String keyword=req.getParameter("Keyword");
		resp.setContentType("text/javascript; charset=utf-8"); 
		String str=Utils.objectToJson(new JsonResult(checkDeviceService.getCheckDevicePage(companyId, index, size, status, keyword)));
		resp.getWriter().write(str);
	}
	private void getCheckParameter(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int deviceTypeId=ConvertUtils.toInt(req.getParameter("DeviceTypeId"));
		int index=ConvertUtils.toInt(req.getParameter("Index"));
		int size=ConvertUtils.toInt(req.getParameter("Size"));
		resp.setContentType("text/javascript; charset=utf-8"); 
		String str=Utils.objectToJson(new JsonResult(devicetypeparameterService.getDevicetypeparameterPage(deviceTypeId,2, 1, 30)));
		resp.getWriter().write(str);
	}
	private void getStatistics(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		int companyId=ConvertUtils.toInt(req.getParameter("CompanyId"));
		Integer status=null;
		if(req.getParameter("Status")!=null){
			status=ConvertUtils.toInt(req.getParameter("Status"));
		}
		String keyword=req.getParameter("Keyword");
		resp.setContentType("text/javascript; charset=utf-8"); 
		String str=Utils.objectToJson(new JsonResult(checkDeviceService.getStatistics(companyId, status, keyword)));
		resp.getWriter().write(str);
	}

}
