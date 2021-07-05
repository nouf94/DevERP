package deployment;

public class Goal {
	private String p_Description;
	private String p_KPI;
	private String p_Impact;
	private String p_ProjectCode;
	private String p_OldDescription;

	
	
	public Goal() {

	}
	public Goal(String p_Description, String p_Impact , String p_ProjectCode,String p_KPI,String p_OldDescription) {
		this.p_Description = p_Description;
		this.p_KPI = p_KPI;
		this.p_ProjectCode = p_ProjectCode;
		this.p_OldDescription = p_OldDescription;

	}

	public Goal(String p_Description,  String p_Impact, String p_KPI) {
		this.p_Description = p_Description;
		this.p_KPI = p_KPI;
		this.p_Impact = p_Impact;

	}
		public Goal(String p_ProjectCode) {
		this.p_ProjectCode = p_ProjectCode;
	}

	public String getP_OldDescription() {
		return p_OldDescription;
	}
	public void setP_OldDescription(String p_OldDescription) {
		this.p_OldDescription = p_OldDescription;
	}

	public String getP_Description() {
		return p_Description;
	}
	public void setP_Description(String p_Description) {
		this.p_Description = p_Description;
	}

	public String getP_KPI() {
		return p_KPI;
	}
	public void setP_KPI(String p_KPI) {
		this.p_KPI = p_KPI;
	}
	
	public String getP_Impact() {
		return p_Impact;
	}
	public void setP_Impact(String p_Impact) {
		this.p_Impact = p_Impact;
	}

	public String getP_ProjectCode() {
		return p_ProjectCode;
	}
	public void setP_ProjectCode(String p_ProjectCode) {
		this.p_ProjectCode = p_ProjectCode;
	}
}
