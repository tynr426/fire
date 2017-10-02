package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.web.entity.Manager;


public interface ManagerDAO {
	public Manager getManagerByCompanyId(int companyId);
	public int deleteByCompanyId(int companyId);
	public Manager findByUserName(String username);
	public Manager findByName(String name);
	public Manager findNameIsExist(
			@Param("name")String name,
			@Param("id")int id);
	//�޸�
	public int updateManager(Manager Manager);
	//ͨ��id��ѯ
	public Manager findById(int Id);
	//���
	public int addManager(Manager Manager);
	//ɾ��
	public int delete(Integer Id);
	//��ѯ����
	public int findManagerCount();
	//��ҳ��ѯ
	public List<Manager> findByLimit(
			@Param("companyId")int companyId,
			@Param("begin") Integer begin,
			@Param("size") Integer size
			);
	//�޸�״ֵ̬
	public int updateStatus(Manager Manager);
}
