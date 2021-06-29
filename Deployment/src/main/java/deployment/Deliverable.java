package deployment;

public class Deliverable{
    // المخرجات
    private int p_ID;
    private String p_Title;

	public Deliverable(){
	}

    public Deliverable(int p_ID,String p_Title){
        this.p_Title=p_Title;
        this.p_ID=p_ID;
	}

    public Deliverable(int p_ID){
        this.p_ID=p_ID;
	}

    public String getP_Title() {
		return p_Title;
	}
	public void setP_Title(String p_Title) {
		this.p_Title = p_Title;
	}

    public int getP_ID() {
		return p_ID;
	}
	public void setP_ID(int p_ID) {
		this.p_ID = p_ID;
	}



}