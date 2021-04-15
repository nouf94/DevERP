package deployment;

public class ChangeRequest {

	private String p_Description;
	private String p_Justification;		
	private String p_ProjectCode;
	private int p_CreationDate;
	private String p_ImpactCategory;
	private String p_ImpactDetails;

	public ChangeRequest() {
		
	}
	public ChangeRequest(String p_Description, String p_Justification, String p_ProjectCode, int p_CreationDate,String p_ImpactCategory, String p_ImpactDetails) {
		this.p_Description = p_Description;
		this.p_Justification = p_Justification;
		this.p_ProjectCode = p_ProjectCode;
		this.p_CreationDate = p_CreationDate;
		this.p_ImpactCategory = p_ImpactCategory;
		this.p_ImpactDetails = p_ImpactDetails;

	}
	public ChangeRequest( int p_CreationDate,String p_Description, String p_Justification) {
		this.p_Description = p_Description;
		this.p_Justification = p_Justification;
		this.p_CreationDate = p_CreationDate;

	}

	public int getP_CreationDate() {
		return p_CreationDate;
	}

	public void setP_CreationDate(int p_CreationDate) {
		this.p_CreationDate = p_CreationDate;
	}
    public String getP_ProjectCode() {
		return p_ProjectCode;
	}

	public void setP_ProjectCode(String p_ProjectCode) {
		this.p_ProjectCode = p_ProjectCode;
	}
	public String getP_Description() {
		return p_Description;
	}

	public void setP_Description(String p_Description) {
		this.p_Description = p_Description;
	}
	public String getP_Justification() {
		return p_Justification;
	}

	public void setP_Justification(String p_Justification) {
		this.p_Justification = p_Justification;
	}
	public String getP_ImpactCategory() {
		return p_ImpactCategory;
	}

	public void setP_ImpactCategory(String p_ImpactCategory) {
		this.p_ImpactCategory = p_ImpactCategory;
	}
	public String getP_ImpactDetails() {
		return p_ImpactDetails;
	}

	public void setP_ImpactDetails(String p_ImpactDetails) {
		this.p_ImpactDetails = p_ImpactDetails;
	}
}