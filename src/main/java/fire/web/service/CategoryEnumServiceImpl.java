package fire.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fire.common.entity.CategoryEnum;
import fire.web.dao.CategoryEnumDAO;
@Service("categoryEnumService")
public class CategoryEnumServiceImpl implements CategoryEnumService{
	@Resource
	private CategoryEnumDAO ceDAO;
	
	public List<CategoryEnum> getEnumList(String enumType) {
		return ceDAO.getList(enumType);
	}

}
