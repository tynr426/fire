package fire.web.utils;



public enum UnitPropertiesEnum {
	Market("商场(市场)","1"),
	Hotel("宾馆(饭店)","2"),
	Gymnasium("体育场(馆)","3"),
	Hall("会堂","4"),
	Publicentertainment("公共娱乐场所","5"),
	Otherpublicgatheringplaces("其他公众聚集场所","6"),
	Hospital("医院","7"),
	Anursinghomeandaboardingschool("养老院和寄宿制的学校","8"),
	Kindergarten("幼儿园","9"),
	Stateorgans("国家机关","10"),
	Inflammableandexplosivechemicals("易燃易爆化学物品的生产、充装、存储、供应、销售单位","11"),
	Library("公共图书馆","12"),
	Exhibitionhall("展览馆","13"),
	Museum("博物馆","14"),
	Archives("档案馆以及具有火灾危险性的文物保护单位","15"),
	Passengerstation("客运车站","16"),
	Wharf("码头","17"),
	Airport("民用机场","18"),
	Other("其他","19");
	private String typeName;
	private String typeCode;
	
	private UnitPropertiesEnum(String typeName,String typeCode){
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

