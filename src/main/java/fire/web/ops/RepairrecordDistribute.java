package fire.web.ops;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fire.common.entity.Repairrecord;
import fire.web.service.RepairrecordService;
import fire.web.utils.JsonResult;
import fire.web.utils.Utils;

public class RepairrecordDistribute extends Distribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 519443849894705880L;
	private RepairrecordService repairrecordService;
	public RepairrecordDistribute(SysParameter sp, ServletContext context) {
		super(sp, context);
		if( repairrecordService==null){
			repairrecordService=getServiceIml("repairrecordService");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(sp.Action.equals("addRepairrecord")){
			addRepairrecord(req,resp);
		}
	}
	private void addRepairrecord(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		Repairrecord repairrecord=null;
		try {
			ObjectInputStream ois = new ObjectInputStream(req.getInputStream());
			repairrecord= (Repairrecord) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//DeviceResult device=(DeviceResult)DTOBeanUtils.getDTO(req, DeviceResult.class);
		String str=Utils.objectToJson(new JsonResult(repairrecordService.addRepairrecord(repairrecord)));
		resp.getWriter().write(str);
	}

}
