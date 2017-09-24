package fire.web.entity;
public class Devicetypeparameter {
	private int Id;
	private String Description;
	private String DataType;
	private String EditorType;
	private String Candidate;
	private String Unit;
	private Integer Reorder;
	private Integer Required;
	private Integer Status;
	private String Remark;
	private int DeviceTypeId;
	
	public Devicetypeparameter(){
		
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getDataType() {
		return DataType;
	}

	public void setDataType(String dataType) {
		DataType = dataType;
	}

	public String getEditorType() {
		return EditorType;
	}

	public void setEditorType(String editorType) {
		EditorType = editorType;
	}

	public String getCandidate() {
		return Candidate;
	}

	public void setCandidate(String candidate) {
		Candidate = candidate;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public Integer getReorder() {
		return Reorder;
	}

	public void setReorder(Integer reorder) {
		Reorder = reorder;
	}

	public Integer getRequired() {
		return Required;
	}

	public void setRequired(Integer required) {
		Required = required;
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

	public int getDeviceTypeId() {
		return DeviceTypeId;
	}

	public void setDeviceTypeId(int deviceTypeId) {
		DeviceTypeId = deviceTypeId;
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
		Devicetypeparameter other = (Devicetypeparameter) obj;
		if (Id != other.Id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Devicetypeparameter [Id=" + Id + ", Description=" + Description + ", DataType=" + DataType
				+ ", EditorType=" + EditorType + ", Candidate=" + Candidate + ", Unit=" + Unit + ", Reorder=" + Reorder
				+ ", Required=" + Required + ", Status=" + Status + ", Remark=" + Remark + ", DeviceTypeId="
				+ DeviceTypeId + "]";
	}
	
}
