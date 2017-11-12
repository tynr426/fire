package fire.web.service;

import fire.common.entity.CompanyResult;
import fire.common.entity.Manager;
import fire.web.service.NameException;
import fire.web.service.PasswordException;
import fire.web.service.VerifyCodeException;
import fire.web.utils.PageInfo;

public interface ManagerService {
	public CompanyResult login(String username,String password,String code) throws VerifyCodeException,NameException,PasswordException;
	public int loginOut();
	public int addManager(Manager manager)throws NameException;
	public int updateManager(Manager manager);
	public Manager getManager(int id);
	public int deleteManager(int id);
	public PageInfo<Manager> getManagerPage(int companyId,int index,int size);
	public int updateStatus(Integer id,int status);
	public CompanyResult verifyToken(String token);
	public int updatePwd(String oldPwd,String pwd,int managerId);
}
