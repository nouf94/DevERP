package deployment;

public class Outcome{
    // المخرجات
    private String p_ProjectCode;
    private String p_Title;
    private String p_Description;
    private int p_StartDate;
    private int p_EndDate;
    private int duration;

	public Outcome(){
	}
  public Outcome(String p_ProjectCode,String p_Title, String p_Description, int p_StartDate, int p_EndDate, int duration){

        this.p_ProjectCode=p_ProjectCode;
        this.p_Title=p_Title;
        this.p_Description=p_Description;
        this.p_StartDate=p_StartDate;
        this.p_EndDate=p_EndDate;
        this.duration=duration;
	}

    public Outcome(String p_ProjectCode,String p_Title, String p_Description, int p_StartDate, int p_EndDate){

        this.p_ProjectCode=p_ProjectCode;
        this.p_Title=p_Title;
        this.p_Description=p_Description;
        this.p_StartDate=p_StartDate;
        this.p_EndDate=p_EndDate;
	}
    public Outcome(String p_Title, String p_Description, int p_StartDate, int p_EndDate){
        this.p_Title=p_Title;
        this.p_Description=p_Description;
        this.p_StartDate=p_StartDate;
        this.p_EndDate=p_EndDate;

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
	public void setPp_Description(String p_Description) {
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

    public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
}