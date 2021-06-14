package deployment;

public class Impact {

	private String[] p_Impacts;
	private String[] p_Details;
	private int p_CreationDate;

	public Impact() {
		
	}
	public Impact(String[] p_Impacts,String[] p_Details, int p_CreationDate) {
		this.p_Impacts = p_Impacts;
		this.p_Details= p_Details;
		this.p_CreationDate=p_CreationDate;
	}
	public Impact(String[] p_Impacts,String[] p_Details) {
		this.p_Impacts = p_Impacts;
		this.p_Details= p_Details;
	}
//set and Get p_Details
	public String[] getP_Impacts() {
		return p_Impacts;
	}
	public void setP_Impacts(String[] p_Impacts) {
		this.p_Impacts = p_Impacts;
	}
//set and Get p_Details
	public String[] getP_Details() {
		return p_Details;
	}
	public void setP_Details(String[] p_Details) {
		this.p_Details = p_Details;
	}
//set and Get p_ChangeRequestCreationDate	
	public int getP_CreationDate() {
		return p_CreationDate;
	}
	public void setP_CreationDate(int p_CreationDate) {
		this.p_CreationDate = p_CreationDate;
	}

}