package fire.web.entity;



import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Device {
	private int Id;
	private int DeviceTypeId;
	private String Model;
	private String Manufacturer;
	private String Spec;
	private Date UseTime;
	private Date AddTime;
	private Integer Buildings;
	private Integer Floor;
	private String Position;
	private String Passageway;
	private String Detail;
	private int ManagerId;
	private int CompanyId;
	public int getCompanyId() {
		return CompanyId;
	}
	public void setCompanyId(int companyId) {
		CompanyId = companyId;
	}
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
	public Date getUseTime() {
		return UseTime;
	}
	public void setUseTime(Date useTime) {
		UseTime = useTime;
	}
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	public Date getAddTime() {
		return AddTime;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setAddTime(Date addTime) {
		AddTime = addTime;
	}
	public Integer getBuildings() {
		return Buildings;
	}
	public void setBuildings(Integer buildings) {
		Buildings = buildings;
	}
	public Integer getFloor() {
		return Floor;
	}
	public void setFloor(Integer floor) {
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
				+ Detail + ", ManagerId=" + ManagerId + ", CompanyId=" + CompanyId + "]";
	}
	
	
}
