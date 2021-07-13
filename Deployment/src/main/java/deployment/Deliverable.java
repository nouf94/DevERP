package deployment;

public class Deliverable{
    // المخرجات
    private String p_ProjectCode;
    private String[] p_Dlivrables;
	private String p_OutcomeTitle;


	public Deliverable(){
	}

    public Deliverable(String p_ProjectCode,String[] p_Dlivrables,String p_OutcomeTitle){
        this.p_Dlivrables=p_Dlivrables;
        this.p_ProjectCode=p_ProjectCode;
		this.p_OutcomeTitle=p_OutcomeTitle;

	}
	public Deliverable(String[] p_Dlivrables,String p_OutcomeTitle){
        this.p_Dlivrables=p_Dlivrables;
        this.p_OutcomeTitle=p_OutcomeTitle;

	}
	
    public Deliverable(String p_ProjectCode){
        this.p_ProjectCode=p_ProjectCode;
	}
    public String[] getP_Dlivrables() {
		return p_Dlivrables;
	}
	public void setP_Dlivrables(String[] p_Dlivrables) {
		this.p_Dlivrables = p_Dlivrables;
	}

    public String getP_ProjectCode() {
		return p_ProjectCode;
	}
	public void setP_ProjectCode(String p_ProjectCode) {
		this.p_ProjectCode = p_ProjectCode;
	}

    public String getP_OutcomeTitle() {
		return p_OutcomeTitle;
	}
	public void setP_OutcomeTitle(String p_OutcomeTitle) {
		this.p_OutcomeTitle = p_OutcomeTitle;
	}

}