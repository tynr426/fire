package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.web.entity.User;


public interface UserDAO {
	//ͨ��username��ѯUser
	public User findByUserName(String username);
	//��ѯUser����
	public List<User> findAll();
	//�޸�
	public int updateUser(User user);
	//ͨ��email��ѯ
	public User findByEmail(
			@Param("Id")int Id,
			@Param("email")String email);
	//ͨ��id��ѯ
	public User findById(int Id);
	//ͨ��Mobile��ѯ
	public User findByMobile(
			@Param("Id")int Id,
			@Param("mobile")String mobile);
	//���
	public int addUser(User user);
	//ɾ��
	public int delete(Integer Id);
	//��ѯ����
	public int findUserCount();
	//��ҳ��ѯ
	public List<User> findByLimit(
			@Param("begin") Integer begin,
			@Param("size") Integer size
			);
	//�޸�״ֵ̬
	public int updateStatus(User user);
}
