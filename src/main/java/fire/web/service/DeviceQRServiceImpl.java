package fire.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fire.common.entity.AssignmentResult;
import fire.common.entity.DeviceQR;
import fire.common.entity.DeviceQRResult;
import fire.common.entity.ScanInfo;
import fire.web.dao.AssignmentDAO;
import fire.web.dao.DeviceDAO;
import fire.web.dao.DeviceQRDAO;
import fire.web.utils.PageInfo;
@Service("deviceQRService")
public class DeviceQRServiceImpl implements DeviceQRService{
	@Resource
	private DeviceQRDAO deviceQRDAO;
	@Resource
	private AssignmentDAO assignmentDAO;
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

	public ScanInfo getDeviceQRByCode(String code,Integer toManagerId) {
		ScanInfo re = deviceQRDAO.findByCode(code);
 		if(re!=null&&re.getDeviceId()!=null&&re.getDeviceId()>0){
			AssignmentResult assignment= assignmentDAO.getAssignmentByDeviceId(re.getDeviceId(), toManagerId);
			if(assignment!=null){				
				re.setAssignmentId(assignment.getId());
				re.setAssignmentDes(assignment.getDescription());
			}
		}
		return re;
	}

}
