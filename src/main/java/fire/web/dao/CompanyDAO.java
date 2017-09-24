package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.web.entity.Company;
import fire.web.entity.CompanyResult;


public interface CompanyDAO {
	//ͨ��Companyname��ѯCompany
	public Company getCompanyByCode(String code);
	//��ѯCompany����
	public List<Company> findAll();
	//�޸�
	public int updateCompany(Company company);
	//ͨ��id��ѯ
	public CompanyResult findById(int Id);
	//ͨ��Tel��ѯ
	public Company findByTel(
			@Param("Id")int Id,
			@Param("mobile")String mobile);
	//���
	public int addCompany(Company company);
	//ɾ��
	public int delete(Integer Id);
	//��ѯ����
	public int findCompanyCount();
	//��ҳ��ѯ
	public List<Company> findByLimit(
			@Param("begin") Integer begin,
			@Param("size") Integer size
			);
	//�޸�״ֵ̬
	public int updateStatus(Company company);
}
