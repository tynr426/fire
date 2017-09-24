package fire.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.web.entity.Devicetypeparameter;


public interface DTPDAO {
	//通过DTPname查询DTP
	public Devicetypeparameter getDTPByDescription(
			@Param("description")String description,
			@Param("id")int id );
	//查询DTP集合
	public List<Devicetypeparameter> findAll();
	//修改
	public int updateDevicetypeparameter(Devicetypeparameter dtp);
	//通过id查询
	public Devicetypeparameter findById(int Id);
	//添加
	public int addDTP(Devicetypeparameter dtp);
	//删除
	public int delete(Integer Id);
	//查询计数
	public int findDevicetypeparameterCount();
	//分页查询
	public List<Devicetypeparameter> findByLimit(
			@Param("deviceTypeId")int deviceTypeId,
			@Param("begin") Integer begin,
			@Param("size") Integer size
			);
	//修改状态值
	public int updateStatus(Devicetypeparameter dtp);
}
