package fire.web.service;




import java.util.List;

import org.apache.ibatis.annotations.Param;

import fire.common.entity.DeviceQR;
import fire.common.entity.DeviceQRResult;
import fire.common.entity.DeviceResult;
import fire.common.entity.ScanInfo;
import fire.web.utils.PageInfo;

public interface DeviceQRService {
	public int addDeviceQR(List<DeviceQR> list)throws NameException;
	public DeviceQR getDeviceQR(int id);
	public ScanInfo getDeviceQRByCode(String code,Integer toManagerId);
	public int deleteDeviceQR(int id);
	public PageInfo<DeviceQRResult> getDeviceQRPage(int begin,int size);
	public PageInfo<DeviceQRResult> search(String model,Integer deviceTypeId,int begin,int size);
	public List<DeviceQR> getDeviceQRList(String batch);
	public int getQrByCodeOrDeviceId( String code,Integer deviceId,Integer deviceTypeId);
	public int bind(String code,Integer deviceId);
}
