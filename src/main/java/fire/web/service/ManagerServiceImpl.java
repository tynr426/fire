package fire.web.service;

import javax.annotation.Resource;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import fire.web.dao.ManagerDAO;
import fire.web.entity.Manager;
import fire.web.dao.CompanyDAO;
import fire.web.entity.Company;
import fire.web.service.NameException;
import fire.web.service.PasswordException;
import fire.web.service.VerifyCodeException;
import fire.web.utils.CookiesUtil;
import fire.web.utils.Md5;
import fire.web.utils.PageInfo;
@Service("managerService")
public class ManagerServiceImpl implements ManagerService{
	@Resource
	private ManagerDAO managerDao;
	@Resource
	private CompanyDAO companyDAO;
	
	@HttpConstraint
	//登录
	public Manager login(String username, String password,String code) throws VerifyCodeException,NameException, PasswordException{
		if(username==null||username.trim().isEmpty()){
			throw new NameException("用户名为空");
		}
		if(password==null||password.trim().isEmpty()){
			throw new PasswordException("密码为空");
		}
		Manager manager = managerDao.findByUserName(username);
		if(manager==null){
			throw new NameException("用户名不正确");
		}

        Company company = companyDAO.findById(manager.getCompanyId());
        if(company==null){
        	throw new NameException("ID不能为空");
        }
        if(!company.getCode().equals(code)){
        	throw new NameException("公司代码输入不正确");
        }
		String md5Password = Md5.getMd5(password);
		if(manager.getPassword().equals(md5Password)){
			return manager;
		}else {
			throw new PasswordException("密码错误");
		}
	}
	//退出登录
	public int loginOut() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().removeAttribute("manager");
		return 0;
	}
	public int addManager(Manager manager) throws NameException {
		Manager one = managerDao.findByName(manager.getName());
		if(one!=null){
			throw new NameException("该用户已存在");
		}
		Company company = companyDAO.findById(manager.getCompanyId());
		if(company!=null){
			manager.setCompanyId(company.getId());
		}
		manager.setUserId(1);
		manager.setStatus(1);
		manager.setPassword(Md5.getMd5(manager.getPassword()));
		int n = managerDao.addManager(manager);
		return n;
	}
	public int updateManager(Manager manager) {
		Manager one = managerDao.findNameIsExist(manager.getName(),manager.getId());
		if(one!=null){
			throw new NameException("该用户已存在");
		}
		manager.setPassword(Md5.getMd5(manager.getPassword()));
		int n = managerDao.updateManager(manager);
		return n;
	}
	public Manager getManager(int id) {
		Manager manager = managerDao.findById(id);
		return manager;
	}
	public int deleteManager(int id) {
		Manager manager = managerDao.findById(id);
		if(manager==null){
			throw new NameException("Id不存在");
		}
		int n = managerDao.delete(id);
		return n;
	}
	public PageInfo<Manager> getManagerPage(int companyId,int index, int size) {
		PageInfo<Manager> pi = new PageInfo<Manager>();
		pi.setPageIndex(index);
		pi.setPageSize(size);
		pi.setCount(managerDao.findManagerCount());
		pi.setList(managerDao.findByLimit(companyId,pi.getBegin(), size));
		return pi;
	}
	public int updateStatus(Integer id, int status) {
		Manager manager = managerDao.findById(id);
		if(manager==null){
			throw new NameException("id不存在");
		}
		manager.setStatus(status);
		int n = managerDao.updateStatus(manager);
		return n;
	}
	
}
