package fire.web.entity;

import java.io.Serializable;
import java.util.Date;

public class Manager  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7301234958351142733L;
	private int Id;
	private int CompanyId;
	private String UserName;
	private String Password;
	private String Position;
	private String Name;
	private String NickName;
	private String Face;
	private String Email;
	private String Mobile;
	private Integer Errors;
	private Date ErrorTime;
	private String LoginIP;
	private Date LoginTime;
	private String LastLoginIP;
	private Date LastLoginTime;
	private Integer LoginCount;
	private String Question;
	private String Answer;
	private Integer Reorder;
	private Integer Status;
	private Date AddTime;
	private String Remark;
	private int UserId;
	private String Token;
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	public Manager(){
		
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getCompanyId() {
		return CompanyId;
	}
	public void setCompanyId(int companyId) {
		CompanyId = companyId;
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
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getNickName() {
		return NickName;
	}
	public void setNickName(String nickName) {
		NickName = nickName;
	}
	public String getFace() {
		return Face;
	}
	public void setFace(String face) {
		Face = face;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public Integer getErrors() {
		return Errors;
	}
	public void setErrors(Integer errors) {
		Errors = errors;
	}
	public Date getErrorTime() {
		return ErrorTime;
	}
	public void setErrorTime(Date errorTime) {
		ErrorTime = errorTime;
	}
	public String getLoginIP() {
		return LoginIP;
	}
	public void setLoginIP(String loginIP) {
		LoginIP = loginIP;
	}
	public Date getLoginTime() {
		return LoginTime;
	}
	public void setLoginTime(Date loginTime) {
		LoginTime = loginTime;
	}
	public String getLastLoginIP() {
		return LastLoginIP;
	}
	public void setLastLoginIP(String lastLoginIP) {
		LastLoginIP = lastLoginIP;
	}
	public Date getLastLoginTime() {
		return LastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		LastLoginTime = lastLoginTime;
	}
	public Integer getLoginCount() {
		return LoginCount;
	}
	public void setLoginCount(Integer loginCount) {
		LoginCount = loginCount;
	}
	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String question) {
		Question = question;
	}
	public String getAnswer() {
		return Answer;
	}
	public void setAnswer(String answer) {
		Answer = answer;
	}
	public Integer getReorder() {
		return Reorder;
	}
	public void setReorder(Integer reorder) {
		Reorder = reorder;
	}
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
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
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
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
		Manager other = (Manager) obj;
		if (Id != other.Id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Manager [Id=" + Id + ", CompanyId=" + CompanyId + ", UserName=" + UserName + ", Password=" + Password
				+ ", Position=" + Position + ", Name=" + Name + ", NickName=" + NickName + ", Face=" + Face + ", Email="
				+ Email + ", Mobile=" + Mobile + ", Errors=" + Errors + ", ErrorTime=" + ErrorTime + ", LoginIP="
				+ LoginIP + ", LoginTime=" + LoginTime + ", LastLoginIP=" + LastLoginIP + ", LastLoginTime="
				+ LastLoginTime + ", LoginCount=" + LoginCount + ", Question=" + Question + ", Answer=" + Answer
				+ ", Reorder=" + Reorder + ", Status=" + Status + ", AddTime=" + AddTime + ", Remark=" + Remark
				+ ", UserId=" + UserId + ", Token=" + Token + "]";
	}
	
}
