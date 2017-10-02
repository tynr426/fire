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
	//��¼
	public Manager login(String username, String password,String verifyCode,String code) throws VerifyCodeException,NameException, PasswordException{
		if(username==null||username.trim().isEmpty()){
			throw new NameException("�û���Ϊ��");
		}
		if(password==null||password.trim().isEmpty()){
			throw new PasswordException("����Ϊ��");
		}
		Manager manager = managerDao.findByUserName(username);
		if(manager==null){
			throw new NameException("�û�������ȷ");
		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Cookie cookie = CookiesUtil.getCookieByName(request, "VerifyCode");
		
        if(cookie==null){
            throw new VerifyCodeException("��֤�����");   
        }else if(!verifyCode.equals(cookie.getValue().toLowerCase())){
        	throw new VerifyCodeException("��֤���������"); 
        }
        Company company = companyDAO.findById(manager.getCompanyId());
        if(company==null){
        	throw new NameException("ID����Ϊ��");
        }
        if(!company.getCode().equals(code)){
        	throw new NameException("��˾�������벻��ȷ");
        }
		String md5Password = Md5.getMd5(password);
		if(manager.getPassword().equals(md5Password)){
			return manager;
		}else {
			throw new PasswordException("�������");
		}
	}
	//�˳���¼
	public int loginOut() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().removeAttribute("manager");
		return 0;
	}
	public int addManager(Manager manager) throws NameException {
		Manager one = managerDao.findByName(manager.getName());
		if(one!=null){
			throw new NameException("���û��Ѵ���");
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
			throw new NameException("���û��Ѵ���");
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
			throw new NameException("Id������");
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
			throw new NameException("id������");
		}
		manager.setStatus(status);
		int n = managerDao.updateStatus(manager);
		return n;
	}
	
}
