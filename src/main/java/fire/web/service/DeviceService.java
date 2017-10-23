package fire.web.service;




import fire.common.entity.DeviceResult;
import fire.web.utils.PageInfo;

public interface DeviceService {
	public int addDevice(DeviceResult device)throws NameException;
	public int updateDevice(DeviceResult device);
	public DeviceResult getDevice(int id);
	public int deleteDevice(int id);
	public PageInfo<DeviceResult> getDevicePage(int companyId,int index,int size);
	
}
