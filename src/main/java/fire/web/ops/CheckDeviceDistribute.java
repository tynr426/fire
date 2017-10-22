package fire.web.ops;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fire.common.entity.CheckDevice;
import fire.web.service.CheckDeviceService;
import fire.web.utils.JsonResult;
import fire.web.utils.Utils;

public class CheckDeviceDistribute extends Distribute {

	private CheckDeviceService checkDeviceService;
	public CheckDeviceDistribute(SysParameter sp,ServletContext context) {
		super(sp,context);
		if( checkDeviceService==null){
			checkDeviceService=getServiceIml("checkDeviceService");
		}
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(sp.Action.equals("addCD")){
			addCD(req,resp);
		}
	}
	private void addCD(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		CheckDevice CD=null;
		try {
			ObjectInputStream ois = new ObjectInputStream(req.getInputStream());
			CD= (CheckDevice) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//DeviceResult device=(DeviceResult)DTOBeanUtils.getDTO(req, DeviceResult.class);
		String str=Utils.objectToJson(new JsonResult(checkDeviceService.addCD(CD)));
		resp.getWriter().write(str);
	}


}
