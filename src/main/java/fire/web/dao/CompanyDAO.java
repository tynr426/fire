package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.web.entity.Company;
import fire.web.entity.CompanyResult;


public interface CompanyDAO {
	//通过Companyname查询Company
	public Company getCompanyByCode(String code);
	//查询Company集合
	public List<Company> findAll();
	//修改
	public int updateCompany(Company company);
	//通过id查询
	public CompanyResult findById(int Id);
	//通过Tel查询
	public Company findByTel(
			@Param("Id")int Id,
			@Param("mobile")String mobile);
	//添加
	public int addCompany(Company company);
	//删除
	public int delete(Integer Id);
	//查询计数
	public int findCompanyCount();
	//分页查询
	public List<Company> findByLimit(
			@Param("begin") Integer begin,
			@Param("size") Integer size
			);
	//修改状态值
	public int updateStatus(Company company);
}
