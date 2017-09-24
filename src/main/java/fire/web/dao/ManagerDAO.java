package fire.web.dao;

import fire.web.entity.Manager;


public interface ManagerDAO {
	public int addManager(Manager manager);
	public int updateManager(Manager manager);
	public Manager getManagerByCompanyId(int companyId);
	public int delete(int companyId);
}
