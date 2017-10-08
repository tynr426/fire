package fire.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fire.common.entity.CompanyResult;
import fire.web.dao.CompanyDAO;
import fire.web.dao.ManagerDAO;
import fire.common.entity.*;
import fire.web.utils.Md5;
import fire.web.utils.PageInfo;
@Service("companyService")
public class CompanyServiceImpl implements CompanyService{
	@Resource
	private CompanyDAO companyDAO;
	@Resource
	private ManagerDAO managerDAO;
	public List<Company> showCompany() {
		List<Company> list = companyDAO.findAll();
		return list;
	}
	@Transactional
	public int addCompany(CompanyResult result) throws NameException {
		Company one = companyDAO.getCompanyByCode(result.getCode());
		if(one!=null){
			throw new NameException("用户已存在");
		}
		result.setStatus(1);
		result.setSN(Md5.createID());
		int n = companyDAO.addCompany(result);
		if(result.getPassword()==null||result.getPassword().isEmpty()){
			result.setPassword("000000");
		}
		Manager manager = new Manager();
		manager.setUserName(result.getUserName());
		manager.setPassword(result.getPassword());
		manager.setStatus(1);
		manager.setUserId(0);
		if(n>0){			
			manager.setCompanyId(result.getId());	
		}
		Manager entity=managerDAO.getManagerByCompanyId(manager.getCompanyId());
		if(entity!=null)  throw new  NameException("用户已经存在");
		if(manager.getPassword()==""||manager.getUserName()=="") throw new NameException("用户名或密码不能为空");
		manager.setPassword(Md5.getMd5(manager.getPassword()));
		return managerDAO.addManager(manager);
	}
	@Transactional
	public int updateCompany(CompanyResult result) {
		CompanyResult one = companyDAO.findById(result.getId());
		if(one==null){
			throw new NameException("用户不存在");
		}
		int n = companyDAO.updateCompany(result);
		if(result.getPassword()!=null&&!result.getPassword().isEmpty()){		
			Manager mg = new Manager();
			mg.setId(one.getManagerId());
			mg.setCompanyId(result.getId());
			mg.setPassword(Md5.getMd5(result.getPassword()));
			managerDAO.updateManager(mg);
		}
		return n;
	}

	public CompanyResult getCompany(int Id) {
		CompanyResult result = companyDAO.findById(Id);
		return result;
	}
	@Transactional
	public int deleteCompany(Integer Id) {
		if(Id==null){
			throw new NameException("ID不能为空");
		}
		Company result = companyDAO.getCompanyById(Id);
		if(result==null){
			throw new NameException("用户不存在");
		}
		int n = companyDAO.delete(Id);
		return managerDAO.deleteResult(Id);
	}

	public PageInfo<Company> getCompanyPage(int index, int size) {
		PageInfo<Company> pi = new PageInfo<Company>();
		pi.setPageIndex(index);
		pi.setPageSize(size);
		pi.setCount(companyDAO.findCompanyCount());
		pi.setList(companyDAO.findByLimit(pi.getBegin(), size));
		return pi;
	}

	public int updateStatus(Integer id, int status) {
		Company company = companyDAO.getCompanyById(id);
		if(company==null){
			throw new NameException("id不存在");
		}
		company.setStatus(status);
		int n = companyDAO.updateStatus(company);
		return n;
	}
}
