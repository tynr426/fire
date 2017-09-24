package fire.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fire.web.dao.DeviceTypeDAO;
import fire.web.entity.DeviceType;
import fire.web.utils.PageInfo;
@Service("deviceTypeService")
public class DeviceTypeServiceImpl implements DeviceTypeService{
	@Resource
	private DeviceTypeDAO deviceTypeDAO;
	
	public List<DeviceType> findAll() {
		List<DeviceType> list = deviceTypeDAO.findAll();
		return list;
	}

	public int addDeviceType(DeviceType dt) throws NameException {
		DeviceType one = deviceTypeDAO.getDeviceType(dt.getName());
		if(one!=null){
			throw new NameException("该类型已存在");
		}
		dt.setStatus(1);
		int n = deviceTypeDAO.addDeviceType(dt);
		return n;
	}

	public int updateDeviceType(DeviceType dt) {
		DeviceType one = deviceTypeDAO.findById(dt.getId());
		if(one==null){
			throw new NameException("该类型不存在");
		}
		int n = deviceTypeDAO.updateDeviceType(dt);
		return n;
	}

	public DeviceType getDeviceType(int id) {
		DeviceType deviceType = deviceTypeDAO.findById(id);
		return deviceType;
	}

	public int deleteDeviceType(Integer id) {
		if(id==null){
			throw new NameException("ID不能为空");
		}
		DeviceType deviceType = deviceTypeDAO.findById(id);
		if(deviceType==null){
			throw new NameException("用户不存在");
		}
		int n = deviceTypeDAO.delete(id);
		return n;
	}

	public PageInfo<DeviceType> getDeviceTypePage(int index, int size) {
		PageInfo<DeviceType> pi = new PageInfo<DeviceType>();
		pi.setPageIndex(index);
		pi.setPageSize(size);
		pi.setCount(deviceTypeDAO.findDeviceTypeCount());
		pi.setList(deviceTypeDAO.findByLimit(pi.getBegin(), size));
		return pi;
	}

	public int updateStatus(Integer id, int status) {
		DeviceType deviceType = deviceTypeDAO.findById(id);
		if(deviceType==null){
			throw new NameException("id不存在");
		}
		deviceType.setStatus(status);
		int n = deviceTypeDAO.updateStatus(deviceType);
		return n;
	}

}
