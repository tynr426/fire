package fire.web.admin.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.WriterException;

import fire.common.entity.DeviceQR;
import fire.common.entity.DeviceType;
import fire.common.entity.DeviceTypeResult;
import fire.sdk.utils.QRUtil;
import fire.web.service.DeviceQRService;
import fire.web.service.DeviceTypeService;
import fire.web.utils.JsonResult;
import fire.web.utils.Md5;
import fire.web.utils.PageInfo;

@Controller
@RequestMapping("/deviceType")
public class DeviceTypeController {
	@RequestMapping("/DeviceType.do")
	public String Api(){
		return "WebAdmin/System/DeviceType";
	}
	@Resource
	private DeviceTypeService deviceTypeService;
	@Resource
	private DeviceQRService deviceQRService;
	@RequestMapping("/show.do")
	@ResponseBody
	public Object getDeviceTypePage(int index,int size){
		PageInfo<DeviceType> pi = deviceTypeService.getDeviceTypePage(index, size);
		return new JsonResult(pi);	
	}
	@RequestMapping("/findAll.do")
	@ResponseBody
	public Object findAll(){
		List<DeviceTypeResult> list = deviceTypeService.findDeviceTypeResult();
		return new JsonResult(list);	
	}


	@RequestMapping("/addDeviceType.do")
	@ResponseBody	
	public JsonResult addDeviceType(DeviceType dt){
		int n = deviceTypeService.addDeviceType(dt);
		return new JsonResult(n);	
	}
	@RequestMapping("/getDeviceType.do")
	@ResponseBody	
	public JsonResult getdeviceType(int id){
		DeviceType dt = deviceTypeService.getDeviceType(id);	
		return new JsonResult(dt);
	}
	@RequestMapping("/update.do")
	@ResponseBody	
	public JsonResult updateDevicetype(DeviceType dt){
		int n = deviceTypeService.updateDeviceType(dt);	
		return new JsonResult(n);
	}
	@RequestMapping("/delete.do")
	@ResponseBody
	public JsonResult deleteDevicetype(Integer id){
		int n = deviceTypeService.deleteDeviceType(id);
		return new JsonResult(n);
	}
	@RequestMapping("/switchStatus.do")
	@ResponseBody
	public JsonResult updateStatus(Integer id,int status){
		int n =deviceTypeService.updateStatus(id, status);
		return new JsonResult(n);
	}	
	@RequestMapping("/createQR.do")
	@ResponseBody
	public JsonResult createQR(int id,int number, HttpServletResponse response,HttpServletRequest req ){
		DeviceType deviceType= deviceTypeService.getDeviceType(id);
		if(deviceType==null) return new JsonResult(new Exception("当前设备不存在"));
		
		String imagePath="";
		if(deviceType.getVirtualPath()!=null&&deviceType.getVirtualPath().length()>0){
		req.getSession().getServletContext().getRealPath(deviceType.getVirtualPath());
		}
		List<DeviceQR> list=new ArrayList<DeviceQR>();
		String virtural = "/userfiles/devicetype/"+id+"/";
		String dir=req.getSession().getServletContext().getRealPath(virtural);
		Random rand = new Random();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");	
		String batch = sdf.format(new Date())+rand.nextInt(1000);
		for(int i=0;i<number;i++){
			DeviceQR entity=new DeviceQR();
			entity.setDeviceTypeId(id);
			entity.setCode(Md5.createID());
			entity.setAddTime(new Date());
			entity.setBatch(batch);
			
				try {
					entity.setQRVirtural(virtural+ QRUtil.encode(entity.getCode(),imagePath,dir,false));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			list.add(entity);
		}
		deviceQRService.addDeviceQR(list);
		return new JsonResult(batch);
	}
}
