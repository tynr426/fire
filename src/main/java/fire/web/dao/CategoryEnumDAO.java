package fire.web.dao;

import java.util.List;

import fire.common.entity.CategoryEnum;

public interface CategoryEnumDAO {
	public List<CategoryEnum> getList(String enumType);
}
