package deployment;

public class Requirement{
    private String p_ProjectCode;
    private String p_Title;
    private double p_Cost;


	public Requirement(){
	}

    public Requirement(String p_ProjectCode,String p_Title,double p_Cost){

        this.p_Title=p_Title;
        this.p_ProjectCode=p_ProjectCode;
		this.p_Cost=p_Cost;

	}

      public Requirement(String p_Title,double p_Cost){
        this.p_Title=p_Title;
		this.p_Cost=p_Cost;

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
    
    public double getP_Cost() {
		return p_Cost;
	}
	public void setP_Cost(double p_Cost) {
		this.p_Cost = p_Cost;
	}


}