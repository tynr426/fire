package fire.web.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fire.web.dao.ManagerDAO;
import fire.web.entity.Manager;
@Service("managerService")
public class ManagerServiceImpl implements ManagerService{
	@Resource
	private ManagerDAO managerDao;
	
	public int  addManager(Manager manager) {
		return 0;
	}
	
}
