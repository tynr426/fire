package fire.web.service;




import java.util.List;

import fire.common.entity.DeviceQR;
import fire.web.utils.PageInfo;

public interface DeviceQRService {
	public int addDeviceQR(List<DeviceQR> list)throws NameException;
	public DeviceQR getDeviceQR(int id);
	public int deleteDeviceQR(int id);
	public PageInfo<DeviceQR> getDeviceQRPage(int begin,int size);
	public List<DeviceQR> getDeviceQRList(String batch);
}
