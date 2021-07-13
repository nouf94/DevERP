package deployment;

public class Risk {

    private String p_Title;
    private String p_Severity;
    private String p_Probability;
    private boolean p_IsIssue;
    private boolean p_IsOpen;
    private String p_MitigationPlan;
    private int p_ExpectedDeadline;
    private String p_ProjectCode;



	public Risk() {
		
	}

    public Risk(String p_ProjectCode, String p_Title, String p_Severity, String p_Probability,boolean p_IsIssue, boolean p_IsOpen, String p_MitigationPlan, int p_ExpectedDeadline) {
		this.p_ProjectCode = p_ProjectCode;
		this.p_Title= p_Title;
		this.p_Severity=p_Severity;
        this.p_Probability = p_Probability;
		this.p_IsIssue= p_IsIssue;
		this.p_IsOpen=p_IsOpen;
        this.p_MitigationPlan = p_MitigationPlan;
		this.p_ExpectedDeadline= p_ExpectedDeadline;
	}

	public Risk(String p_Title, String p_Severity, String p_Probability,boolean p_IsIssue, boolean p_IsOpen, String p_MitigationPlan, int p_ExpectedDeadline) {
		this.p_Title= p_Title;
		this.p_Severity=p_Severity;
        this.p_Probability = p_Probability;
		this.p_IsIssue= p_IsIssue;
		this.p_IsOpen=p_IsOpen;
        this.p_MitigationPlan = p_MitigationPlan;
		this.p_ExpectedDeadline= p_ExpectedDeadline;
	}

//set and Get 
	public String getP_ProjectCode() {
		return p_ProjectCode;
	}
	public void setP_ProjectCode(String p_ProjectCode) {
		this.p_ProjectCode = p_ProjectCode;
	}

//set and Get 
	public String getP_Title() {
		return p_Title;
	}
	public void setP_Title(String p_Title) {
		this.p_Title = p_Title;
	}
       
    //set and Get 
	public String getP_Severity() {
		return p_Severity;
	}
	public void setP_Severity(String p_Severity) {
		this.p_Severity = p_Severity;
	}
    
    //set and Get 
	public String getP_Probability() {
		return p_Probability;
	}
	public void setP_Probability(String p_Probability) {
		this.p_Probability = p_Probability;
	}

     //set and Get 
	public boolean getP_IsIssue() {
		return p_IsIssue;
	}
	public void setP_IsIssue(boolean p_IsIssue) {
		this.p_IsIssue = p_IsIssue;
	}

    //set and Get 
	public boolean getP_IsOpen() {
		return p_IsOpen;
	}
	public void setP_IsOpen(boolean p_IsOpen) {
		this.p_IsOpen = p_IsOpen;
	}

//set and Get 
	public String getP_MitigationPlan() {
		return p_MitigationPlan;
	}
	public void setP_MitigationPlan(String p_MitigationPlan) {
		this.p_MitigationPlan = p_MitigationPlan;
	}

//set and Get 	
	public int getP_ExpectedDeadline() {
		return p_ExpectedDeadline;
	}
	public void setP_ExpectedDeadline(int p_ExpectedDeadline) {
		this.p_ExpectedDeadline = p_ExpectedDeadline;
	}

}