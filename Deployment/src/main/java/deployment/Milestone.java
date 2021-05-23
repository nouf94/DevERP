package deployment;

public class Milestone {

	private String p_Name;
	private int p_CompletePlannedDate;
	private int p_Weight;		
	private String p_ProjectCode;
    private int p_CompletedActualDate;
	private int p_CompletePercentage;
    

	public Milestone() {
		
	}
	public Milestone(  String p_Name,   int p_CompletePlannedDate,   int p_Weight,   String p_ProjectCode ){
		this.p_Name=p_Name;
		this.p_CompletePlannedDate = p_CompletePlannedDate;
		this.p_Weight = p_Weight;
		this.p_ProjectCode = p_ProjectCode;
    }

public Milestone(  String p_Name,   int p_CompletePlannedDate,int p_CompletedActualDate,   int p_Weight,   int p_CompletePercentage ){
		this.p_Name=p_Name;
		this.p_CompletePlannedDate = p_CompletePlannedDate;
        this.p_CompletedActualDate = p_CompletedActualDate;
		this.p_Weight = p_Weight;
		this.p_CompletePercentage = p_CompletePercentage;
    }
	public String getP_Name() {
		return p_Name;
	}
	public void setP_Name(String p_Name) {
		this.p_Name = p_Name;
	}

	public int getP_CompletePlannedDate() {
		return p_CompletePlannedDate;
	}
    public void setP_CompletePlannedDate(int p_CompletePlannedDate) {
		this.p_CompletePlannedDate = p_CompletePlannedDate;
	}

	public void setP_Weight(int p_Weight) {
		this.p_Weight = p_Weight;
	}
    public int getP_Weight() {
		return p_Weight;
	}

	public String getP_ProjectCode() {
		return p_ProjectCode;
	}
	public void setP_ProjectCode(String p_ProjectCode) {
		this.p_ProjectCode = p_ProjectCode;
	}

	public void setP_CompletedActualDate(int p_CompletedActualDate) {
		this.p_CompletedActualDate = p_CompletedActualDate;
	}
    public int getP_CompletedActualDate() {
		return p_CompletedActualDate;
	}

    public void setP_CompletePercentage(int p_CompletePercentage) {
		this.p_CompletePercentage = p_CompletePercentage;
	}
    public int getP_CompletePercentage() {
		return p_CompletePercentage;
	}
}