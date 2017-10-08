package fire.web.service;


import java.util.List;

import fire.common.entity.DeviceType;
import fire.common.entity.DeviceTypeResult;
import fire.web.utils.PageInfo;

public interface DeviceTypeService {
	public List<DeviceTypeResult> findAll();
	public List<DeviceTypeResult> findDeviceTypeResult();
	public int addDeviceType(DeviceType dt)throws NameException;
	public int updateDeviceType(DeviceType dt);
	public DeviceType getDeviceType(int id);
	public int deleteDeviceType(Integer id);
	public PageInfo<DeviceType> getDeviceTypePage(int index,int size);
	public int updateStatus(Integer id,int status);
}
