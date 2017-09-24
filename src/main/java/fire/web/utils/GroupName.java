package fire.web.utils;



public enum GroupName {
	Administrator("��̨����Ա","1"),
	Factories("������","2"),
	Passport("��Ա","3"),
	Resale("�������","4"),
	Store("������","5"),
	Supplier("��Ӧ��","6"),
	Wholesale("�������","7"),
	Zone("��Ӫ��","8");
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

