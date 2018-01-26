package fire.web.admin.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fire.sdk.utils.JsonResult;
import fire.web.service.CategoryEnumService;

@Controller
@RequestMapping("/categoryenum")
public class CategoryEnumController {
	@Resource
	private CategoryEnumService categoryEnumService;
	@RequestMapping("/getEnumList.do")
	@ResponseBody
	public Object getEnumList(String enumType){
		if(enumType==null)return new JsonResult(new Throwable("枚举类型为空"));
		return new JsonResult(categoryEnumService.getEnumList(enumType));
	}
}
