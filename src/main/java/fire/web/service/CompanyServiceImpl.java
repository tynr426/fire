package fire.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fire.web.dao.CompanyDAO;
import fire.web.dao.ManagerDAO;
import fire.web.entity.Company;
import fire.web.entity.CompanyResult;
import fire.web.entity.Manager;
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
			throw new NameException("�û��Ѵ���");
		}
		result.setStatus(1);
		result.setSN(Md5.createID());
		int n = companyDAO.addCompany(result);
		Manager manager = new Manager();
		manager.setUserName(result.getUserName());
		manager.setPassword(result.getPassword());
		manager.setUserId(0);
		if(n>0){			
			manager.setCompanyId(result.getId());	
		}
		Manager entity=managerDAO.getManagerByCompanyId(manager.getCompanyId());
		if(entity!=null)  throw new  NameException("�û��Ѿ�����");
		if(manager.getPassword()==""||manager.getUserName()=="") throw new NameException("�û��������벻��Ϊ��");
		manager.setPassword(Md5.getMd5(manager.getPassword()));
		return managerDAO.addManager(manager);
	}
	@Transactional
	public int updateCompany(CompanyResult result) {
		CompanyResult one = companyDAO.findById(result.getId());
		if(one==null){
			throw new NameException("�û�������");
		}
		int n = companyDAO.updateCompany(result);
		if(result.getPassword()!=null){		
			Manager mg = new Manager();
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
			throw new NameException("ID����Ϊ��");
		}
		CompanyResult result = companyDAO.findById(Id);
		if(result==null){
			throw new NameException("�û�������");
		}
		int n = companyDAO.delete(Id);
		return managerDAO.delete(result.getId());
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
		Company company = companyDAO.findById(id);
		if(company==null){
			throw new NameException("id������");
		}
		company.setStatus(status);
		int n = companyDAO.updateStatus(company);
		return n;
	}
}
