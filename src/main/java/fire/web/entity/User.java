package fire.web.entity;

import java.util.Date;

public class User {
	public User(){
		
	}
	private Integer Id;
	private String UserName;
	private String Password;
	private String NickName;
    private String Mobile;
    private String Email;
    private Integer Errors;
    private Date ErrorTime;
    private String LoginIP;
    private Date LoginTime;
    private String LastLoginIP;
    private Date LastLoginTime;
    private Integer LoginCount;
    private String Question;
    private String Answer;
    private Date AddTime;
    private Integer Status;
    private String Remark;
    private int UserId;
    private String IP;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
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
	public String getNickName() {
		return NickName;
	}
	public void setNickName(String nickName) {
		NickName = nickName;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
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
	public Date getAddTime() {
		return AddTime;
	}
	public void setAddTime(Date addTime) {
		AddTime = addTime;
	}
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
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
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	@Override
	public String toString() {
		return "User [Id=" + Id + ", UserName=" + UserName + ", Password=" + Password + ", NickName=" + NickName
				+ ", Mobile=" + Mobile + ", Email=" + Email + ", Errors=" + Errors + ", ErrorTime=" + ErrorTime
				+ ", LoginIP=" + LoginIP + ", LoginTime=" + LoginTime + ", LastLoginIP=" + LastLoginIP
				+ ", LastLoginTime=" + LastLoginTime + ", LoginCount=" + LoginCount + ", Question=" + Question
				+ ", Answer=" + Answer + ", AddTime=" + AddTime + ", Status=" + Status + ", Remark=" + Remark
				+ ", UserId=" + UserId + ", IP=" + IP + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
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
		User other = (User) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}
	public User(String userName, String password, String nickName, String mobile, String email, Integer status) {
		super();
		UserName = userName;
		Password = password;
		NickName = nickName;
		Mobile = mobile;
		Email = email;
		Status = status;
	}
    
}
