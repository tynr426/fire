package fire.web.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import fire.web.dao.DTPDAO;
import fire.web.entity.Devicetypeparameter;
import fire.web.utils.PageInfo;
@Service("devicetypeparameterService")
public class DevicetypeparameterServiceImpl implements DevicetypeparameterService{
	@Resource
	private DTPDAO dTPDAO;
	public List<Devicetypeparameter> showDevicetypeparameter() {
		List<Devicetypeparameter> list = dTPDAO.findAll();
		return list;
	}

	public int addDevicetypeparameter(Devicetypeparameter dtp) throws NameException {
		Devicetypeparameter one = dTPDAO.getDTPByDescription(dtp.getDescription(), dtp.getId());
		if(one!=null){
			throw new NameException("�������Ѵ���");
		}
		dtp.setStatus(1);
		dtp.setDeviceTypeId(1);
		int n = dTPDAO.addDTP(dtp);
		return n;
	}

	public int updateDevicetypeparameter(Devicetypeparameter dtp) {
		Devicetypeparameter one = dTPDAO.getDTPByDescription(dtp.getDescription(), dtp.getId());
		if(one!=null){
			throw new NameException("�������Ѵ���");
		}
		int n = dTPDAO.updateDTP(dtp);
		return n;
	}

	public Devicetypeparameter getDevicetypeparameter(int id) {
		Devicetypeparameter devicetypeparameter = dTPDAO.findById(id);
		return devicetypeparameter;
	}

	public int deleteDevicetypeparameter(Integer id) {
		if(id==null){
			throw new NameException("ID����Ϊ��");
		}
		Devicetypeparameter devicetypeparameter = dTPDAO.findById(id);
		if(devicetypeparameter==null){
			throw new NameException("�û�������");
		}
		int n = dTPDAO.delete(id);
		return n;
	}

	public PageInfo<Devicetypeparameter> getDevicetypeparameterPage(int index, int size) {
		PageInfo<Devicetypeparameter> pi = new PageInfo<Devicetypeparameter>();
		pi.setPageIndex(index);
		pi.setPageSize(size);
		pi.setCount(dTPDAO.findDevicetypeparameterCount());
		pi.setList(dTPDAO.findByLimit(pi.getBegin(), size));
		return pi;
	}

	public int updateStatus(Integer id, int status) {
		Devicetypeparameter devicetypeparameter = dTPDAO.findById(id);
		if(devicetypeparameter==null){
			throw new NameException("id������");
		}
		devicetypeparameter.setStatus(status);
		int n = dTPDAO.updateStatus(devicetypeparameter);
		return n;
	}

}