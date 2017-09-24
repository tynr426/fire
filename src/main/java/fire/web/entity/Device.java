package fire.web.entity;

import java.sql.Timestamp;

public class Device {
	private int Id;
	private int DeviceTypeId;
	private String Model;
	private String Manufacturer;
	private String Spec;
	private Timestamp UseTime;
	private Timestamp AddTime;
	private int Buildings;
	private int Floor;
	private String Position;
	private String Passageway;
	private String Detail;
	public Device(){
		
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getDeviceTypeId() {
		return DeviceTypeId;
	}
	public void setDeviceTypeId(int deviceTypeId) {
		DeviceTypeId = deviceTypeId;
	}
	public String getModel() {
		return Model;
	}
	public void setModel(String model) {
		Model = model;
	}
	public String getManufacturer() {
		return Manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		Manufacturer = manufacturer;
	}
	public String getSpec() {
		return Spec;
	}
	public void setSpec(String spec) {
		Spec = spec;
	}
	public Timestamp getUseTime() {
		return UseTime;
	}
	public void setUseTime(Timestamp useTime) {
		UseTime = useTime;
	}
	public Timestamp getAddTime() {
		return AddTime;
	}
	public void setAddTime(Timestamp addTime) {
		AddTime = addTime;
	}
	public int getBuildings() {
		return Buildings;
	}
	public void setBuildings(int buildings) {
		Buildings = buildings;
	}
	public int getFloor() {
		return Floor;
	}
	public void setFloor(int floor) {
		Floor = floor;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public String getPassageway() {
		return Passageway;
	}
	public void setPassageway(String passageway) {
		Passageway = passageway;
	}
	public String getDetail() {
		return Detail;
	}
	public void setDetail(String detail) {
		Detail = detail;
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
		Device other = (Device) obj;
		if (Id != other.Id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Device [Id=" + Id + ", DeviceTypeId=" + DeviceTypeId + ", Model=" + Model + ", Manufacturer="
				+ Manufacturer + ", Spec=" + Spec + ", UseTime=" + UseTime + ", AddTime=" + AddTime + ", Buildings="
				+ Buildings + ", Floor=" + Floor + ", Position=" + Position + ", Passageway=" + Passageway + ", Detail="
				+ Detail + "]";
	}
	
}
