package fire.web.service;


import java.util.List;

import fire.common.entity.CompanyResult;
import fire.common.entity.Company;
import fire.web.utils.PageInfo;

public interface CompanyService {
	public List<Company> showCompany();
	public int addCompany(CompanyResult result)throws NameException;
	public int updateCompany(CompanyResult result);
	public CompanyResult getCompany(int Id);
	public int deleteCompany(Integer Id);
	public PageInfo<Company> getCompanyPage(int index,int size);
	public int updateStatus(Integer id,int status);
}
