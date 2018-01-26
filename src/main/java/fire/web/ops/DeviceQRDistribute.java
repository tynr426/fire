package fire.web.ops;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fire.common.entity.DeviceResult;
import fire.common.entity.ScanInfo;
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
		String qrCode = req.getParameter("QrCode");
		Integer toManagerId = ConvertUtils.toInt(req.getParameter("ToManagerId"));
		Integer companyId = ConvertUtils.toInt(req.getParameter("CompanyId"));
		
		resp.setContentType("text/javascript; charset=utf-8"); 
		ScanInfo entity=deviceQRService.getDeviceQRByCode(qrCode,toManagerId);
		JsonResult result=new JsonResult(entity);
		if(entity==null){
			result=new JsonResult(100,new Throwable("没有检测到二维码对应的信息"));
		}
		else if(entity.getCompanyId()!=null){
			if(companyId!=entity.getCompanyId()){
				result=new JsonResult(100,new Throwable("该设备已经绑定其他公司"));
			}
		}
		String str=Utils.objectToJson(result);
		resp.getWriter().write(str);
	}

}
