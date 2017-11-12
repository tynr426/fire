package fire.web.company.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fire.common.entity.MenuRelation;
import fire.sdk.utils.JsonResult;
import fire.web.service.MenuService;

@Controller
@RequestMapping("/company/menu")
public class MenuController {
	@Resource
	private MenuService menuService;
	@RequestMapping("/save.do")
	@ResponseBody
	public JsonResult save(MenuRelation entity) {
		return new JsonResult(menuService.save(entity));
	}
	@RequestMapping("/getMenuList.do")
	@ResponseBody
	public JsonResult getMenuList() {
		return new JsonResult(menuService.getMenuList());
	}
	@RequestMapping("/getMenuRelation.do")
	@ResponseBody
	public JsonResult getMenuRelation(Integer companyId, Integer managerId) {
		return new JsonResult(menuService.getMenuRelation(companyId, managerId));
	}
	@RequestMapping("/getMenuRelationList.do")
	@ResponseBody
	public JsonResult getMenuRelationList(Integer companyId, Integer managerId) {
		return new JsonResult(menuService.getMenuRelationList(companyId, managerId));
	}
}
