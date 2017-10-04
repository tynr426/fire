package fire.web.entity;



public class CompanyResult extends Company{
	private String UserName;
	private String Password;
	private int CompanyId;
	private int ManagerId;
	public int getManagerId() {
		return ManagerId;
	}
	public void setManagerId(int managerId) {
		ManagerId = managerId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public int getCompanyId() {
		return CompanyId;
	}
	public void setCompanyId(int companyId) {
		CompanyId = companyId;
	}
	@Override
	public String toString() {
		return "CompanyResult [UserName=" + UserName + ", Password=" + Password + ", CompanyId=" + CompanyId
				+ ", ManagerId=" + ManagerId + ", getId()=" + getId() + ", getName()=" + getName() + ", getCode()="
				+ getCode() + ", getLogo()=" + getLogo() + ", getIntro()=" + getIntro() + ", getIndustry()="
				+ getIndustry() + ", getProvince()=" + getProvince() + ", getCity()=" + getCity() + ", getArea()="
				+ getArea() + ", getAddress()=" + getAddress() + ", getPassed()=" + getPassed() + ", getStatus()="
				+ getStatus() + ", getReorder()=" + getReorder() + ", getAddTime()=" + getAddTime() + ", getRemark()="
				+ getRemark() + ", getLevelId()=" + getLevelId() + ", getTel()=" + getTel() + ", getSN()=" + getSN()
				+ ", getAtten()=" + getAtten() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + "]";
	}

	
}
