package fire.web.entity;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DeviceType {
	private int Id;
	private String Name;
	private Integer Status;
	private Date UseTime;
	public DeviceType(){
		
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
	}
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
	public Date getUseTime() {
		return UseTime;
	}
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public void setUseTime(Date useTime) {
		UseTime = useTime;
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
		DeviceType other = (DeviceType) obj;
		if (Id != other.Id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DeviceType [Id=" + Id + ", Name=" + Name + ", Status=" + Status + ", UseTime=" + UseTime + "]";
	}
	
	
}
