package fire.web.utils;



public enum GroupName {
	Administrator("后台管理员","1"),
	Factories("制造商","2"),
	Passport("会员","3"),
	Resale("零售相关","4"),
	Store("零售商","5"),
	Supplier("供应商","6"),
	Wholesale("批发相关","7"),
	Zone("运营商","8");
	private String typeName;
	private String typeCode;
	
	private GroupName(String typeName,String typeCode){
		this.typeName = typeName;
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
}

