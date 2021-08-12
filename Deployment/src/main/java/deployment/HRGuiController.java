package deployment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import hrsystem.UI;
import hrsystem.hr.main.impl.EmployeeImpl.returnFromLeave;
import io.ciera.runtime.summit.exceptions.XtumlException;
import io.ciera.runtime.summit.types.Date;
import sharedtypes.Payitem;
import sharedtypes.Paytype;
import sharedtypes.Transaction;


@RestController
@RequestMapping(path = "/rest")
public class HRGuiController {
private static HRGuiController singleton;
	
	 private int start = 0;
	 static boolean loged_in = false; 
	
	@Autowired
	public HRGuiController() {
        singleton = this;
	}
	
	public static HRGuiController Singleton() {
		return singleton;
	}
	@GetMapping(path="/initialize", produces = "application/json")
    public void initialize() throws Exception {
    	try {
    		if(start == 0) {
    		UI.Singleton().App().Initialize();
    		Thread.sleep(700);
    		UI.Singleton().Finance().Initialize();
    		UI.Singleton().Projects().Initialize();
    		start++;
    		}
      	}
      	catch ( Exception e ) {
        	  System.out.printf( "Exception, %s, in initialize()\n", e );    			
      	}
	}
	/* Methods in App */
	@GetMapping(path="/approvePayitems", produces="application/json")
	public void ApprovePayitems(int p_EmployeeID) {
		
		try {
			UI.Singleton().App().ApprovePayitems(p_EmployeeID);
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
		
	}
	
	List<ApprovePayment> approvePaymentList = new ArrayList<ApprovePayment>();
	@GetMapping(path="/approvePayment", produces="application/json")
	public void ApprovePayment() {
		
		try {
			employeeList.clear();
			UI.Singleton().App().ReadEmployeeList();
			Thread.sleep(500);
			System.out.println(employeeList.get(0).getP_EmployeeID() + " In Approve");
			int p_Date = 0;  
			double p_Amount = 0;
			for(int i = 0; i < payItemList.size(); i++) {
				if(payItemList.get(i).getP_EmployeeID() == employeeList.get(0).getP_EmployeeID()) {
					if(payItemList.get(i).getP_Name().equals("Salary")) {
						p_Date = Integer.parseInt(payItemList.get(i).getP_Date());
						p_Amount = Double.parseDouble(payItemList.get(i).getP_Amount());
					}
				}
			}
			approvePaymentList.add(new ApprovePayment(employeeList.get(0).getP_EmployeeID(), p_Date, p_Amount));
			UI.Singleton().App().ApprovePayment(employeeList.get(0).getP_EmployeeID(), p_Date, p_Amount);
		} catch (XtumlException | InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@PostMapping(path="/AssignEmployeeBonus", consumes="application/json")
	public void AssignEmployeeBonus(@RequestBody AssignEmployeeBonus bonus) {
		
		try {
			UI.Singleton().App().AssignEmployeeBonus(bonus.getP_EmployeeID(), bonus.getP_BonusName(), bonus.getP_Starting(), bonus.getP_Ending());
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@PostMapping(path="/createBonus", consumes="application/json")
	public void CreateBonus(@RequestBody Bonus bonus) {
		
		try {
			UI.Singleton().App().CreateBonus(bonus.getP_Name(), bonus.getP_Percent());
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@PostMapping(path="/createDepartment", consumes="aaplication/json")
	public void CreateDepartment(@RequestBody CreateDepartment department) {
		
		try {
			UI.Singleton().App().CreateDepartment(department.getP_Name(), department.getP_Mission(), department.getP_ParentDepartment());
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
		
	}
	/* Methods for port AppOps */
	@PostMapping(path="/createEmployee", consumes="application/json")
	public void CreateEmployee(@RequestBody CreateEmployee employee) {
		
		try {
			System.out.println();
			UI.Singleton().App().CreateEmployee(employee.getP_EmployeeID(), employee.getP_NationalID(), employee.getP_FirstName(), employee.getP_MiddleName(), employee.getP_LastName(), employee.getP_DateOfBirth(), employee.getP_Degree(), employee.getP_Gender(), employee.getP_Username());
			Thread.sleep(300);
			UI.Singleton().AppOps().CommenceEmployee(employee.getP_NationalID());
		} catch (XtumlException | InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}

	public void CreateJob(int p_Job_ID, String p_Title, double p_Salary, int p_Step, int p_PentionDeduction, String p_Description, String p_Grade) {
		
		try {
			UI.Singleton().App().CreateJob(p_Job_ID, p_Title, p_Salary, p_Step, p_PentionDeduction, p_Description, p_Grade);
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@PostMapping(path="/CreateLeaveSpecification", consumes="application/json")
	public void CreateLeaveSpecification(@RequestBody CreateLeaveSpecification leave) {
		
		try {
			UI.Singleton().App().CreateLeaveSpecification(leave.getP_Name(), leave.getP_MaximumDays(), leave.getP_MinimumDays());
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@PostMapping(path="/deleteEmployee", consumes="application/json")
	public void DeleteEmployee(@RequestBody Employee employee) {
		
		try {
			UI.Singleton().App().DeleteEmployee(employee.getP_EmployeeID());
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@PostMapping(path="/DeleteLeaveSpecification", consumes="appliaction/json")
	public void DeleteLeaveSpecification(@RequestBody DeleteLeaveSpecification leave) {
		
		try {
			UI.Singleton().App().DeleteLeaveSpecification(leave.getP_Name());
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@PostMapping(path="/editEmployee", consumes="application/json")
	public void EditEmployee(@RequestBody CreateEmployee employee) {
		
		try {
			UI.Singleton().App().EditEmployee(employee.getP_EmployeeID(), employee.getP_NationalID(), employee.getP_FirstName(), employee.getP_MiddleName(), 
					employee.getP_LastName(), employee.getP_OfficeNumber(), employee.getP_DateOfBirth(), employee.getP_Degree(), employee.getP_Gender());
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
		
	}

	int generate = 0;
	@GetMapping(path="/GeneratePayslips", produces="application/json")
	public void GeneratePayslips() {
		
		try {
			if(generate == 0)
			UI.Singleton().App().GeneratePayslips();
			generate = 1;
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
		
	}

	public void GetEmployee(int p_EmployeeID) {
		
		try {
			UI.Singleton().App().GetEmployee(p_EmployeeID);
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
		
	}

	public void GetSalaryStructure(int p_EmployeeID) {
		
		try {
			UI.Singleton().App().GetSalaryStructure(p_EmployeeID);
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
	}

	public void HoldAllPayments() {
		
		try {
			UI.Singleton().App().HoldAllPayments();
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
	}

	public void HoldPayitems(int p_EmployeeID) {
		
		try {
			UI.Singleton().App().HoldPayitems(p_EmployeeID);
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
	}

	public void HoldPayment(int p_EmployeeID,  int p_Date,  double p_Amount ) {
		
		try {
			UI.Singleton().App().HoldPayment(p_EmployeeID, p_Date, p_Amount);
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
	}

	public void MakePayment(int p_EmployeeID, int p_Date, double p_Amount ) {
		
		try {
			UI.Singleton().App().MakePayment(p_EmployeeID, p_Date, p_Amount);
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
	}

	public void ProcessAllPayments() {
		
		try {
			UI.Singleton().App().ProcessAllPayments();
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
	}

	public void ProcessPayments(int p_EmployeeID ) {
		
		try {
			UI.Singleton().App().ProcessPayments(p_EmployeeID);
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
	}

	public void ReadAnyPayItem(int p_EmployeeID ) {
		
		try {
			UI.Singleton().App().ReadAnyPayItem(p_EmployeeID);
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
	}

	public void ReadApprovedPayItems() {
		
		try {
			UI.Singleton().App().ReadApprovedPayItems();
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
	}
	
	@GetMapping(path="/readBonusList", produces="application/json")
	public List<SendBonusList> ReadBonusList() {
		
		try {
			bonusList.clear();
			UI.Singleton().App().ReadBonusList();
			Thread.sleep(700);
			return bonusList;
		} catch (XtumlException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping(path="/readDepartmentList", produces="application/json")
	public List<SendDepartmentList> ReadDepartmentList() {
		
		try {
			departmentList.clear();
			UI.Singleton().App().ReadDepartmentList();
			Thread.sleep(700);
			return departmentList;
		} catch (XtumlException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping(path="/ReadEmployeeBonuses", produces="application/json")
	public List<SendEmployeeBonuses> ReadEmployeeBonuses() {
		
		try {
			employeeBonusList.clear();
			UI.Singleton().App().ReadEmployeeBonuses(employeeBonus);
			Thread.sleep(700);
			System.out.println("--"+employeeBonus+"--");
			System.out.println("----"+employeeBonusList+"-----");
			return employeeBonusList;
		} catch (XtumlException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping(path="/employeeList", produces = "application/json")
	public List<SendEmployee> ReadEmployeeList() {
		
		try {
			employeeList.clear();
			UI.Singleton().App().ReadEmployeeList();
			Thread.sleep(700);
			return employeeList;
			} catch (XtumlException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return null;
	}

	public void ReadEmployeeMessage(int p_EmployeeID) {
		
		try {
			UI.Singleton().App().ReadEmployeeMessage(p_EmployeeID);
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
	}
	
	@GetMapping(path="/readGrades", produces="application/json")
	public List<SendGrades> ReadGrades(String p_Name) {
		
		try {
			scalesList.clear();
			UI.Singleton().App().ReadScales();
			Thread.sleep(700);
			gradeList.clear();
			int size = scalesList.size();
			for(int i = 0; i < size; i++) {
				UI.Singleton().App().ReadGrades(scalesList.get(i).getP_Name());
			}
			Thread.sleep(700);
			return gradeList;
		} catch (XtumlException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping(path="/readJobList", produces="application/json")
	public List<SendJobList> ReadJobList() {
		
		try {
			jobLists.clear();
			UI.Singleton().App().ReadJobList();
			Thread.sleep(700);
			return jobLists;
		} catch (XtumlException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void ReadLastPaymentSlip(int p_EmployeeID ) {
		
		try {
			UI.Singleton().App().ReadLastPaymentSlip(p_EmployeeID);
		} catch (XtumlException e) {
			
			e.printStackTrace();
		} 
	}
	
	@GetMapping(path="/ReadLeaveSpecification", produces="application/json")
	public List<SendLeaveSpecification> ReadLeaveSpecification() {
		
		try {
			ListOfLeaves.clear();
			UI.Singleton().App().ReadLeaveSpecification();
			Thread.sleep(700);
			return ListOfLeaves;
		} catch (XtumlException e) {
			
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	int employeePaymentCounter = 0;
	@PutMapping(path="/ReadPayItems", produces="application/json", consumes="application/json")
	public List<SendPayItem> ReadPayItems(@RequestBody Employee employee) {
		
		try {
			payItemList.clear();
			employeeList.clear();
			UI.Singleton().App().ReadEmployeeList();
			Thread.sleep(300);
			for(int i = 0; i < employeeList.size(); i++) {
				UI.Singleton().App().ReadPayItems(employeeList.get(i).getP_EmployeeID());
				Thread.sleep(300);
			}
			List<SendPayItem> newList = new ArrayList<SendPayItem>();
			for(int i = 0; i < payItemList.size(); i++) {
				String FirstName = "";
				String MiddleName = "";
				String LastName = "";
				for(int j = 0; j < employeeList.size(); j++) {
					if(payItemList.get(i).getP_EmployeeID() == employeeList.get(j).getP_EmployeeID()) {
						FirstName = employeeList.get(j).getP_FirstName();
						MiddleName = employeeList.get(j).getP_MiddleName();
						LastName = employeeList.get(j).getP_LastName(); 
					}
				}
				newList.add( new SendPayItem("SendPayItem", payItemList.get(i).getP_EmployeeID(), FirstName, MiddleName,
						LastName, payItemList.get(i).getP_Name(), payItemList.get(i).getP_Date(), payItemList.get(i).getP_Type(),
						payItemList.get(i).getP_Amount(), payItemList.get(i).getP_Status()));
			}
			return newList;
		} catch (XtumlException | InterruptedException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	public void ReadScales() {
		
		try {
		UI.Singleton().App().ReadScales();
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
	}

	public void SelectAnyBonus() {
		
		try {
		UI.Singleton().App().SelectAnyBonus();
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
	}

	public void SelectAnyEmployee() {
		
		try {
		UI.Singleton().App().SelectAnyEmployee();
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
	}

	public void StopEmployeeBonus(int p_EmployeeID, String p_BonusName) {
		
		try {
		UI.Singleton().App().StopEmployeeBonus(p_EmployeeID, p_BonusName);
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
	}

	public void SuspendPayment(int p_EmployeeID, int p_Date, double p_Amount ) {
		
		try {
		UI.Singleton().App().SuspendPayment(p_EmployeeID, p_Date, p_Amount);
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
	}

	public void UnassignBonus(int p_EmployeeID, String p_Bonus ) {
		
		try {
		UI.Singleton().App().UnassignBonus(p_EmployeeID, p_Bonus);
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
	}
	public void SendFinanceMessage( final int p_Sender,  final String p_Content,  final Transaction p_Type,  final double p_Amount,  final int p_Date,  final String p_Account,  final String p_Description )  {
		
		try {
		UI.Singleton().App().SendFinanceMessage( p_Sender,p_Content, p_Type, p_Amount,p_Date, p_Account, p_Description);
		} catch (XtumlException e) {
			
			e.printStackTrace();
		}
	}
	public void InitializeComplete() {
		
		System.out.println("InitializeComplete");
	
	}
	

	public Reply Reply(String p_msg, boolean p_state) {
		
		
			Reply msg = new Reply( "Reply", p_msg, String.valueOf( p_state ) );
	        return msg;
		
	}

	public void ReplyNewEmployee(String p_Username, String p_Password) {
		
		try {
			ReplyNewEmployee msg = new ReplyNewEmployee( "ReplyNewEmployee", p_Username, p_Password );
	        
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	List<SendBonusList> bonusList = new ArrayList<SendBonusList>();
	public void SendBonusList(String p_Name, double p_Percent) {
		
		try {
			bonusList.add(new SendBonusList( "SendBonusList", p_Name, String.valueOf( p_Percent ) ));
	        
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	List<SendDepartmentList> departmentList = new ArrayList<SendDepartmentList>(); 
	public void SendDepartmentList(String p_Name, String p_Mission, String p_Description, String p_Manager,
			String p_ManagingDepartment) {
		
		try {
			departmentList.add(new SendDepartmentList( "SendDepartmentList", p_Name, p_Mission, p_Description, p_Manager, p_ManagingDepartment));
	        
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	List<SendLeaveSpecification> ListOfLeaves = new ArrayList<SendLeaveSpecification>();
	public void SendLeaveSpecification(String p_Name, int p_MaximumDays, int p_MinimumDays, int p_Size) {
		
		try {
			ListOfLeaves.add(new SendLeaveSpecification( "SendLeaveSpecification", p_Name, String.valueOf( p_MaximumDays ),
														  String.valueOf( p_MinimumDays ), String.valueOf( p_Size )));
	        
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	List<SendEmployee> employeeList = new ArrayList<SendEmployee>();
	public void SendEmployee(int p_EmployeeID, int p_NationalID, String p_FirstName, String p_MiddleName,
			String p_LastName, String p_Email, String p_OfficePhone, int p_DateOfBirth, String p_Degree,
			String p_Gender, int p_StartDate, int p_LeaveBalance, int p_SickLeaveBalance, double p_Salary) {
		
		
			SendEmployee msg = new SendEmployee( "SendEmployee",  p_EmployeeID ,  p_NationalID , p_FirstName, p_MiddleName, 
																 p_LastName, p_Email, p_OfficePhone,  p_DateOfBirth , p_Degree,
																 p_Gender, p_StartDate ,  p_LeaveBalance , p_SickLeaveBalance,  p_Salary );
			employeeList.add(msg);
		
	}
	
	List<SendEmployeeBonuses> employeeBonusList = new ArrayList<SendEmployeeBonuses>();
	public void SendEmployeeBonuses(String p_BonusName, int p_Starting, int p_Ending, double p_Percent,
			double p_Amount) {
		
		try {
			employeeBonusList.add(new SendEmployeeBonuses( "SendEmployeeBonuses", p_BonusName,  p_Starting ,
					 											p_Ending ,  p_Percent ,  p_Amount ));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	public void SendEmployeeMessages(int p_LeaveRequesterID, int p_Starting, int p_Ending, String p_Content) {
		
		try {
			SendEmployeeMessages msg = new SendEmployeeMessages( "SendEmployeeMessages",String.valueOf( p_LeaveRequesterID ),
																String.valueOf( p_Starting ), String.valueOf( p_Ending ), p_Content);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	List<SendGrades> gradeList = new ArrayList<SendGrades>();
	public void SendGrades(String p_Name, double p_BaseSalary, double p_Allowance, int p_NumberOfSteps, String p_Above,
			String p_Below) {
		
		try {
			gradeList.add(new SendGrades( "SendGrades", p_Name, String.valueOf( p_BaseSalary ), String.valueOf( p_Allowance ),
															String.valueOf( p_NumberOfSteps ), p_Above, p_Below));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	List<SendJobList> jobLists = new ArrayList<SendJobList>();
	public void SendJobList(int p_JobID, String p_Title, double p_Salary, String p_EmployeeName, int p_EmployeeID) {
		
		try {
			jobLists.add( new SendJobList( "SendJobList", String.valueOf( p_JobID ), p_Title, String.valueOf( p_Salary ),
															  p_EmployeeName, String.valueOf( p_EmployeeID )));
				
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	List<SendPayItem> payItemList = new ArrayList<SendPayItem>();
	public void SendPayItem(int p_EmployeeID, String p_Name, int p_Date, Paytype p_Type, double p_Amount, Payitem p_Status ) {
		
		try {
			payItemList.add( new SendPayItem( "SendPayItem", p_EmployeeID, p_Name,  String.valueOf( p_Date ),  String.valueOf( p_Type ),  String.valueOf( p_Amount ),  String.valueOf( p_Status )));
			System.out.println(employeePaymentCounter + ", "+ p_EmployeeID +", " + p_Name);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void SendSalaryStructure(double p_EmployeeSalary, String p_BonusName, double p_BonusAmount) {
		
		try {
			SendSalaryStructure msg = new SendSalaryStructure( "SendSalaryStructure", String.valueOf( p_EmployeeSalary ), p_BonusName, String.valueOf( p_BonusAmount ));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	List<SendScales> scalesList = new ArrayList<SendScales>();
	public void SendScales(String p_Name, String p_Description) {
		
		try {
			scalesList.add(new SendScales( "SendScales", p_Name, p_Description));
				
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void AddToGroup(int p_EmployeeID, String p_Group) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Authenticate().AddToGroup(p_EmployeeID, p_Group);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ChangePassword(String p_Username, String p_OldPassword, String p_NewPassword) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Authenticate().ChangePassword(p_Username, p_OldPassword, p_NewPassword);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(path="/CheckUsernamePassword", consumes="application/json", produces = "application/json")
	public ReplyUsernamePassword CheckUsernamePassword(@RequestBody CheckUsernamePasswordClass check) {
		// TODO Auto-generated method stub
		try {
			System.out.printf("%s, %s", check.getP_Username(), check.getP_Password());
			UI.Singleton().Authenticate().CheckUsernamePassword(check.getP_Username(), check.getP_Password());
			Thread.sleep(700);
			if(userChecked.getP_state()) {
				loged_in = true;
			}
			return userChecked;
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@PostMapping(path="/createNewAccount", consumes="application/json")
	public void CreateNewAccount(@RequestBody CreateNewAccount account) {
		// TODO Auto-generated method stub
		try {
		UI.Singleton().Authenticate().CreateNewAccount(account.getP_First_Name(), account.getP_Last_Name(), account.getP_EmployeeID(), account.getP_Username());
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ReadEmployeePermissions(int p_EmployeeID) {
		
		try {
			UI.Singleton().Authenticate().ReadEmployeePermissions(p_EmployeeID);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static ReplyUsernamePassword userChecked;
	public ReplyUsernamePassword ReplyUsernamePassword(int p_EmployeeID, String p_Username, String p_msg, boolean p_state) {
		
		try {
			userChecked = new ReplyUsernamePassword( "ReplyUsernamePassword", p_EmployeeID, p_Username, p_msg, p_state);
			return userChecked;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	List<SendEmployeePermissions> roles = new ArrayList<SendEmployeePermissions>();
	public void SendEmployeePermissions(String p_GroupName, String p_Description) {
		
		try {
			roles.add(new SendEmployeePermissions( "SendEmployeePermissions", p_GroupName, p_Description));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(path="/ApproveEmployeeLeave", consumes="application/json")
	public void ApproveEmployeeLeave(@RequestBody ApproveEmployeeLeave employee) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().AppOps().ApproveEmployeeLeave(employee.getP_EmployeeID());
			int size = listOfRequestedLeaves.size();
			for(int i = 0; i < size; i++) {
				if(listOfRequestedLeaves.get(i).get(0).equals(employee.getP_EmployeeID()+"")) {
					listOfRequestedLeaves.remove(i);
				}
			}
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(path="/AssignBonusToEmployee", consumes="application/json")
	public void AssignBonusToEmployee(@RequestBody AssignBonusToEmployee employee) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().AppOps().AssignBonusToEmployee(employee.getP_National_ID(), employee.getP_Name(), employee.getP_Starting(), employee.getP_Ending(), "NEW");
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(path="/AssignJobToEmployee", consumes="appliaction/json")
	public void AssignJobToEmployee(@RequestBody AssignJobToEmployee employee) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().AppOps().AssignJobToEmployee(employee.getP_Job_ID(), employee.getP_National_ID(), "NEW", true);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(path="/commenceEmployee", consumes="application/json")
	public void CommenceEmployee(@RequestBody CommenceEmployee commence) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().AppOps().CommenceEmployee(commence.getP_National_ID());
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void GenerateEmployeePayslip(int p_National_ID) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().AppOps().GenerateEmployeePayslip(p_National_ID);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(path="/RejectEmployeeLeave", consumes="application/json")
	public void RejectEmployeeLeave(@RequestBody RejectEmployeeLeave employee) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().AppOps().RejectEmployeeLeave(employee.getP_EmployeeID());
			int size = listOfRequestedLeaves.size();
			for(int i = 0; i < size; i++) {
				if(listOfRequestedLeaves.get(i).get(0).equals(employee.getP_EmployeeID()+"")) {
					listOfRequestedLeaves.remove(i);
				}
			}
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	List<List<String>> listOfRequestedLeaves = new ArrayList<List<String>>();
	@PostMapping(path="/requestEmployeeLeave", consumes="application/json")
	public void RequestEmployeeLeave(@RequestBody RequestEmployeeLeave employeeLeave ) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().AppOps().RequestEmployeeLeave(employeeLeave.getP_Starting(), employeeLeave.getP_Ending(), employeeLeave.getP_EmployeeID(), employeeLeave.getP_LeaveName());
			List<String> leaves = new ArrayList<String>();
			leaves.add(employeeLeave.getP_EmployeeID()+ "");
			leaves.add(employeeLeave.getP_LeaveName());
			listOfRequestedLeaves.add(leaves);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(path="/returnFromLeave", consumes="application/json", produces="application/json")
	public void ReturnFromLeave(@RequestBody ReturnFromLeave leave) {
		// TODO Auto-generated method stub
		try {
			
			UI.Singleton().AppOps().ReturnFromLeave(leave.getP_National_ID());
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Return_Employee_Payment(double p_Amount, String p_Name, int p_Date) {
		
		try {
			Return_Employee_Payment msg = new Return_Employee_Payment( "Return_Employee_Payment", String.valueOf( p_Amount ), p_Name, String.valueOf( p_Date ));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/* Methods for Port Finance */
	public void AcceptAllCeiling() {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().AcceptAllCeiling();
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void AcceptAllRequested() {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().AcceptAllRequested();
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(path="/acceptCeiling", consumes="application/json")
	public void AcceptCeiling(@RequestBody Chapter chapter) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().AcceptCeiling(chapter.getP_Code());
			Thread.sleep(500);
			UI.Singleton().Finance().OperationalizeAccount(chapter.getP_Code());
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(path="/acceptRequested", consumes="application/json")
	public void AcceptRequested(@RequestBody Chapter chapter) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().AcceptRequested(chapter.getP_Code());
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	int createTransfer = 0;
	List<String> transferColumn = new ArrayList<String>();
	@PostMapping(path="/accountTransfers", consumes="application/json")
	public void AccountTransfers(@RequestBody AccountTransfers account) {
		// TODO Auto-generated method stub
		try {
			if(createTransfer == 0) {
				UI.Singleton().Finance().CreateTransfersJournal();
				createTransfer = 1;
			}
			String column = "من: " + account.getP_Code() + " إلي: " + account.getP_CodeTo();
			boolean match = false;
			for(int i = 0; i < transferColumn.size(); i++) {
				if(transferColumn.get(i).equals(column)) {
					match = true;
				}
			}
			if(match) {
				UI.Singleton().Finance().AccountTransfers(column, account.getP_Amount(), account.getP_Date(), account.getP_Description());
			}else {
				UI.Singleton().Finance().AddTransferColumn("من: " + account.getP_Code() + " إلي: " + account.getP_CodeTo(), account.getP_Code(), account.getP_CodeTo());
				UI.Singleton().Finance().AccountTransfers(column, account.getP_Amount(), account.getP_Date(), account.getP_Description());
			}
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(path="/addFunds", consumes="application/json")
	public void AddFunds(@RequestBody Chapter chapter) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().AddFunds(chapter.getP_Code(), chapter.getP_Amount());
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void AddJournalColumn(String p_JournalName, String p_ColumnTitle, String p_ColumnDescription, String p_DebitAccountCode, String p_CreditAccountCode ) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().AddJournalColumn(p_JournalName, p_ColumnTitle, p_ColumnDescription, p_DebitAccountCode, p_CreditAccountCode);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void AddTransferColumn(String p_Title, String p_TransferFrom, String p_TransferTo ) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().AddTransferColumn(p_Title, p_TransferFrom, p_TransferTo);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(path="/createAccount", consumes="application/json")
	public void CreateAccount(@RequestBody CreateAccount account) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().CreateAccount(account.getP_Code(),  account.getP_Name(),  account.getP_Description(),  account.getP_ParentAccount());
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	int BudgetCreated = 0;
	@PutMapping(path="/createBudget", consumes="application/json", produces="application/json")
	public List<SendAccountFunds> CreateBudget(@RequestBody CreateBudget budget) {
		// TODO Auto-generated method stub
		try {
			sendAccountFundsList.clear();
			//UI.Singleton().Finance().AcceptAllCeiling();
			//Thread.sleep(500);
			//UI.Singleton().Finance().Operationalize(budget.getP_FromBudget());
			//Thread.sleep(500);
			UI.Singleton().Finance().CreateBudget(budget.getP_Year(), budget.getP_FromBudget());
			Thread.sleep(500);
			UI.Singleton().Finance().Operationalize(budget.getP_Year());
			UI.Singleton().Finance().ReadBudgetAccounts();
			BudgetCreated = 1;
			Thread.sleep(500);
			return sendAccountFundsList;
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("Error in CreateBudget");
		return null;
	}

	public void CreateCashJournal() {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().CreateCashJournal();
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(path="/createExpensesJournal", consumes="application/json")
	public void CreateExpensesJournal(@RequestBody CreateExpensesJournal journal ) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().CreateExpensesJournal(journal.getP_Name(), journal.getP_Description());
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(path="/createJournal", consumes="application/json")
	public void CreateJournal(@RequestBody CreateJournal journal) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().CreateJournal(journal.getP_Name(), journal.getP_Description(), journal.getP_ColumnTitle(), journal.getP_ColumnDescription(), journal.getP_DebitAccountCode(), journal.getP_CreditAccountCode());
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*public void CreateSection(String p_Code, String p_Name) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().CreateSection(p_Code, p_Name);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	@PostMapping(path="/createProgram", consumes="application/json")
	public void CreateProgram(@RequestBody CreateProgram program) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().CreateProgram(program.getP_Code(),  program.getP_Name(),  program.getP_Description(),  program.getP_Duration(),  program.getP_StartDate(),  program.getP_DedicatedFunds(),  program.getP_Account());
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping(path="/createSalaryJournal", produces="application/json")
	public void CreateSalaryJournal() {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().CreateSalaryJournal();
			System.out.println("In CreateSalaryJournal");
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void CreateSubAccount(String p_Code, String p_Name, String p_Description ) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().CreateSubAccount(p_Code, p_Name, p_Description);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void CreateTransfersJournal() {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().CreateTransfersJournal();
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void DisregardBudget(String p_Year) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().DisregardBudget(p_Year);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping(path="/modifyCeiling", produces="application/json")
	public void ModifyCeiling() {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().ModifyCeiling("211111", 100, 120);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ModifyRequested(String p_Code, int p_Fund, int p_Revenue ) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().ModifyRequested(p_Code, p_Fund, p_Revenue);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(path="/operationalize", consumes="application/json")
	public void Operationalize(@RequestBody Budget budget) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().AcceptAllCeiling();
			Thread.sleep(500);
			accountCount = 2;
			UI.Singleton().Finance().Operationalize(budget.getP_Year());
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void OperationalizeAccount(String p_Code ) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().OperationalizeAccount(p_Code);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void OperationalizeBudget(String p_Code ) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().OperationalizeBudget(p_Code);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ReadAccepted() {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().ReadAccepted();
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*public void ReadAccountFunds(String p_Code ) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().ReadAccountFunds(p_Code);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	@GetMapping(path="/ReadAccounts", produces="application/json")
	public List<SendAccount> ReadAccounts() {
		// TODO Auto-generated method stub
		try {
			sendAccountList.clear();
			UI.Singleton().Finance().ReadAccounts();
			Thread.sleep(300);
			return sendAccountList;
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void ReadAllBudgetAccounts() {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().ReadAllBudgetAccounts();
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void ReadBudget(String p_Year) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().ReadBudget(p_Year);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@PutMapping(path="/readBudgetAccount", produces="application/json", consumes="application/json")
	public List<SendAccountFunds> ReadBudgetAccount(@RequestBody Chapter chapter) {
		// TODO Auto-generated method stub
		try {
			sendAccountFundsList.clear();
			UI.Singleton().Finance().ReadBudgetAccount(chapter.getP_Code());
			Thread.sleep(500);
			return sendAccountFundsList;
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Error in ReadBudgetAccount");
		return null;
	}
	
	@GetMapping(path="/getReadBudgetAccount", produces="application/json")
	public List<SendAccountFunds> ReadBudgetAccount() {
		// TODO Auto-generated method stub
		try {
			sendAccountFundsList.clear();
			UI.Singleton().Finance().ReadBudgetAccount(Taccount);
			Thread.sleep(500);
			return sendAccountFundsList;
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Error in ReadBudgetAccount");
		return null;
	}
	
	@GetMapping(path="/readBudgetAccounts", produces="application/json")
	public List<SendAccountFunds> ReadBudgetAccounts() {
		// TODO Auto-generated method stub
		try {
			sendAccountFundsList.clear();
			UI.Singleton().Finance().ReadBudgetAccounts();
			Thread.sleep(500);
			return sendAccountFundsList;
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Error in ReadBudgetAccounts");
		return null;

	}

	public void ReadBudgetPrograms() {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().ReadBudgetPrograms();
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping(path="/readBudgets", produces="application/json")
	public List<SendBudgets> ReadBudgets() {
		// TODO Auto-generated method stub
		try {
			budgetList.clear();
			
			if(BudgetCreated == 1) {
			UI.Singleton().Finance().ReadBudget("2025");
			}else {
				UI.Singleton().Finance().ReadBudget("2021");
			}
			Thread.sleep(700);
			for(int i = 0; i < budgetList.size(); i++)
			System.out.println(budgetList.get(i).getP_Year() +", "+ budgetList.get(i).getP_Ceiling() +", "+ budgetList.get(i).getP_Requested() +", "+
							   budgetList.get(i).getP_CeilingRevenue() +", "+ budgetList.get(i).getP_RequestedRevenue());
			return budgetList;
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void ReadChartAccount(String p_Code ) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().ReadChartAccount(p_Code);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	int accountCount = 0;
	@GetMapping(path="/readChartAccounts", produces="application/json")
	public List<SendChartAccounts> ReadChartAccounts() {
		// TODO Auto-generated method stub
		try {
			chartOfAccount.clear();
				
			if(accountCount == 0) {
				UI.Singleton().Finance().AcceptCeiling("211111");
				UI.Singleton().Finance().AcceptCeiling("211114");
				Thread.sleep(300);
				UI.Singleton().Finance().OperationalizeAccount("211111");
				UI.Singleton().Finance().OperationalizeAccount("211114");
			Thread.sleep(400);
			accountCount++;
			}
			//for(int i = 0; i < sectionList.size(); i++) {
				UI.Singleton().Finance().ReadChartAccounts();
			//}
				Thread.sleep(300);
				return chartOfAccount;
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Error in ChartAccount");
		return null;
	}
	
	@PutMapping(path="/readEarmarkedPrograms", consumes="application/json", produces="application/json")
	public List<SendPrograms> ReadEarmarkedPrograms(@RequestBody Chapter chapter) {
		// TODO Auto-generated method stub
		try {
			programList.clear();
			UI.Singleton().Finance().ReadEarmarkedPrograms(chapter.getP_Code());
			Thread.sleep(500);
			if(!programList.isEmpty()) {
			System.out.println("Programs: " + programList.get(0).getP_Code() + " Account: " + programList.get(0).getP_AccountCode());
			}
			return programList;
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Error in ReadEarmarkedPrograms");
		return null;
	}
	
	@GetMapping(path="/readNewPrograms", produces="application/json")
	public List<SendPrograms> ReadNewPrograms() {
		// TODO Auto-generated method stub
		try {
			programList.clear();
			UI.Singleton().Finance().ReadNewPrograms();
			Thread.sleep(500);
			return programList;
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Error in ReadNewPrograms");
		return null;
	}
	
	@PutMapping(path="/readProgram", consumes="application/json", produces="application/json")
	public List<SendPrograms> ReadProgram(@RequestBody Chapter chapter ) {
		// TODO Auto-generated method stub
		try {
			programList.clear();
			UI.Singleton().Finance().ReadProgram(chapter.getP_Code());
			Thread.sleep(300);
			return programList;
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Error in ReadProgram");
		return null;
	}
	
	@GetMapping(path="/readPrograms", produces="application/json")
	public List<SendPrograms> ReadPrograms() {
		// TODO Auto-generated method stub
		try {
			programList.clear();
			/*UI.Singleton().Finance().AcceptAllCeiling();//THERE'S A PROBLEM HERE FIX IT!
			UI.Singleton().Finance().OperationalizeAccount("٣١");
			UI.Singleton().Finance().OperationalizeAccount("311129");
			UI.Singleton().Finance().OperationalizeAccount("311126");
			UI.Singleton().Finance().OperationalizeAccount("311132");
			Thread.sleep(300);*/
			//UI.Singleton().Finance().ReadProgram("400750200");
			UI.Singleton().Finance().ReadPrograms();
			Thread.sleep(300);
			if(!programList.isEmpty()) {
			for(int k = 0; k < programList.size(); k++) {
				System.out.println("Program: " + programList.get(k).getP_Code() + " Account: " + programList.get(k).getP_AccountCode());
			}
			}
			return programList;
		} catch ( InterruptedException | XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void ReadRejected() {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().ReadRejected();
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping(path="/ReadRequested", produces="application/json")
	public List<SendSections> ReadRequested(String p_BudgetYear ) {
		// TODO Auto-generated method stub
		try {
			sectionList.clear();
			UI.Singleton().Finance().ReadRequested("2021");
			Thread.sleep(300);
			return sectionList;
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping(path="/readSalaryJournal", produces="application/json")
	public List<SendJournal> ReadSalaryJournal() {
		// TODO Auto-generated method stub
		try {
			journalList.clear();
			UI.Singleton().Finance().ReadSalaryJournal();
			Thread.sleep(500);
			System.out.println("journalList: " +journalList.get(0).getP_Name());
			return journalList;
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Error in ReadSalaryJournal");
		return null;
	}
	
	String Taccount;
	@GetMapping(path="/readTAccount", produces="application/json")
	public List<SendTAccount> ReadTAccount() {
		// TODO Auto-generated method stub
		try {
			TAccountList.clear();
			UI.Singleton().Finance().ReadTAccount(Taccount);
			Thread.sleep(300);
			System.out.println("----Here-----@Taccount");
			return TAccountList;
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Error in TAccount");
		return null;
	}
	
	@GetMapping(path="/ReadTransferColumns", produces="application/json")
	public List<SendTransferColumns> ReadTransferColumns() {
		// TODO Auto-generated method stub
		try {/*
			UI.Singleton().Finance().CreateTransfersJournal();
			Thread.sleep(500);
			*/

			UI.Singleton().Finance().CreateTransfersJournal();
			UI.Singleton().Finance().AddTransferColumn("from 211114 to 211111", "211114", "211111");
			/*UI.Singleton().Finance().AccountTransfers("from 211114 to 211111", 1000000, 129101982, "نقل ١٠٠٠ ريال");
			Thread.sleep(500);*/
			UI.Singleton().Finance().ReadTransferColumns();
			//UI.Singleton().Finance().ReadTransferJournal("from 211114 to 211111");
			Thread.sleep(500);
			//return listTransferColumns;
			return null;
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("Error in ReadTransferColumns");
		return null;
	}

	public void ReadTransferJournal(String p_Column ) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().ReadTransferJournal(p_Column);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GetMapping(path="/recordSalaryEntry", produces="application/json")
	public void RecordSalaryEntry() {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().App().MakePayment(approvePaymentList.get(0).getP_EmployeeID(), approvePaymentList.get(0).getP_Date(), approvePaymentList.get(0).getP_Amount());
			Thread.sleep(500);
			UI.Singleton().Finance().RecordSalaryEntry( approvePaymentList.get(0).getP_Date(), "record salary entry", approvePaymentList.get(0).getP_Amount());
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(path="/reduceFunds", consumes="application/json")
	public void ReduceFunds(@RequestBody Chapter chapter ) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().ReduceFunds(chapter.getP_Code(), chapter.getP_Amount());
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PutMapping(path="/relateProgram", consumes="application/json", produces="application/json")
	public List<SendPrograms> RelateProgram(@RequestBody RelateProgram program) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().UnrelateProgram(program.getP_Program());
			Thread.sleep(500);
			UI.Singleton().Finance().RelateProgram(program.getP_Program(), program.getP_Account());
			System.out.println("Programs:" + program.getP_Program() + " Account: " + program.getP_Account()+"--------");
			Thread.sleep(500);
			programList.clear();
			UI.Singleton().Finance().ReadEarmarkedPrograms(program.getP_Account());
			Thread.sleep(500);
			if(!programList.isEmpty()) {
			System.out.println("Programs:" + programList.get(0).getP_Code() + " Account: " + programList.get(0).getP_AccountCode());
			}
			return programList;
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("Error in RelateProgram");
		return null;
	}
	//Rewrite this method!!
	@PostMapping(path="/settleTransfer", consumes="application/json")
	public void SettleTransfer(String p_Column, int p_Amount, int p_Date ) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().SettleTransfer(p_Column, p_Amount, p_Date);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SingleColumnEntry(String p_JournalName, double p_Amount, int p_Date, String p_Description ) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().SingleColumnEntry(p_JournalName, p_Amount, p_Date, p_Description);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PutMapping(path="/unrelateProgram", consumes="application/json", produces="application/json")
	public List<SendPrograms> UnrelateProgram(@RequestBody RelateProgram program) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().UnrelateProgram(program.getP_Program());
			Thread.sleep(500);
			programList.clear();
			UI.Singleton().Finance().ReadProgram(program.getP_Account());
			Thread.sleep(500);
			return programList;
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Error in UnrelateProgram");
		return null;
	}

	/*public void ReadCeiling(String p_BudgetYear ) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().ReadCeiling(p_BudgetYear);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	/*@GetMapping(path="/ReadExpenditureAccounts", produces="application/json")
	public List<SendChapters> ReadExpenditureAccounts() {
		// TODO Auto-generated method stub
		try {
			chapterList.clear();
			Thread.sleep(700);
			UI.Singleton().Finance().ReadExpenditureAccounts(budgetYear);
			System.out.printf("///////////////---%s", budgetYear);
			Thread.sleep(700);
			for(int i = 0; i < chapterList.size(); i++)
			System.out.println(chapterList.get(i).getP_Code() +", "+ chapterList.get(i).getP_Name() +", "+ chapterList.get(i).getP_Ceiling() +", "+ chapterList.get(i).getP_Requested());
			return chapterList;
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	

	public void ReviewAccount(String p_Code ) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().ReviewAccount(p_Code);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	
	
	/*@GetMapping(path="/readChapters", produces="application/json")
	public List<SendChapters> ReadChapters() {
		// TODO Auto-generated method stub
		try {
			chapterList.clear();
			Thread.sleep(700);
			System.out.printf("///////////////---%s", budgetYear);
			UI.Singleton().Finance().ReadChapters(budgetYear);
			Thread.sleep(700);
			return chapterList;
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/

	/*public void ReadSectionsCeiling(String p_BudgetYear, String p_ChapterCode) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Finance().ReadSectionsCeiling(p_BudgetYear, p_ChapterCode);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	/*
	@GetMapping(path="/readSectionsRequested", produces="application/json")
	public List<SendSections> ReadSectionsRequested() {
		// TODO Auto-generated method stub
		try {
			sectionList.clear();
			Thread.sleep(700);
			UI.Singleton().Finance().ReadSectionsRequested(budgetYear, chapterCode);
			Thread.sleep(700);
			return sectionList;
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/

	public void ReplyFinance(String p_msg, boolean p_state) {
		
		try {
			ReplyFinance msg = new ReplyFinance( "ReplyFinance", p_msg, String.valueOf( p_state ));
			System.out.println(p_msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	List<SendAccount> sendAccountList = new ArrayList<SendAccount>();
	public void SendAccount(String p_Code, String p_Name, int p_Fund, int p_Revenue ) {
		// TODO Auto-generated method stub
		try {
			sendAccountList.add( new SendAccount(p_Code, p_Name, p_Fund, p_Revenue));
			System.out.println("Here 1: " + p_Code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	List<SendAccountFunds> sendAccountFundsList = new ArrayList<SendAccountFunds>();
	public void SendAccountFunds(String p_Code, String p_Name, boolean p_New, int p_Ceiling, int p_Requested,  int p_CeilingRevenue, int p_RequestedRevenue ) {
		// TODO Auto-generated method stub
		try {
			sendAccountFundsList.add(new SendAccountFunds(p_Code, p_Name, p_New, p_Ceiling, p_Requested, p_CeilingRevenue, p_RequestedRevenue));
			System.out.println("Here 2: " + p_Code +" Ceiling 2: " + p_Ceiling);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	List<SendBudgets> budgetList = new ArrayList<SendBudgets>();
	public void SendBudgets(String p_Year, int p_Ceiling, int p_Requested, int p_CeilingRevenue, int p_RequestedRevenue) {
		
		try {
			budgetList.add(new SendBudgets( "SendBudgets", p_Year, String.valueOf( p_Ceiling ), String.valueOf( p_Requested ), String.valueOf( p_CeilingRevenue ), String.valueOf( p_RequestedRevenue )));
			System.out.println("Here 3: " + p_Year + "Ceiling: " + p_Ceiling);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	List<SendChartAccounts> chartOfAccount = new ArrayList<SendChartAccounts>();
	public void SendChartAccounts(String p_Code, String p_Name, int p_OpeningBalance, int p_AddedFunds, int p_Reduction, double p_TransferDebit,  double p_TransferCredit, double p_NetBalance, double p_Spending, double p_GrandTotal, int p_DocumantryCredits, int p_Custodies, int p_Paid, double p_RemainingBalance, double p_Percentage ) {
		// TODO Auto-generated method stub
		try {
			chartOfAccount.add( new SendChartAccounts( p_Code, p_Name, p_OpeningBalance, p_AddedFunds, p_Reduction, p_TransferDebit, p_TransferCredit, p_NetBalance, p_Spending, p_GrandTotal, p_DocumantryCredits, p_Custodies, p_Paid, p_RemainingBalance, p_Percentage));
			System.out.println("Here 4: " + p_Code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	List<SendJournal> journalList = new ArrayList<SendJournal>();
	public void SendJournal(String p_Name, String p_Description, String p_ColumnTitle, String p_ColumnDescription, int p_EntryDate, String p_EntryDescription, double p_EntryAmount ) {
		// TODO Auto-generated method stub
		try {
			journalList.add( new  SendJournal(p_Name, p_Description, p_ColumnTitle, p_ColumnDescription, p_EntryDate, p_EntryDescription, p_EntryAmount));
			System.out.println("Here 5: " + p_Name + " p_ColumnTitle: " + p_ColumnTitle + "Amount:" + p_EntryAmount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	List<SendPrograms> programList = new ArrayList<SendPrograms>();
	public void SendPrograms(String p_Code, String p_Name, String p_Description, int p_StartDate, int p_Duration, String p_AccountCode, String p_AccountName, int p_DedicatedFunds, double p_TransferDebit, double p_TransferCredit, double p_FundsAfterTransfers, double p_PastYearsSpending, int p_RemainingFunds, int p_CurrentYearBudget, double p_Commitments, double p_Percentage ) {
		// TODO Auto-generated method stub
		try {
			programList.add( new SendPrograms(p_Code, p_Name, p_Description, p_StartDate, p_Duration, p_AccountCode, p_AccountName, p_DedicatedFunds, p_TransferDebit, p_TransferCredit, p_FundsAfterTransfers, p_PastYearsSpending, p_RemainingFunds, p_CurrentYearBudget, p_Commitments,p_Percentage ));
			System.out.println("Here 6: " + p_Code + " Account: " + p_AccountCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	List<SendSections> sectionList =  new ArrayList<SendSections>();
	public void SendSections(String p_Code, String p_Name, int p_RequestedFund, int p_RequestedRevenue) {
		// TODO Auto-generated method stub
		try {
			sectionList.add(new SendSections( "SendSections", p_Code, p_Name, String.valueOf( p_RequestedFund ), String.valueOf( p_RequestedRevenue )));
			System.out.println("Here 7: " + p_Code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	List<SendTAccount> TAccountList = new ArrayList<SendTAccount>();
	public void SendTAccount(String p_Code, double p_Amount, int p_Date, String p_Description, String p_Type ) {
		// TODO Auto-generated method stub
		try {
			TAccountList.add( new SendTAccount( p_Code, p_Amount, p_Date, p_Description, p_Type));
			System.out.println("Here 8: " + p_Code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	List<SendTransferColumns> listTransferColumns = new ArrayList<SendTransferColumns>();
	public void SendTransferColumns(String p_Column, String p_Description ) {
		// TODO Auto-generated method stub
		try {
			listTransferColumns.add(new SendTransferColumns(p_Column, p_Description));
			//TAccountList.add( new SendTAccount( p_Code, p_Amount, p_Date, p_Description));
			System.out.println("Here 9: " + p_Column);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	

	/*List<SendChapters> chapterList = new ArrayList<SendChapters>();
	public void SendChapters(String p_Code, String p_Name, int p_Ceiling, int p_Requested) {
		
		try {
			chapterList.add(new SendChapters( "SendChapters", p_Code, p_Name, String.valueOf( p_Ceiling ), String.valueOf( p_Requested )));
			System.out.println("BudgetList========>" + p_Code); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	/*public void SendSectionsCeiling(String p_Code, String p_Name, int p_Fund, int p_Revenue) {
		// TODO Auto-generated method stub
		try {
			SendSectionsCeiling msg = new SendSectionsCeiling( "SendSectionsCeiling", p_Code, p_Name, String.valueOf( p_Fund ), String.valueOf( p_Revenue ));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void SendSectionsRequested(String p_Code, String p_Name, int p_RequestedFund, int p_RequestedRevenue) {
		// TODO Auto-generated method stub
		try {
			SendSectionsRequested msg = new SendSectionsRequested( "SendSectionsRequested", p_Code, p_Name, String.valueOf( p_RequestedFund ), String.valueOf( p_RequestedRevenue ));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/

	/* Methods for port Projects */
/*
	@PostMapping(path="/addMilestone", consumes="application/json")
	public void AddMilestone(String p_Name, String p_FullCode, String p_SucessCriteria, int p_CompletePlanned, int p_Weight, int p_Percentage, String p_Notes, String p_Initiative) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Projects().AddMilestone(p_Name, p_FullCode, p_SucessCriteria, p_CompletePlanned, p_Weight, p_Percentage, p_Notes, p_Initiative);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	@PostMapping(path="/createInitiative", consumes="appliaction/json")
	public void CreateInitiative(String p_Name, String p_ShortNumber, String p_LongNumber, String p_Description, int p_StartDate, int p_EndDate, double p_Budget, String p_Strategy) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Projects().CreateInitiative(p_Name, p_ShortNumber, p_LongNumber, p_Description, p_StartDate, p_EndDate, p_Budget, p_Strategy);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(path="/createProject", consumes="application/json")
	public void CreateProject( String p_Code,  String p_Name,  String p_Description,  int p_StartDate,  int p_EndDate,  double p_Budget,  int p_Duration,  String p_Sponsor ) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Projects().CreateProject(p_Code, p_Name, p_Description, p_StartDate, p_EndDate, p_Budget, p_Duration, p_Sponsor);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(path="/createStrategy", consumes="application/json")
	public void CreateStrategy(String p_Number, String p_Name, String p_Description ) {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Projects().CreateStrategy(p_Number, p_Name, p_Description);
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@PutMapping(path="/CreateChangeRequest", consumes="application/json", produces="application/json")	
	public List<ChangeRequest> CreateChangeRequest(@RequestBody ChangeRequest chreq ) {
		// TODO Auto-generated method stub
		try {
			changes.clear();
			UI.Singleton().Projects().CreateChangeRequest(chreq.getP_Title(),chreq.getP_Description(), chreq.getP_Justification(), chreq.getP_ProjectCode(),  chreq.getP_ImpactCategory(), chreq.getP_ImpactDetails());
			Thread.sleep(700);
			return changes;
		
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	

	@PutMapping(path="/ReadChangeRequests", produces="application/json", consumes="application/json")
	public List<ChangeRequest> ReadChangeRequests(@RequestBody ChangeRequest chrq) {
		// TODO Auto-generated method stub
		try {
			changes.clear();
			UI.Singleton().Projects().ReadChangeRequests(chrq.getP_ProjectCode());
			Thread.sleep(700);
			return changes;
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
 	List<ChangeRequest> changes = new ArrayList<ChangeRequest>();
	@GetMapping(path="/ReadAllChangeRequest", produces="application/json")
	public List<ChangeRequest> ReadAllChangeRequest() {
		// TODO Auto-generated method stub
		try {
			changes.clear();
			UI.Singleton().Projects().ReadAllChangeRequest();
			Thread.sleep(700);
			return changes;
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void sendProjectMember (  String p_MemberName,   String p_Role ) {
          
          try {
            UI.Singleton().Projects().sendProjectMember( p_MemberName, p_Role);
         }catch(Exception e) {
      	   
         } 
         } 

	
		public void SendProjects( final String p_Code, final String p_Name, final String p_Description, final int p_StartDate, final int p_EndDate, final double p_Budget, final int p_Duration, final String p_Sponsor){
        Project proj;
          try {
			proj=new Project(p_Code, p_Name, p_Description, p_StartDate, p_EndDate, p_Budget , p_Duration, p_Sponsor);
			projects.add(proj);

         }catch(Exception e) {
      	   	e.printStackTrace();

         } 
         }

	@PutMapping(path="/ReadProject", produces="application/json", consumes="application/json")
	public List<Project> ReadProject(@RequestBody Project pro) {
		// TODO Auto-generated method stub
		try {
			projects.clear();
			UI.Singleton().Projects().ReadProject(pro.getP_Code());
			Thread.sleep(700);
			return projects;
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	List<Project> projects=new ArrayList<Project>();
	@GetMapping(path="/ReadAllTheProjects", produces="application/json")
	public List<Project> ReadAllTheProjects() {
		// TODO Auto-generated method stub
		try {
			projects.clear();
			UI.Singleton().Projects().ReadAllTheProjects();
			Thread.sleep(700);
			return projects;
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	//المخرجات
	List<Outcome> outs=new ArrayList<Outcome>();
    public void SendProjectOutcome(final String p_Title,  final String p_Description,  final int p_StartDate,  final int p_EndDate ){
        Outcome out=null;
          try {
			out=new Outcome( p_Title,p_Description,p_StartDate,p_EndDate);
			outs.add(out);

         }catch(Exception e) {
      	   	e.printStackTrace();

         } 
         } 
	@PutMapping(path="/ReadProjectOutcome", consumes="application/json", produces="application/json")	
	public List<Outcome> ReadProjectOutcome(@RequestBody Outcome out ){
          
          try {
			outs.clear();
            UI.Singleton().Projects().ReadProjectOutcome(out.getP_ProjectCode());
			Thread.sleep(700);
			return outs;
         }catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
        } 
		@PutMapping(path="/AddProjectOutcome", consumes="application/json", produces="application/json")
		public List<Outcome> AddProjectOutcome(@RequestBody Outcome out ){
          
        try {	
		outs.clear();  
        UI.Singleton().Projects().AddProjectOutcome(out.getP_Title() , out.getP_ProjectCode(), out.getP_Description(),out.getP_StartDate(),out.getP_EndDate());
		Thread.sleep(700);
		return outs;
	     }catch(Exception e) {
      	   
         } 
		 return null;
         } 
		//التسليمات 
		List<String>  delvsTitle=new ArrayList<String>();
		public void SendOutcomeDeliverable( final String p_Title ){
        Deliverable dlev=null;
          try {
			delvsTitle.add(p_Title);

         }catch(Exception e) {
      	   	e.printStackTrace();

         } 
         } 

		@PutMapping(path="/ReadOutcomeDeliverable", consumes="application/json", produces="application/json")	
		public List<String> ReadOutcomeDeliverable(@RequestBody Deliverable delv ){
          
          try {
			delvsTitle.clear();
            UI.Singleton().Projects().ReadOutcomeDeliverable(delv.getP_ProjectCode(),delv.getP_OutcomeTitle());
			Thread.sleep(700);
			return delvsTitle;
         }catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
        } 
		
		@PostMapping(path="/AddOutcomeDelivrable", consumes="application/json")
		public void AddOutcomeDelivrable(@RequestBody Deliverable dev ){
          
        try {	
		for(int i = 0; i < dev.getP_Dlivrables().length; i++) {
        UI.Singleton().Projects().AddOutcomeDelivrable(dev.getP_ProjectCode(),dev.getP_OutcomeTitle() , dev.getP_Dlivrables()[i]);
			}
	     }catch(Exception e) {
      	   
         } 

         } 


	List<Purchase> procurs=new ArrayList<Purchase>();
    public void SendProjectPurchase( final String p_Scope,  final double p_ExpectetCost, final String p_PurchaseMethod ){
        Purchase prch=null;
          try {
			prch=new Purchase(p_Scope,p_ExpectetCost,p_PurchaseMethod);
			procurs.add(prch);

         }catch(Exception e) {
      	   	e.printStackTrace();

         } 
         } 

		@PostMapping(path="/AddProjectPurchase", consumes="application/json")
		public void AddProjectPurchase(@RequestBody Purchase purch ){
          try {
        UI.Singleton().Projects().AddProjectPurchase(purch.getP_ProjectCode() , purch.getP_Scope(), purch.getP_ExpectetCost(),purch.getP_PurchaseMethod());
         }catch(Exception e) {
      	   
         } 
         } 

		@PutMapping(path="/ReadProjectPurchase", consumes="application/json", produces="application/json")	
		public List<Purchase> ReadProjectPurchase(@RequestBody Purchase purch ){
          
          try {
			procurs.clear();
            UI.Singleton().Projects().ReadProjectPurchase(purch.getP_ProjectCode());
			Thread.sleep(700);
			return procurs;
         }catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
        } 

// Project Scope
List<Scope> scopes=new ArrayList<Scope>();
    public void SendProjectScope( final String p_Title,  final boolean p_IsInScope,  final String p_Description ){
        Scope scope=null;
          try {
			scope=new Scope(p_Title,p_IsInScope,p_Description);
			scopes.add(scope);

         }catch(Exception e) {
      	   	e.printStackTrace();

         } 
         } 

		@PostMapping(path="/AddProjectScope", consumes="application/json")
		public void AddProjectScope(@RequestBody Scope scope ){
          try {
        UI.Singleton().Projects().AddProjectScope(scope.getP_ProjectCode() , scope.getP_Title(), scope.getP_IsInScope(),scope.getP_Description());
         }catch(Exception e) {
      	   
         } 
         } 

		@PutMapping(path="/ReadProjectScope", consumes="application/json", produces="application/json")	
		public List<Scope> ReadProjectScope(@RequestBody Scope scope ){
          
          try {
			scopes.clear();
            UI.Singleton().Projects().ReadProjectScope(scope.getP_ProjectCode());
			Thread.sleep(700);
			return scopes;
         }catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
        } 
// Project Constrains
List<Constraint> consts=new ArrayList<Constraint>();
    public void SendProjectConstraint( final String p_Title, final String p_Note ){
        Constraint constraint=null;
          try {
			constraint=new Constraint(p_Title,p_Note);
			consts.add(constraint);

         }catch(Exception e) {
      	   	e.printStackTrace();

         } 
         } 

		@PostMapping(path="/AddProjectConstraint", consumes="application/json")
		public void AddProjectConstraint(@RequestBody Constraint constraint ){
          try {
        UI.Singleton().Projects().AddProjectConstraint(constraint.getP_ProjectCode() , constraint.getP_Title(),constraint.getP_Note());
         }catch(Exception e) {
      	   
         } 
         } 

		@PutMapping(path="/ReadProjectConstraint", consumes="application/json", produces="application/json")	
		public List<Constraint> ReadProjectConstraint(@RequestBody Constraint constraint ){
          
          try {
			consts.clear();
            UI.Singleton().Projects().ReadProjectConstraint(constraint.getP_ProjectCode());
			Thread.sleep(700);
			return consts;
         }catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
        } 
		// Project Assumption
List<Assumption> Assmps=new ArrayList<Assumption>();
    public void SendProjectAssumption( final String p_Title,  final String p_Description,  final String p_Impact,  final int p_ValidUntil ) {
        Assumption assump=null;
          try {
			assump=new Assumption(p_Title,p_Description,p_Impact,p_ValidUntil);
			Assmps.add(assump);

         }catch(Exception e) {
      	   	e.printStackTrace();

         } 
         } 
		@PostMapping(path="/AddProjectAssumption", consumes="application/json")
		public void AddProjectAssumption(@RequestBody Assumption assump ){
          try {
        UI.Singleton().Projects().AddProjectAssumption(assump.getP_ProjectCode() , assump.getP_Title(),assump.getP_Description(),assump.getP_Impact(),assump.getP_ValidUntil());
         }catch(Exception e) {
      	   
         } 
         } 

		@PutMapping(path="/ReadProjectAssumption", consumes="application/json", produces="application/json")	
		public List<Assumption> ReadProjectAssumption(@RequestBody Assumption assump ){  
          try {
			Assmps.clear();
            UI.Singleton().Projects().ReadProjectAssumption(assump.getP_ProjectCode());
			Thread.sleep(700);
			return Assmps;
         }catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
        } 

		// Project Stakeholders
List<Stakeholder> stkholders=new ArrayList<Stakeholder>();
    public void SendProjectStakeholder(  final String p_Name,  final String p_Influence,  final String p_Priorty,  final String p_Interest ) {
        Stakeholder holder=null;
          try {
			holder=new Stakeholder(p_Name,p_Influence,p_Priorty,p_Interest);
			stkholders.add(holder);

         }catch(Exception e) {
      	   	e.printStackTrace();

         } 
         } 
		@PostMapping(path="/AddProjectStakeholder", consumes="application/json")
		public void AddProjectStakeholder(@RequestBody Stakeholder holder ){
          try {
        UI.Singleton().Projects().AddProjectStakeholder(holder.getP_ProjectCode() , holder.getP_Name(),holder.getP_Influence(),holder.getP_Priorty(),holder.getP_Interest());
         }catch(Exception e) {
      	   
         } 
         } 

		@PutMapping(path="/ReadProjectStakeholder", consumes="application/json", produces="application/json")	
		public List<Stakeholder> ReadProjectStakeholder(@RequestBody Stakeholder holder ){  
          try {
			stkholders.clear();
            UI.Singleton().Projects().ReadProjectStakeholder(holder.getP_ProjectCode());
			Thread.sleep(700);
			return stkholders;
         }catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
        }
	// Project Requirements
List<Requirement> requirments=new ArrayList<Requirement>();
    public void SendProjectRequirement(  final String p_Title,  final double p_Cost ) {
        Requirement req=null;
          try {
			req=new Requirement(p_Title,p_Cost);
			requirments.add(req);

         }catch(Exception e) {
      	   	e.printStackTrace();

         } 
         } 
		@PostMapping(path="/AddProjectRequirement", consumes="application/json")
		public void AddProjectRequirement(@RequestBody Requirement req ){
          try {
        UI.Singleton().Projects().AddProjectRequirement(req.getP_ProjectCode() , req.getP_Title(),req.getP_Cost());
         }catch(Exception e) {
      	   
         } 
         } 

		@PutMapping(path="/ReadProjectRequirement", consumes="application/json", produces="application/json")	
		public List<Requirement> ReadProjectRequirement(@RequestBody Requirement req ){  
          try {
			requirments.clear();
            UI.Singleton().Projects().ReadProjectRequirement(req.getP_ProjectCode());
			Thread.sleep(700);
			return requirments;
         }catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
        } 
		//Project Goal
		List<Goal> goals=new ArrayList<Goal>();
		 public void SendProjectGoal( String p_Description,String p_Impact,String p_KPI ){
        Goal goal=null;
          try {
			goal=new Goal(p_Description,p_Impact,p_KPI);
			goals.add(goal);

         }catch(Exception e) {
      	   	e.printStackTrace();

         } 
         } 

		@PostMapping(path="/AddGoal", consumes="application/json")
		public void AddGoal(@RequestBody Goal goal ){
          
          try {
			  
            UI.Singleton().Projects().AddGoal(goal.getP_Description() , goal.getP_Impact(), goal.getP_ProjectCode(), goal.getP_KPI());
         }catch(Exception e) {
      	   
         } 
         } 
		 
		@PostMapping(path="/UpdateGoal", consumes="application/json")
		public void UpdateGoal(@RequestBody Goal goal ){
          
          try {
            UI.Singleton().Projects().UpdateGoal(goal.getP_OldDescription(),goal.getP_Description() , goal.getP_Impact(), goal.getP_ProjectCode(), goal.getP_KPI());
         }catch(Exception e) {
      	   
         } 
         }

		@PutMapping(path="/ReadProjectGoals", consumes="application/json", produces="application/json")	
		public List<Goal> ReadProjectGoals(@RequestBody Goal goal ){
          
          try {
			goals.clear();
            UI.Singleton().Projects().ReadProjectGoals(goal.getP_ProjectCode());
			Thread.sleep(700);
			return goals;
         }catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
        } 

		@PostMapping(path="/AddProjectMilestone", consumes="application/json")
		public void AddProjectMilestone(@RequestBody Milestone ms ){
          
          try {
            UI.Singleton().Projects().AddProjectMilestone( ms.getP_Name(), ms.getP_CompletePlannedDate(), ms.getP_Weight() ,ms.getP_ProjectCode(),ms.getP_CommulativeWeight());
         }catch(Exception e) {
      	   
         } 
         } 
        List<Milestone> mss=new ArrayList<Milestone>();
		 public void SendProjectMilestone(  String p_Name,  int p_CompletedPlannedDate,   int p_CompletedActualDate,   int p_Weight,   boolean p_CompleteStatus, int p_CommulativeWeight ){

        Milestone ms=null;
          try {
			ms=new Milestone(p_Name,p_CompletedPlannedDate,p_CompletedActualDate, p_Weight, p_CompleteStatus, p_CommulativeWeight);
			mss.add(ms);
         }catch(Exception e) {
      	   
         } 
         }  
		 
		@PutMapping(path="/ReadProjectMilestone", consumes="application/json", produces="application/json")	
		public List<Milestone> ReadProjectMilestone(@RequestBody Milestone ms ){
          
          try {
			mss.clear();
            UI.Singleton().Projects().ReadProjectMilestone(ms.getP_ProjectCode());
			Thread.sleep(700);
			return mss;
         }catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
        } 
		
		List<Document> docs=new ArrayList<Document>();
		 public void SendDocument( String p_Title,  String p_Path,  int p_State ){
        Document doc=null;
          try {
			doc=new Document(p_Title, p_Path, p_State);
			docs.add(doc);

         }catch(Exception e) {
      	   	e.printStackTrace();

         } 
         } 
		  
		@PutMapping(path="/readDocs", consumes="application/json", produces="application/json")
		 public List<Document> ReadDocuments(Document doc) {
		// TODO Auto-generated method stub
		try {
			docs.clear();
			UI.Singleton().Projects().ReadDocuments(doc.getP_ProjectCode());
			Thread.sleep(300);
			return docs;
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping(path="/AddDoc", consumes="application/json")
		public void AddDocument(@RequestBody Document doc ){
          
          try {
            UI.Singleton().Projects().AddDocument(doc.getP_ProjectCode(),doc.getP_Title(),doc.get_Path(),doc.getP_State());
         }catch(Exception e) {
      	   
         } 
         } 
	@GetMapping(path="/readInitiatives", produces="appliaction/json")
	public List<deployment.SendInitiatives> ReadInitiatives() {
		// TODO Auto-generated method stub
		try {
			initiativesList.clear();
			UI.Singleton().Projects().ReadInitiatives();
			Thread.sleep(300);
			return initiativesList;
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping(path="/readMilestones", produces="application/json")
	public List<SendMilestones> ReadMilestones() {
		// TODO Auto-generated method stub
		try {
			milestoneList.clear();
			UI.Singleton().Projects().ReadMilestones("مبادرة إطار تنظيمي لتعزيز البرمجيات الحرة ومفتوحة المصدر", "687","");
			Thread.sleep(300);
			return milestoneList;
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
/*	@GetMapping(path="/readProjects", produces="application/json")
	public void ReadProjects() {
		// TODO Auto-generated method stub
		try {
			UI.Singleton().Projects().ReadProjects();
		} catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
		
	@GetMapping(path="/readStrategies", produces="application/json")
	public List<SendStrategies> ReadStrategies() {
		// TODO Auto-generated method stub
		try {
			strategeList.clear();
			UI.Singleton().Projects().ReadStrategies();
			Thread.sleep(300);
			return strategeList;
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void Project(String p_msg, boolean p_state) {
		// TODO Auto-generated method stub
		try {
			ProjectReply x = new ProjectReply(p_msg, p_state);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void SendChangeRequest( final int p_CreationDate, final String p_Title, final String p_Description,  final String p_Justification ) {
        ChangeRequest chread=null;
		try{
			chread=new ChangeRequest(p_CreationDate,p_Title,p_Description,p_Justification);
			changes.add(chread);

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@PostMapping(path="/SendStrategies", consumes="application/json")
	public void SendStrategies(@RequestBody Stratgey stratgey) {
		try{
			UI.Singleton().Projects().SendStrategies(stratgey.getP_Number(),stratgey.getP_Name(),stratgey.getP_Description());
			}catch(Exception e){
			e.printStackTrace();
		}


	}
	@PostMapping(path="/AddChangeRequestImpact", consumes="application/json")
	public void AddChangeRequestImpact( @RequestBody Impact Impacts) {
		ChangeRequest chreq;
		try{
			//int i=1; // skipped the first impact because it was sent in the CreateChangeRequest
			for(int i = 1; i < Impacts.getP_Impacts().length; i++) {
			chreq=new ChangeRequest(Impacts.getP_Impacts()[i], Impacts.getP_Details()[i], Impacts.getP_CreationDate() );
			UI.Singleton().Projects().AddChangeRequestImpact(chreq.getP_ProjectCode(),chreq.getP_ImpactCategory(),chreq.getP_ImpactDetails(),chreq.getP_Title());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

		List<ChangeRequest> ReadImpacts=new ArrayList<ChangeRequest>();
	   public void SendChangeRequestImpact(  String p_Category,  String p_Details)  {		
        ChangeRequest imp;
		 try {
		
			if(p_Category.equals("time")){	
			 String[] arrSplit = p_Details.split(",");
			imp=new ChangeRequest(p_Category, arrSplit[0],arrSplit[1]);
 			}else{
			imp=new ChangeRequest(p_Category, p_Details);
			}
			ReadImpacts.add(imp);
         }catch(Exception e) {
      	   
         } 
         } 

		@PutMapping(path="/ReadChangeRequestImpact", consumes="application/json", produces="application/json")	
		public List<ChangeRequest> ReadChangeRequestImpact(@RequestBody ChangeRequest ch ){

          try {
			//categories.clear();
			ReadImpacts.clear();
            UI.Singleton().Projects().ReadChangeRequestImpact(ch.getP_ProjectCode(),ch.getP_Title());
			Thread.sleep(700);
			//pimpacts=new Impact(categories.toArray(new String[0]),details.toArray(new String[0]));
			return ReadImpacts;
         }catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
        } 

		List<Risk> risks=new ArrayList<Risk>();
        public void SendProjectRisk( String p_Title, String p_Severity, String p_Probability,boolean p_IsIssue, boolean p_IsOpen, String p_MitigationPlan, int p_ExpectedDeadline )  {
		Risk risk=null;
		
         try {
			risk=new Risk(p_Title, p_Severity, p_Probability, p_IsIssue, p_IsOpen, p_MitigationPlan, p_ExpectedDeadline );
			risks.add(risk);
         }catch(Exception e) {
      	   
         } 
         } 

		 @PutMapping(path="/ReadProjectRisks", consumes="application/json", produces="application/json")	
		public List<Risk> ReadProjectRisks(@RequestBody Risk risk ){
          try {
			risks.clear();
            UI.Singleton().Projects().ReadProjectRisks(risk.getP_ProjectCode());
			Thread.sleep(700);
			return risks;
         }catch (XtumlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
        } 

		@PostMapping(path="/AddProjectRisk", consumes="application/json")
		public void AddProjectRisk(@RequestBody Risk risk ){
          
          try {
			  //risk.getP_IsOpen() risk.getP_IsIssue()
            UI.Singleton().Projects().AddProjectRisk(risk.getP_ProjectCode(), risk.getP_Title(), risk.getP_Severity() ,risk.getP_Probability(),risk.getP_IsIssue(),risk.getP_IsOpen(),risk.getP_MitigationPlan(),risk.getP_ExpectedDeadline());
         }catch(Exception e) {
      	   
         } 
         } 

		@PostMapping(path="/UpdateRisk", consumes="application/json")
		public void UpdateRisk(@RequestBody Risk risk ){
          
          try {

            UI.Singleton().Projects().UpdateRisk(risk.getP_OldTitle(),risk.getP_ProjectCode(), risk.getP_Title(), risk.getP_Severity() ,risk.getP_Probability(),risk.getP_IsIssue(),risk.getP_IsOpen(),risk.getP_MitigationPlan(),risk.getP_ExpectedDeadline());
         }catch(Exception e) {
      	   
         } 
         }


	List<SendInitiatives> initiativesList = new ArrayList<SendInitiatives>();
	public void SendInitiatives(String p_Name, String p_ShortNumber, String p_LongNumber, String p_Description, int p_StartDate, int p_EndDate ) {
		// TODO Auto-generated method stub
		try {
			initiativesList.add(new SendInitiatives(p_Name, p_ShortNumber, p_LongNumber, p_Description, p_StartDate, p_EndDate));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	List<SendMilestones> milestoneList = new ArrayList<SendMilestones>();
	public void SendMilestones(String p_Name, String p_FullCode, String p_Code, String p_Type, String p_SuccessCriteria,
			int p_CompletePlanned, int p_CompleteActual, boolean p_Complete, int p_Weight, int p_Percentage,
			String p_sdState, String p_sdDescription, String p_Notes, String p_IncompleteReasons) {
		
		try {
			milestoneList.add( new SendMilestones(p_Name, p_FullCode, p_Code, p_Type, p_SuccessCriteria, p_CompletePlanned, p_CompleteActual, p_Complete, p_Weight, p_Percentage, p_sdState, p_sdDescription, p_Notes, p_IncompleteReasons));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	List<SendStrategies> strategeList = new ArrayList<SendStrategies>();
	public void SendStrategies(String p_Number, String p_Name, String p_Description) {
		
		try {
			strategeList.add(new SendStrategies(p_Number, p_Name, p_Description));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//methods that was not created by the compiler
	
	@GetMapping(path="/role", produces="application/json")
	public String role() {
		try {
			UI.Singleton().Authenticate().ReadEmployeePermissions(userChecked.getP_EmployeeID());
			Thread.sleep(700);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		int size = roles.size();
		if(size == 1) {
			System.out.printf("--------------%s----------------", roles.get(0).getP_GroupName());
			return roles.get(0).getP_GroupName();
		}
		String role = "null";
		for(int i = 0; i < size; i++) {
			if(roles.get(i).getP_GroupName().equals("Managers")) {
				role = "Managers";
				roles.clear();
				return role;
			}else {
				if(roles.get(i).getP_GroupName().equals("HR")) {
					role = "HR";
				}else {
					if(roles.get(i).getP_GroupName().equals("Employees") && !role.equals("HR")) {
						role = "Employees";
					}
				}
			}
		}
		roles.clear();
		return role;
	}
	
	@GetMapping(path="/id", produces="application/json")
	public int id() {
		return userChecked.getP_EmployeeID();
	}
	
	private int clickedEmployeeID; //when a manager/hr user clicks to view an employee this attribute holds the id of that employee to view in details
	
	@PostMapping(path="/saveID", consumes="application/json")
	public void saveID(@RequestBody Employee employee){
		clickedEmployeeID =  employee.getP_EmployeeID();
	}
	
	@GetMapping(path="/employeeInfo", produces="application/json")
	public List<SendEmployee> employeeInfo() {
		List<SendEmployee> employee = new  ArrayList<SendEmployee>();
		int size = employeeList.size();
		for(int i = 0; i < size; i++) {
			if(employeeList.get(i).getP_EmployeeID() == clickedEmployeeID) {
				employee.add(employeeList.get(i));
				return employee;
			}
		}
		return null;
	}
	
	@GetMapping(path="/listOfRequestedLeaves", produces="application/json")
	public List<List<String>> listOfRequestedLeaves(){
		return listOfRequestedLeaves;
	}
	
	private String gradeName; //the name of the grade which the user wants to see the detailed info for it
	@PostMapping(path="/saveGradeID", consumes="application/json")
	public void saveGradeID(@RequestBody Grade grade) {
		gradeName = grade.getP_GradeName();
	}
	
	@GetMapping(path="/gradeInfo", produces="application/json")
	public List<SendGrades> gradeInfo() {
		List<SendGrades> grade = new ArrayList<SendGrades>();
		int size = gradeList.size();
		for(int i = 0; i < size; i++) {
			if(gradeList.get(i).getP_Name().equals(gradeName)) {
				grade.add(gradeList.get(i));
				return grade;
			}
		}
		return null;
	}
	
	@RequestMapping("/logout")
	public RedirectView logout() {
		clickedEmployeeID = 0;
		userChecked = null;
		loged_in = false;
		return new RedirectView("/");
	}
	
	private String budgetYear;
	@PostMapping(path="/saveBudgetYear", consumes="application/json")
	public void saveBudgetYear(@RequestBody Budget budget){
		budgetYear = budget.getP_Year();
		System.out.printf("-----%s", budgetYear);
	}
	
	private String chapterCode;
	@PostMapping(path="/saveChapter", consumes="application/json")
	public void saveChapter(@RequestBody Chapter chapter) {
		chapterCode = chapter.getP_Code();
	}
	
	@PostMapping(path="/array", consumes="application/json")
	public void array(@RequestBody Test test) {
		int size = test.getArray().length;
		for(int i = 0; i < size; i++) {
			System.out.println(test.getArray()[i]);
		}
	}
	private int employeeBonus;
	@PostMapping(path="/employeeBonusInfo", consumes="application/json")
	public void emplyeeBonusInfo(@RequestBody Employee employee) {
		employeeBonus = employee.getP_EmployeeID();
	}
	
	int count = 0;
	@PostMapping(path="/alwaysrunning", consumes="application/json")
	public void alwaysRunning(@RequestBody Employee employee) {
		try {
			System.out.printf("-----------------Timer running----------");
			if(count == 0) {
				count = 1;
			while(true) {
				Thread.sleep(300000);
				System.out.println("5 minutes");
			}
			}
		}catch(Exception e) {
			
		}
	}
	
	@PostMapping(path="/saveTaccount", consumes="application/json")
	public void saveTaccount(@RequestBody Chapter chapter) {
		Taccount = chapter.getP_Code();
	}
	List<SendChartAccounts> savedAccount = new ArrayList<SendChartAccounts>();
	@PostMapping(path="/saveAccount", consumes="application/json")
	public void saveAccount(@RequestBody SendChartAccounts account) {
		savedAccount.clear();
		savedAccount.add(new SendChartAccounts(account.getP_Code(), account.getP_Name(), account.getP_OpeningBalance(), account.getP_AddedFunds(), account.getP_Reduction(),
				account.getP_TransferDebit(), account.getP_TransferCredit(), account.getP_NetBalance(), account.getP_Spending(), account.getP_GrandTotal(),
				account.getP_DocumantryCredits(), account.getP_Custodies(), account.getP_Paid(), account.getP_RemainingBalance(), account.getP_Percentage()));
	}
	
	@GetMapping(path="/readSavedAccount", produces="application/json")
	public List<SendChartAccounts> readSavedAccount(){
		return savedAccount;
	}
	
	@GetMapping(path="/budgetCreated", produces="application/json")
	public int budgetCreated() {
		return BudgetCreated;
	}

	@RequestMapping(path="/test01")
	public String test01() {
		try {
			UI.Singleton().App().GeneratePayslips();
			Thread.sleep(300);
			return "HERE!";
		} catch (XtumlException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
