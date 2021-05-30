package deployment;

public class Document {
	private String p_ProjectCode;
	private String p_Title;
    private String p_Path;
    private int p_State;
	public Document() {
		
	}
	public Document(String p_ProjectCode,String p_Title,String p_Path,int p_State) {
		this.p_ProjectCode = p_ProjectCode;
		this.p_Title = p_Title;
        this.p_Path = p_Path;
		this.p_State = p_State;

	}
	public Document(String p_Title,String p_Path,int p_State) {
		this.p_Title = p_Title;
        this.p_Path = p_Path;
		this.p_State = p_State;

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

    public String get_Path() {
		return p_Path;
	}
	public void setP_Path(String p_Path) {
		this.p_Path = p_Path;
	}

	public int getP_State() {
		return p_State;
	}
	public void setP_State(int p_State) {
		this.p_State = p_State;
	}

}
