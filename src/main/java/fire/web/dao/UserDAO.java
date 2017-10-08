package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.common.entity.User;


public interface UserDAO {
	//通过username查询User
	public User findByUserName(String username);
	//查询User集合
	public List<User> findAll();
	//修改
	public int updateUser(User user);
	//通过email查询
	public User findByEmail(
			@Param("Id")int Id,
			@Param("email")String email);
	//通过id查询
	public User findById(int Id);
	//通过Mobile查询
	public User findByMobile(
			@Param("Id")int Id,
			@Param("mobile")String mobile);
	//添加
	public int addUser(User user);
	//删除
	public int delete(Integer Id);
	//查询计数
	public int findUserCount();
	//分页查询
	public List<User> findByLimit(
			@Param("begin") Integer begin,
			@Param("size") Integer size
			);
	//修改状态值
	public int updateStatus(User user);
}
