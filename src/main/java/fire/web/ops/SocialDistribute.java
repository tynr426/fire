package fire.web.ops;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fire.common.entity.AuthBind;
import fire.common.entity.CheckDevice;
import fire.common.entity.WeChatAccount;
import fire.sdk.utils.ConvertUtils;
import fire.sdk.utils.QRUtil;
import fire.sdk.utils.WechatJsSDK;
import fire.web.service.WeChatAccountService;
import fire.web.service.WeChatAccountServiceImpl;
import fire.web.utils.Constants;
import fire.web.utils.JsonResult;
import fire.web.utils.Md5;
import fire.web.utils.PropertyUtil;
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
		}else if(sp.Action.equals("getMedia")){
			getMedia(req,resp);
		}
	}
	private void getWeChatAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String str=Utils.objectToJson(new JsonResult(weChatAccountService.getWeChatAccount()));
		resp.getWriter().write(str);
	}
	public void getMedia(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

		int companyId=ConvertUtils.toInt(req.getParameter("CompanyId"));
		String mediaId=req.getParameter("MediaId");
		WeChatAccount wca=weChatAccountService.getWeChatAccount();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String virtual="/device/"+companyId+"/"+sdf.format(new Date())+"/";
		//System.out.println("pc-mediaId="+mediaId);
		String fileName=saveImageToDisk(req,wca,mediaId,virtual);
		String str=Utils.objectToJson(new JsonResult(fileName));
		resp.getWriter().write(str);
	}
	private String saveImageToDisk(HttpServletRequest request,WeChatAccount wca, String mediaId,String virtual){

		String path = "";
		InputStream inputStream =WechatJsSDK.getMedia(wca.getAppId(),wca.getSecret(),mediaId);
		byte[] data = new byte[1024];
		int len = 0;
		FileOutputStream fileOutputStream = null;
		try {
			//服务器存图路径

			String dir=QRUtil.mkdirs(PropertyUtil.getProperty(Constants.UpLoadDir)+virtual);

			String filename = Md5.createID() + ".jpg";
			fileOutputStream = new FileOutputStream(dir + filename);
			while ((len = inputStream.read(data)) != -1) {
				fileOutputStream.write(data, 0, len);
			}
			path=Constants.ImageVirtual+virtual+filename;
			//System.out.println("path="+path);
		} catch (IOException e) {
			e.printStackTrace();
			//System.out.println("ex="+e.getMessage());
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return path;
	}
}
