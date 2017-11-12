package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.common.entity.Menu;
import fire.common.entity.MenuRelation;

public interface MenuDAO {
	public int addMenuRelation(MenuRelation entity);
	public int updateMenuRelation(MenuRelation entity);
	public List<Menu> getMenuList();
	public MenuRelation getMenuRelation(
			@Param("companyId") Integer companyId,
			@Param("managerId") Integer managerId);
	public List<Menu> getMenuRelationList(
			String... menuIds);
}
