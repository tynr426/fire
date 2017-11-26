package fire.web.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fire.common.entity.Manager;
import fire.common.entity.Menu;
import fire.common.entity.MenuRelation;
import fire.web.dao.ManagerDAO;
import fire.web.dao.MenuDAO;
@Service("menuService")
public class MenuServiceImpl implements MenuService{
	@Resource
	private MenuDAO menuDAO;
	@Resource
	private ManagerDAO managerDAO;
	public int save(MenuRelation entity) {
		MenuRelation mr =getMenuRelation(entity.getCompanyId(), entity.getManagerId());
		if(mr==null){
			return menuDAO.addMenuRelation(entity);
		}
		return menuDAO.updateMenuRelation(entity);
	}

	public List<Menu> getMenuList() {
		List<Menu> list=menuDAO.getMenuList();
		List<Menu> listMenu = new ArrayList<Menu>();
		getTree(list,listMenu,0);
		return listMenu;
	}

	public MenuRelation getMenuRelation(Integer companyId, Integer managerId) {
		return menuDAO.getMenuRelation(companyId, managerId);
	}

	public List<Menu> getMenuRelationList(Integer companyId, Integer managerId) {
		Manager manager = managerDAO.findById(managerId);
		if(manager.getUserId()==0){
			return getMenuList();
		}
		MenuRelation entity=getMenuRelation(companyId,managerId);
		if(entity!=null&&entity.getMenuIds().length()>0){
			List<Menu> list= menuDAO.getMenuRelationList(entity.getMenuIds().split(","));
		
			List<Menu> listMenu = new ArrayList<Menu>();
			getTree(list,listMenu,0);
			return listMenu;
		}
		
		
		return null;
	}
	

	private void getTree(List<Menu> list,List<Menu> listMenu,int parentId){
		for(Menu menu:list){
			if(menu.getParentId()==parentId){
				menu.setList(new ArrayList<Menu>());
				listMenu.add(menu);
				getTree(list,menu.getList(),menu.getId());
			}
		}
	}

}
