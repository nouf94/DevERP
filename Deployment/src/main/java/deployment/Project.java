package deployment;

public class Project {

    private String p_Code;
	private String p_Name;
	private String p_Description;
	private int p_StartDate;
	private int p_EndDate;
	private double p_Budget;
	private int p_Duration;
	private String p_Sponsor;

	public Project() {
	}
	   
	public Project(String p_Code, String p_Name, String p_Description, int p_StartDate, int p_EndDate, double p_Budget, int p_Duration, String p_Sponsor ){
		super();
		this.p_Code = p_Code;
		this.p_Name = p_Name;
		this.p_Description = p_Description;
		this.p_StartDate = p_StartDate;
		this.p_EndDate = p_EndDate;
		this.p_Budget = p_Budget;
		this.p_Duration=p_Duration;
		this.p_Sponsor=p_Sponsor;
		

	}

    public String getP_Code() {
		return p_Code;
	}
	public void setP_Code(String p_Code) {
		this.p_Code = p_Code;
	}

    public String getP_Name() {
		return p_Name;
	}
	public void setP_Name(String p_Name) {
		this.p_Name = p_Name;
	}
    public String getP_Description() {
		return p_Description;
	}
	public void setP_Description(String p_Description) {
		this.p_Description = p_Description;
	}
    public int getP_StartDate() {
		return p_StartDate;
	}
	public void setP_StartDate(int p_StartDate) {
		this.p_StartDate = p_StartDate;
	}

    public int getP_EndDate() {
		return p_EndDate;
	}
	public void setP_EndDate(int p_EndDate) {
		this.p_EndDate = p_EndDate;
	}

    public double getP_Budget() {
		return p_Budget;
	}
	public void setP_Budget(double p_Budget) {
		this.p_Budget = p_Budget;
    }

	public int getP_Duration() {
		return p_Duration;
	}
	public void setP_Duration(int p_Duration) {
		this.p_Duration = p_Duration;
    }
	
	public String getP_Sponsor() {
		return p_Sponsor;
	}
	public void setP_Sponsor(String p_Sponsor) {
		this.p_Sponsor = p_Sponsor;
    }

/*
	public String toString() {
		String str="project name:" +this.p_Name+" Project Code: "+ this.p_Code;
		return str;
    }
	*/
/*
	public CreateProject(String p_Code, String p_Name, String p_Description, int p_StartDate,  int p_EndDate, int p_Budget ) {

		super();
		this.p_Code = p_Code;
		this.p_Name = p_Name;
		this.p_Description = p_Description;
		this.p_EndDate = p_EndDate;
		this.p_StartDate = p_StartDate;
		this.p_Budget = p_Budget;
	}*/

}