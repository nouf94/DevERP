package deployment;

public class Stakeholder{
    private String p_ProjectCode;
    private String p_Name;
	private String p_Influence;
    private String p_Priorty;
	private String p_Interest;

	public Stakeholder(){
	}

    public Stakeholder(String p_ProjectCode,String p_Name,String p_Influence, String p_Priorty, String p_Interest){

        this.p_Name=p_Name;
        this.p_ProjectCode=p_ProjectCode;
		this.p_Influence=p_Influence;
		this.p_Priorty=p_Priorty;
		this.p_Interest=p_Interest;

	}
    public Stakeholder(String p_Name,String p_Influence, String p_Priorty, String p_Interest){
        this.p_Name=p_Name;
		this.p_Influence=p_Influence;
        this.p_Priorty=p_Priorty;
		this.p_Interest=p_Interest;
	}
    
    public String getP_ProjectCode() {
		return p_ProjectCode;
	}
	public void setP_ProjectCode(String p_ProjectCode) {
		this.p_ProjectCode = p_ProjectCode;
	}

    public String getP_Name() {
		return p_Name;
	}
	public void setP_Name(String p_Name) {
		this.p_Name = p_Name;
	}

    public String getP_Influence() {
		return p_Influence;
	}
	public void setP_Influence(String p_Influence) {
		this.p_Influence = p_Influence;
	}

    public String getP_Priorty() {
		return p_Priorty;
	}
	public void setP_Priorty(String p_Priorty) {
		this.p_Priorty = p_Priorty;
	}

    public String getP_Interest() {
		return p_Interest;
	}
	public void setP_Interest(String p_Interest) {
		this.p_Interest = p_Interest;
	}

}