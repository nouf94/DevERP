package deployment;

public class Assumption{
    private String p_ProjectCode;
    private String p_Title;
	private String p_Description;
    private String p_Impact;
	private int p_ValidUntil;

	public Assumption(){
	}

    public Assumption(String p_ProjectCode,String p_Title,String p_Description, String p_Impact, int p_ValidUntil){

        this.p_Title=p_Title;
        this.p_ProjectCode=p_ProjectCode;
		this.p_Description=p_Description;
		this.p_Impact=p_Impact;
		this.p_ValidUntil=p_ValidUntil;

	}
    public Assumption(String p_Title,String p_Description, String p_Impact, int p_ValidUntil){
        this.p_Title=p_Title;
		this.p_Description=p_Description;
        this.p_Impact=p_Impact;
		this.p_ValidUntil=p_ValidUntil;
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


    public String getP_Description() {
		return p_Description;
	}
	public void setP_Description(String p_Description) {
		this.p_Description = p_Description;
	}

    public String getP_Impact() {
		return p_Impact;
	}
	public void setP_Impact(String p_Impact) {
		this.p_Impact = p_Impact;
	}

    public int getP_ValidUntil() {
		return p_ValidUntil;
	}
	public void setP_ValidUntil(int p_ValidUntil) {
		this.p_ValidUntil = p_ValidUntil;
	}

}