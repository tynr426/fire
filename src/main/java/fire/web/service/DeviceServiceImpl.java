package fire.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fire.web.dao.DeviceDAO;
import fire.web.entity.Device;
import fire.web.utils.PageInfo;
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService{
	@Resource
	private DeviceDAO deviceDAO;
	
	public List<Device> findAll() {
		List<Device> list = deviceDAO.findAll();
		return list;
	}

	public int addDevice(Device device){
		device.setDeviceTypeId(1);
		int n = deviceDAO.addDevice(device);
		return n;
	}

	public int updateDevice(Device device) {
		int n = deviceDAO.updateDevice(device);
		return n;
	}

	public Device getDevice(int id) {
		Device device = deviceDAO.findById(id);
		return device;
	}

	public int deleteDevice(Integer id) {
		if(id==null){
			throw new NameException("ID不能为空");
		}
		Device device = deviceDAO.findById(id);
		if(device==null){
			throw new NameException("用户不存在");
		}
		int n = deviceDAO.delete(id);
		return n;
	}

	public PageInfo<Device> getDevicePage(int index, int size) {
		PageInfo<Device> pi = new PageInfo<Device>();
		pi.setPageIndex(index);
		pi.setPageSize(size);
		pi.setCount(deviceDAO.findDeviceCount());
		pi.setList(deviceDAO.findByLimit(pi.getBegin(), size));
		return pi;
	}

}
