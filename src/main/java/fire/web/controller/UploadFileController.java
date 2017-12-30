package fire.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import fire.common.entity.WeChatAccount;
import fire.sdk.utils.JsonResult;
import fire.sdk.utils.WechatJsSDK;
import fire.web.service.WeChatAccountService;
import fire.web.service.WeChatAccountServiceImpl;
import fire.web.utils.Md5;
@Controller
public class UploadFileController{
	@ResponseBody
	@RequestMapping("/upload.do")
	public Object handleFileUpload(MultipartHttpServletRequest request){
		Iterator<String> iterator = request.getFileNames();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String virtual="/userfiles/device/"+sdf.format(new Date())+"/";
		String dir=mkdirs(request.getSession().getServletContext().getRealPath(virtual));
		
		String name ="";
		while (iterator.hasNext()) {
			String fileName = iterator.next();
		    name = Md5.createID() + ".jpg";//生成随机文件名  
			MultipartFile multipartFile = request.getFile(fileName);
			BufferedOutputStream bos = null;  
			FileOutputStream fos = null;  
			File file = null;
			try {
				byte[] bytes = multipartFile.getBytes();
				file=new File(dir+name);
				
				fos=new FileOutputStream(file);
				bos = new BufferedOutputStream(fos);  
				bos.write(bytes);
				bos.close();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new JsonResult(e);
			}
			
		}
		return new JsonResult(virtual+name);
		// do stuff...

	}

	public  String mkdirs(String destPath) {  
		File file = new File(destPath);  
		// 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir。(mkdir如果父目录不存在则会抛出异常)  
		if (!file.exists() && !file.isDirectory()) {  
			file.mkdirs();  
		}
		if(!destPath.endsWith("\\")){
			destPath+="\\";
		}
		return destPath;
	}

}
