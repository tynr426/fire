package fire.web.service;




import java.util.List;

import fire.common.entity.Menu;
import fire.common.entity.MenuRelation;

public interface MenuService {
	public int save(MenuRelation entity);
	public List<Menu> getMenuList();
	public MenuRelation getMenuRelation(Integer companyId,Integer managerId);
	public List<Menu> getMenuRelationList(Integer companyId,Integer managerId);
}
