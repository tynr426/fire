package fire.web.entity;

public class DeviceParameterValue {
private int Id;
private int DeviceId;
private int DeviceTypeId;
private int ParameterId;
private String Value;
private String Description;
public int getId() {
	return Id;
}
public void setId(int id) {
	Id = id;
}
public int getDeviceId() {
	return DeviceId;
}
public void setDeviceId(int deviceId) {
	DeviceId = deviceId;
}
public int getDeviceTypeId() {
	return DeviceTypeId;
}
public void setDeviceTypeId(int deviceTypeId) {
	DeviceTypeId = deviceTypeId;
}
public int getParameterId() {
	return ParameterId;
}
public void setParameterId(int parameterId) {
	ParameterId = parameterId;
}
public String getValue() {
	return Value;
}
public void setValue(String value) {
	Value = value;
}
public String getDescription() {
	return Description;
}
public void setDescription(String description) {
	Description = description;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + Id;
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	DeviceParameterValue other = (DeviceParameterValue) obj;
	if (Id != other.Id)
		return false;
	return true;
}
@Override
public String toString() {
	return "DeviceParameterValue [Id=" + Id + ", DeviceId=" + DeviceId + ", DeviceTypeId=" + DeviceTypeId
			+ ", ParameterId=" + ParameterId + ", Value=" + Value + ", Description=" + Description + "]";
}

}
