package fire.web.entity;

import java.util.List;

public class DeviceResult extends Device{
	private String Name;
	private List<DeviceParameterValue> list;
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public List<DeviceParameterValue> getList() {
		return list;
	}

	public void setList(List<DeviceParameterValue> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "DeviceResult [Name=" + Name + ", list=" + list + ", getCompanyId()=" + getCompanyId() + ", getId()="
				+ getId() + ", getDeviceTypeId()=" + getDeviceTypeId() + ", getModel()=" + getModel()
				+ ", getManufacturer()=" + getManufacturer() + ", getSpec()=" + getSpec() + ", getUseTime()="
				+ getUseTime() + ", getAddTime()=" + getAddTime() + ", getBuildings()=" + getBuildings()
				+ ", getFloor()=" + getFloor() + ", getPosition()=" + getPosition() + ", getPassageway()="
				+ getPassageway() + ", getDetail()=" + getDetail() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + "]";
	}
	
}
