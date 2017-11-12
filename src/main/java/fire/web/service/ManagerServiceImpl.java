package fire.web.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import fire.web.dao.ManagerDAO;
import fire.common.entity.*;
import fire.common.entity.CompanyResult;
import fire.sdk.utils.AES;
import fire.web.dao.CompanyDAO;
import fire.web.service.NameException;
import fire.web.service.PasswordException;
import fire.web.service.VerifyCodeException;
import fire.web.utils.Authorize;
import fire.web.utils.Constants;
import fire.web.utils.Md5;
import fire.web.utils.PageInfo;
import fire.web.utils.Utils;
@Service("managerService")
public class ManagerServiceImpl implements ManagerService{
	@Resource
	private ManagerDAO managerDao;
	@Resource
	private CompanyDAO companyDAO;

	@HttpConstraint
	//登录
	public CompanyResult login(String username, String password,String code) throws VerifyCodeException,NameException, PasswordException{
		if(username==null||username.trim().isEmpty()){
			throw new NameException("用户名为空");
		}
		if(password==null||password.trim().isEmpty()){
			throw new PasswordException("密码为空");
		}
		if(code.isEmpty()){
			throw new NameException("公司代码为空");
		}


		Company company =companyDAO.getCompanyByCode(code);
		if(company==null){
			throw new NameException("公司代码输入不正确");
		}		

		Manager manager = managerDao.findByUserName(username);
		if(manager==null){
			throw new NameException("用户名不正确");
		}

		String md5Password = Md5.getMd5(password);
		if(manager.getPassword().equals(md5Password)){
			CompanyResult result=new CompanyResult();
			result.setId(company.getId());
			result.setName(company.getName());
			result.setCode(company.getCode());
			result.setAddress(company.getAddress());
			result.setFace(company.getLogo());
			result.setManagerId(manager.getId());
			result.setUserName(manager.getUserName());
			result.setUserId(manager.getUserId());
			result.setToken(Authorize.getCompanyToken(manager, code,1));
			return result;
		}else {
			throw new PasswordException("密码错误");
		}
	}
	//退出登录
	public int loginOut() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		request.getSession().removeAttribute(Constants.CompanyPre+Constants.CompanyLoginCacheKey);
		return 0;
	}
	public int addManager(Manager manager) throws NameException {
		Manager one = managerDao.findByName(manager.getName());
		if(one!=null){
			throw new NameException("该用户已存在");
		}
		CompanyResult company = companyDAO.findById(manager.getCompanyId());
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
		if(manager.getPassword()!=null&&!manager.getPassword().isEmpty()){	
			manager.setPassword(Md5.getMd5(manager.getPassword()));
		}
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
	/// <summary>
	/// 验证token是否有效
	/// </summary>
	/// <param name="token">Token数据.</param>
	/// <returns></returns>
	public CompanyResult verifyToken(String token)
	{
		if(token.isEmpty()) return null;

		String stoken;
		try {
			stoken = AES.aesDecrypt(token, "fire");
			String[] ts = stoken.split("|");
			if(ts.length!=7) return null;
			// 0: tokenkey
			// 1: 授权host
			// 2: 会员id
			// 3: 用户名
			// 4: 密码 
			// 5: 店铺Id
			// 6: 到期时间

			if (ts[1] != Utils.getHost()) return null;


			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Date dt1=df.parse(ts[6]);
			// token 授权已经过期
			if (dt1.getTime()-new Date().getTime()<0) return null;
			return login(ts[4],ts[5],ts[2]);


		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return null;
	}
	public int updatePwd(String oldPwd,String pwd,int managerId){
		Manager manager = managerDao.findById(managerId);
		if(manager==null){
			throw new NameException("不存在该用户");
		}
		if(!Md5.getMd5(oldPwd).equals(manager.getPassword())){
			throw new PasswordException("原密码不正确");
		}
		manager.setPassword(Md5.getMd5(pwd));
		return managerDao.updateManager(manager);
	}
}
