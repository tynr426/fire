package fire.web.entity;

import java.util.Date;

public class WeChatAccount {
	private int Id;
	private String Name;
	private String HeaderSrc;
	private String OriginalId;
	private String Account;
	private Integer Catering;
	private String BindAccount;
	private String OpenId;
	private String AppId;
	private String Secret;
	private String Token;
	private String EncodingAESKey;
	private String AccessToken;
	private Date AccessTime;
	private Date ExpireTime;
	private String Country;
	private String Province;
	private String City;
	private String Area;
	private String Intro;
	private Integer Followers;
	private String Qrcode;
	private String Industry;
	private String Config;
	private Integer Status;
	private Date AddTime;
	private String Remark;
	public WeChatAccount(){
		
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
	public String getHeaderSrc() {
		return HeaderSrc;
	}
	public void setHeaderSrc(String headerSrc) {
		HeaderSrc = headerSrc;
	}
	public String getOriginalId() {
		return OriginalId;
	}
	public void setOriginalId(String originalId) {
		OriginalId = originalId;
	}
	public String getAccount() {
		return Account;
	}
	public void setAccount(String account) {
		Account = account;
	}
	public Integer getCatering() {
		return Catering;
	}
	public void setCatering(Integer catering) {
		Catering = catering;
	}
	public String getBindAccount() {
		return BindAccount;
	}
	public void setBindAccount(String bindAccount) {
		BindAccount = bindAccount;
	}
	public String getOpenId() {
		return OpenId;
	}
	public void setOpenId(String openId) {
		OpenId = openId;
	}
	public String getAppId() {
		return AppId;
	}
	public void setAppId(String appId) {
		AppId = appId;
	}
	public String getSecret() {
		return Secret;
	}
	public void setSecret(String secret) {
		Secret = secret;
	}
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	public String getEncodingAESKey() {
		return EncodingAESKey;
	}
	public void setEncodingAESKey(String encodingAESKey) {
		EncodingAESKey = encodingAESKey;
	}
	public String getAccessToken() {
		return AccessToken;
	}
	public void setAccessToken(String accessToken) {
		AccessToken = accessToken;
	}
	public Date getAccessTime() {
		return AccessTime;
	}
	public void setAccessTime(Date accessTime) {
		AccessTime = accessTime;
	}
	public Date getExpireTime() {
		return ExpireTime;
	}
	public void setExpireTime(Date expireTime) {
		ExpireTime = expireTime;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String country) {
		Country = country;
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
	public String getIntro() {
		return Intro;
	}
	public void setIntro(String intro) {
		Intro = intro;
	}
	public Integer getFollowers() {
		return Followers;
	}
	public void setFollowers(Integer followers) {
		Followers = followers;
	}
	public String getQrcode() {
		return Qrcode;
	}
	public void setQrcode(String qrcode) {
		Qrcode = qrcode;
	}
	public String getIndustry() {
		return Industry;
	}
	public void setIndustry(String industry) {
		Industry = industry;
	}
	public String getConfig() {
		return Config;
	}
	public void setConfig(String config) {
		Config = config;
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
		WeChatAccount other = (WeChatAccount) obj;
		if (Id != other.Id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "WeChatacCount [Id=" + Id + ", Name=" + Name + ", HeaderSrc=" + HeaderSrc + ", OriginalId=" + OriginalId
				+ ", Account=" + Account + ", Catering=" + Catering + ", BindAccount=" + BindAccount + ", OpenId="
				+ OpenId + ", AppId=" + AppId + ", Secret=" + Secret + ", Token=" + Token + ", EncodingAESKey="
				+ EncodingAESKey + ", AccessToken=" + AccessToken + ", AccessTime=" + AccessTime + ", ExpireTime="
				+ ExpireTime + ", Country=" + Country + ", Province=" + Province + ", City=" + City + ", Area=" + Area
				+ ", Intro=" + Intro + ", Followers=" + Followers + ", Qrcode=" + Qrcode + ", Industry=" + Industry
				+ ", Config=" + Config + ", Status=" + Status + ", AddTime=" + AddTime + ", Remark=" + Remark + "]";
	}
	
	
}
