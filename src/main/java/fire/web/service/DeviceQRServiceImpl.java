package fire.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fire.common.entity.DeviceQR;
import fire.common.entity.DeviceQRResult;
import fire.web.dao.DeviceDAO;
import fire.web.dao.DeviceQRDAO;
import fire.web.utils.PageInfo;
@Service("deviceQRService")
public class DeviceQRServiceImpl implements DeviceQRService{
	@Resource
	private DeviceQRDAO deviceQRDAO;
	@Resource
	private DeviceDAO deviceDAO;
	public int addDeviceQR(List<DeviceQR> list) throws NameException {
		
		return deviceQRDAO.addDeviceQR(list);
		
	}

	public DeviceQR getDeviceQR(int id) {
		return deviceQRDAO.findById(id);
	}
	public int deleteDeviceQR(int id) {
		return deviceQRDAO.delete(id);
	}

	public PageInfo<DeviceQRResult> getDeviceQRPage(int begin, int size) {
		PageInfo<DeviceQRResult> pi = new PageInfo<DeviceQRResult>();
		pi.setPageIndex(begin);
		pi.setPageSize(size);
		pi.setCount(deviceQRDAO.findDeviceQRCount());
		pi.setList(deviceQRDAO.findByLimit(pi.getBegin(), size));
		return pi;
	}

	public List<DeviceQR> getDeviceQRList(String batch) {
		return deviceQRDAO.getQRList(batch);
	}

	public PageInfo<DeviceQRResult> search(String model,Integer deviceTypeId, int begin, int size) {
		PageInfo<DeviceQRResult> pi = new PageInfo<DeviceQRResult>();
		pi.setPageIndex(begin);
		pi.setPageSize(size);
		pi.setCount(deviceQRDAO.findDeviceQRCount());
		pi.setList(deviceQRDAO.search(model,deviceTypeId,pi.getBegin(), size));
		return pi;
	}

	public DeviceQR getDeviceQRByCode(String code) {
		DeviceQR dq = deviceQRDAO.findByCode(code);
		return dq;
	}

}
