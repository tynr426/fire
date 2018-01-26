package fire.web.service;

import java.util.List;

import fire.common.entity.CategoryEnum;

public interface CategoryEnumService {
	public List<CategoryEnum> getEnumList(String enumType);
}
