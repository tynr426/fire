package fire.web.service;


import java.util.List;

import fire.web.entity.Device;
import fire.web.utils.PageInfo;

public interface DeviceService {
	public List<Device> findAll();
	public int addDevice(Device device)throws NameException;
	public int updateDevice(Device device);
	public Device getDevice(int id);
	public int deleteDevice(Integer id);
	public PageInfo<Device> getDevicePage(int index,int size);
}
