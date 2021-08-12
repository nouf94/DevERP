package deployment;

public class Constraint{
    private String p_Title;
    private String p_ProjectCode;
	private String p_Note;

	public Constraint(){
	}

    public Constraint(String p_ProjectCode,String p_Title,String p_Note){

        this.p_Title=p_Title;
        this.p_ProjectCode=p_ProjectCode;
		this.p_Note=p_Note;

	}
    public Constraint(String p_Title,String p_Note){
        this.p_Title=p_Title;
		this.p_Note=p_Note;
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


    public String getP_Note() {
		return p_Note;
	}
	public void setP_Note(String p_Note) {
		this.p_Note = p_Note;
	}

}