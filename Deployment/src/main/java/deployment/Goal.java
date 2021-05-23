package deployment;

public class Goal {
	private String p_Description;
	private String p_KPI;
	private String p_ProjectCode;

	
	
	public Goal() {
		
	}
	public Goal(String p_Description, String p_KPI, String p_ProjectCode) {
		this.p_Description = p_Description;
		this.p_KPI = p_KPI;
		this.p_ProjectCode = p_ProjectCode;
	}
	public Goal(String p_Description, String p_KPI) {
		this.p_Description = p_Description;
		this.p_KPI = p_KPI;
	}
		public Goal(String p_ProjectCode) {
		this.p_ProjectCode = p_ProjectCode;
	}

	public String toString(){
		String str= "Desc: "+ this.p_Description+ " p_KPI: "+ this.p_KPI+" p_ProjectCode: "+this.p_ProjectCode;
		return str;
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

	public String getP_ProjectCode() {
		return p_ProjectCode;
	}
	public void setP_ProjectCode(String p_ProjectCode) {
		this.p_ProjectCode = p_ProjectCode;
	}
}
