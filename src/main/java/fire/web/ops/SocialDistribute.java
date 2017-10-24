package fire.web.ops;

import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fire.common.entity.CheckDevice;
import fire.common.entity.WeChatAccount;
import fire.web.service.WeChatAccountService;
import fire.web.utils.JsonResult;
import fire.web.utils.Utils;

public class SocialDistribute extends Distribute {

	private WeChatAccountService weChatAccountService;
	public SocialDistribute(SysParameter sp,ServletContext context) {
		super(sp,context);
		if( weChatAccountService==null){
			weChatAccountService=getServiceIml("weChatAccountService");
		}
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(sp.Action.equals("getWeChatAccount")){
			getWeChatAccount(req,resp);
		}
	}
	private void getWeChatAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String str=Utils.objectToJson(new JsonResult(weChatAccountService.getWeChatAccount()));
		resp.getWriter().write(str);
	}


}
