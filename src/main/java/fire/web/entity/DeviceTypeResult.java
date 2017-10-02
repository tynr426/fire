package fire.web.entity;

import java.util.ArrayList;
import java.util.List;

public class DeviceTypeResult extends DeviceType{
	private List<Devicetypeparameter> list=new ArrayList<Devicetypeparameter>();

	public List<Devicetypeparameter> getList() {
		return list;
	}

	public void setList(List<Devicetypeparameter> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "DeviceTypeResult [list=" + list + ", getId()=" + getId() + ", getName()=" + getName() + ", getStatus()="
				+ getStatus() + ", getUseTime()=" + getUseTime() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + "]";
	}

	
	
}
