package deployment;

public class Scope{
    private String p_Title;
    private boolean p_IsInScope;
    private String p_ProjectCode;
	private String p_Description;


	public Scope(){
	}

    public Scope(String p_ProjectCode,String p_Title,boolean p_IsInScope,String p_Description){

        this.p_Title=p_Title;
        this.p_IsInScope=p_IsInScope;
        this.p_ProjectCode=p_ProjectCode;
		this.p_Description=p_Description;

	}

      public Scope(String p_Title,boolean p_IsInScope,String p_Description){
        this.p_Title=p_Title;
		this.p_Description=p_Description;
        this.p_IsInScope=p_IsInScope;

	}
    public String getP_ProjectCode() {
		return p_ProjectCode;
	}
	public void setP_ProjectCode(String p_ProjectCode) {
		this.p_ProjectCode = p_ProjectCode;
	}

    public String getP_Title() {
		return p_Title;
	}
	public void setP_Title(String p_Title) {
		this.p_Title = p_Title;
	}
    
    public boolean getP_IsInScope() {
		return p_IsInScope;
	}
	public void setP_IsInScope(boolean p_IsInScope) {
		this.p_IsInScope = p_IsInScope;
	}

    public String getP_Description() {
		return p_Description;
	}
	public void setP_Description(String p_Description) {
		this.p_Description = p_Description;
	}

}