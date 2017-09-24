package fire.web.entity;

import java.util.Date;

public class Company {
	private int Id;
	private String Name;
	private String Code;
	private String Logo;
	private String Intro;
	private Integer Industry;
	private String Province;
	private String City;
	private String Area;
	private String Address;
	private Integer Passed;
	private Integer Status;
	private Integer Reorder;
	private Date AddTime;
	private String Remark;
	private Integer LevelId;
	private String Tel;
	private String SN;
	private String Atten;
	public Company(){
		
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
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
	public String getLogo() {
		return Logo;
	}
	public void setLogo(String logo) {
		Logo = logo;
	}
	public String getIntro() {
		return Intro;
	}
	public void setIntro(String intro) {
		Intro = intro;
	}
	public Integer getIndustry() {
		return Industry;
	}
	public void setIndustry(Integer industry) {
		Industry = industry;
	}
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String adress) {
		Address = adress;
	}
	public Integer getPassed() {
		return Passed;
	}
	public void setPassed(Integer passed) {
		Passed = passed;
	}
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
	}
	public Integer getReorder() {
		return Reorder;
	}
	public void setReorder(Integer reorder) {
		Reorder = reorder;
	}
	public Date getAddTime() {
		return AddTime;
	}
	public void setAddTime(Date addTime) {
		AddTime = addTime;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public Integer getLevelId() {
		return LevelId;
	}
	public void setLevelId(Integer levelId) {
		LevelId = levelId;
	}
	
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public String getSN() {
		return SN;
	}
	public void setSN(String sN) {
		SN = sN;
	}
	
	public String getAtten() {
		return Atten;
	}
	public void setAtten(String atten) {
		Atten = atten;
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
		Company other = (Company) obj;
		if (Id != other.Id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Company [Id=" + Id + ", Name=" + Name + ", Code=" + Code + ", Logo=" + Logo + ", Intro=" + Intro
				+ ", Industry=" + Industry + ", Province=" + Province + ", City=" + City + ", Area=" + Area
				+ ", Address=" + Address + ", Passed=" + Passed + ", Status=" + Status + ", Reorder=" + Reorder
				+ ", AddTime=" + AddTime + ", Remark=" + Remark + ", LevelId=" + LevelId + ", Tel=" + Tel + ", SN=" + SN
				+ ", Atten=" + Atten + "]";
	}
}
