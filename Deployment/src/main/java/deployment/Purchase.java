package deployment;

public class Purchase{
    private String p_Scope;
    private double p_ExpectetCost;
    private String p_ProjectCode;
	private String p_PurchaseMethod;


	public Purchase(){
	}

    public Purchase(String p_ProjectCode,String p_Scope,double p_ExpectetCost,String p_PurchaseMethod){

        this.p_Scope=p_Scope;
        this.p_ExpectetCost=p_ExpectetCost;
        this.p_ProjectCode=p_ProjectCode;
		this.p_PurchaseMethod=p_PurchaseMethod;

	}

      public Purchase(String p_Scope,double p_ExpectetCost,String p_PurchaseMethod){
        this.p_Scope=p_Scope;
		this.p_PurchaseMethod=p_PurchaseMethod;
        this.p_ExpectetCost=p_ExpectetCost;

	}
    public String getP_ProjectCode() {
		return p_ProjectCode;
	}
	public void setP_ProjectCode(String p_ProjectCode) {
		this.p_ProjectCode = p_ProjectCode;
	}

    public String getP_Scope() {
		return p_Scope;
	}
	public void setP_Scope(String p_Scope) {
		this.p_Scope = p_Scope;
	}
    
      public double getP_ExpectetCost() {
		return p_ExpectetCost;
	}
	public void setP_ExpectetCost(double p_ExpectetCost) {
		this.p_ExpectetCost = p_ExpectetCost;
	}

    public String getP_PurchaseMethod() {
		return p_PurchaseMethod;
	}
	public void setP_PurchaseMethod(String p_PurchaseMethod) {
		this.p_PurchaseMethod = p_PurchaseMethod;
	}

}