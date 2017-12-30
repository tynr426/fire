package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.common.entity.Manager;


public interface ManagerDAO {
	public List<Manager> getManagerByCompanyId(int companyId);
	public int deleteByCompanyId(int companyId);
	public Manager findByUserName(String username);
	public Manager findByName(String name);
	public Manager findNameIsExist(
			@Param("name")String name,
			@Param("id")int id);
	//修改
	public int updateManager(Manager Manager);
	//通过id查询
	public Manager findById(int Id);
	//添加
	public int addManager(Manager Manager);
	//删除
	public int delete(Integer Id);
	public int deleteResult(Integer companyId);
	//查询计数
	public int findManagerCount(int companyId);
	//分页查询
	public List<Manager> findByLimit(
			@Param("companyId")int companyId,
			@Param("begin") Integer begin,
			@Param("size") Integer size
			);
	//修改状态值
	public int updateStatus(Manager Manager);
}
