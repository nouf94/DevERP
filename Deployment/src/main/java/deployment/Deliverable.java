package deployment;

public class Deliverable{
    // المخرجات
    private int p_ID;
    private String[] p_Dlivrables;
	private int p_OutcomeID;


	public Deliverable(){
	}

    public Deliverable(int p_ID,String[] p_Dlivrables,int p_OutcomeID){
        this.p_Dlivrables=p_Dlivrables;
        this.p_ID=p_ID;
		this.p_OutcomeID=p_OutcomeID;

	}
	public Deliverable(String[] p_Dlivrables,int p_OutcomeID){
        this.p_Dlivrables=p_Dlivrables;
        this.p_OutcomeID=p_OutcomeID;

	}
	
    public Deliverable(int p_ID){
        this.p_ID=p_ID;
	}
    public String[] getP_Dlivrables() {
		return p_Dlivrables;
	}
	public void setP_Dlivrables(String[] p_Dlivrables) {
		this.p_Dlivrables = p_Dlivrables;
	}

    public int getP_ID() {
		return p_ID;
	}
	public void setP_ID(int p_ID) {
		this.p_ID = p_ID;
	}

    public int getP_OutcomeID() {
		return p_OutcomeID;
	}
	public void setP_OutcomeID(int p_OutcomeID) {
		this.p_OutcomeID = p_OutcomeID;
	}

}