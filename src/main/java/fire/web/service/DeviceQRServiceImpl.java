package fire.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fire.common.entity.DeviceQR;
import fire.web.dao.DeviceQRDAO;
import fire.web.utils.PageInfo;
@Service("deviceQRService")
public class DeviceQRServiceImpl implements DeviceQRService{
	@Resource
	private DeviceQRDAO deviceQRDAO;
	
	public int addDeviceQR(List<DeviceQR> list) throws NameException {
		
		return deviceQRDAO.addDeviceQR(list);
		
	}

	public DeviceQR getDeviceQR(int id) {
		return deviceQRDAO.findById(id);
	}

	public int deleteDeviceQR(int id) {
		return deviceQRDAO.delete(id);
	}

	public PageInfo<DeviceQR> getDeviceQRPage(int begin, int size) {
		PageInfo<DeviceQR> pi = new PageInfo<DeviceQR>();
		pi.setPageIndex(begin);
		pi.setPageSize(size);
		pi.setCount(deviceQRDAO.findDeviceQRCount());
		pi.setList(deviceQRDAO.findByLimit(begin, size));
		return pi;
	}

}
