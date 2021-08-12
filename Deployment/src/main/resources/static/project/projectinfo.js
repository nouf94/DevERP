var Impacts=[];
var Details=[]; 
var totalWeight=0;
var totalCMWeight=0;

//Add ProjectInfo Instance
var ProjectInfoApp = new Vue({
  el: '#projectInfo',
  data:{
    p_Code:'Project2Code',
    ProjectInfo:''
  },
  mounted: function mounted () {
    this.readProjectInfo()
    //this.ReadStrategies()

  },
  methods: {
    readProjectInfo:function (event){
      //console.log(this.$route)
      axios.put('/rest/ReadProject',{
        p_Code: this.p_Code
      }).then(response => (this.ProjectInfo = response.data[0],
        $("#projectName").html(this.ProjectInfo.p_Name),
        $("#ProjectDesc").html(this.ProjectInfo.p_Description),
         date1 = new Date(null),
        console.log(this.ProjectInfo),
        date1.setTime(this.ProjectInfo.p_StartDate*1000),
        this.ProjectInfo.p_StartDate=date1.toLocaleDateString(),
         date2 = new Date(),
        // To calculate the time difference of two dates
         Difference_In_Time = date1.getTime() - date2.getTime(),
        // To calculate the no. of days between two dates
        ProgressApp.actualDays = Difference_In_Time / (1000 * 3600 * 24),
        ProgressApp.Delay=ProjectInfoApp.ProjectInfo.p_Duration-ProgressApp.actualDays
        )).catch(error => {
            console.log(error)
        })
      }
    }//End Methods
});//End Vue ProjectInfo


//Change Request Instance
var app = new Vue({
    el: '#nav-requests',
    data:{
      p_Title:'',
      p_Description: '',
      p_Justification: '',
      p_ProjectCode: 'Project1Code',
      p_ImpactCategory: '',
      p_ImpactDetails: '',
     Requests:'',
      errors: {
        name: false,
        email: false
      },
      selectedRequest:'',
      selectedImpacts:'',
      Impacts:''
  
    },
    mounted: function mounted () {
      this.readChnages()
    },
    methods: {
      readChnages:function (event){
        axios.put('/rest/ReadChangeRequests',{
          p_ProjectCode: this.p_ProjectCode
        }).then(response2 => (this.Requests = response2.data,
          //console.log(response2),
          ProcessRequest(this.Requests)
            )).catch(error => {
              console.log(error.response)
          })},
      submitValues: function (event) {
        GetImpact();
          axios.put('/rest/CreateChangeRequest', {
            p_Title: this.p_Title,
            p_Description: this.p_Description,
            p_Justification: this.p_Justification,
            p_ProjectCode: this.p_ProjectCode,
            p_ImpactCategory: Impacts[0],
            p_ImpactDetails: Details[0]
            }).then(response => {
              //If the Change request has more than one Impact submit the rest Impacts here
            if(Impacts.length>1){
             this.submitImpact(response.data[0]['p_Title'])
            }
            $('#pchange-form')[0].reset();//Clear Form after Submit Seccssfully
             $("#Alert").show();
             this.readChnages()//To Update List of Requests
            }).catch(error => {
              $("#Error").show()
                console.log(error)
            });
        },//End SubmitValues Method  
        submitImpact: function (ChangeReq) {
            axios.post('/rest/AddChangeRequestImpact', {
              p_ProjectCode: this.p_ProjectCode,
              p_Impacts: Impacts,
              p_Details: Details,
              p_ChangeRequestTitle:ChangeReq
              }).then(response => {
                console.log(response)
              }).catch(error => {
                  console.log(error)
              });
          },//End submitImpact Method  
          ReadChangeRequestImpact: function (selectedChange) {
            axios.put('/rest/ReadChangeRequestImpact', {
              p_ProjectCode: this.p_ProjectCode,
              p_ChangeRequestTitle:selectedChange
              }).then(response => {
                this.selectedImpacts=response.data;
                //ConvertDatetoJSON(this.selectedImpacts);
                console.log(response.data)
              }).catch(error => {
                  console.log(error)
              });
          },//End read GetImpact Method 
          viewRequest:function(event){
            this.selectedImpacts="";
            index=(event.target.parentElement.rowIndex)-1;
            this.selectedRequest=this.Requests[index];
            this.ReadChangeRequestImpact(this.selectedRequest.p_Title)

          } 
      }//End  Methods
    
});//End Vue 

//Add Goal Instance
var GoalApp = new Vue({
  el: '#nav-goals',
  data:{
    p_GoalEffect:'',
    p_gName:'',
    p_ProjectCode:'Project1Code',
    p_OldDescription:'',
    Goals:'',
    Strategies:'',
    errors: {
      name: false,
      email: false
    },
    selectedGoal:''
  },
  mounted: function mounted () {
    this.readGoals()
    //this.ReadStrategies()

  },
  methods: {
    readGoals:function (){
      axios.put('/rest/ReadProjectGoals',{
        p_ProjectCode: this.p_ProjectCode
      }).then(response2 => (this.Goals = response2.data,
        //this.selectedGoal=this.Goals[0],
        showGoals(this.Goals)
          )).catch(error => {
            console.log(error)
        })},
        ReadStrategies:function (){
          axios.get('/rest/readStrategies')
          .then(response2 => (this.Strategies = response2.data,
            console.log(response2)
              )).catch(error => {
                console.log(error)
            })},
    AddGoal: function (event) {
        axios.post('/rest/AddGoal', {
          p_Description: this.p_gName,
          p_Impact: $('#GEffect :selected').val(),
          p_ProjectCode: this.p_ProjectCode,
          p_KPI: this.p_GoalEffect,
          }).then(response => {
           //console.log(response)
           $("#Alert").show();
           this.readGoals()//To Update List of Goals
          }).catch(error => {
            $("#Error").show();
              console.log(error)
          });
      },//End Add Goal Method  
      viewGoalUpdate: function (event){
                //Get The selected element to update 
                this.selectedGoal="";
                index=(event.target.parentElement.parentElement.parentElement.parentElement.rowIndex)-1;
                this.selectedGoal=this.Goals[index];
                this.p_gName=this.selectedGoal.p_Description;
                $('#UGEffect').val(this.selectedGoal.p_Impact);
      }, 
      UpdateGoal: function (event) {
          axios.post('/rest/UpdateGoal', {
          p_OldDescription:this.selectedGoal.p_Description,
          p_Description: this.p_gName,
          p_Impact:$('#UGEffect :selected').val(),
          p_ProjectCode: this.p_ProjectCode,
          p_KPI: this.p_GoalEffect,
          }).then(response => {
           $("#Update").show();
           this.readGoals()//To Update List of Goals
          }).catch(error => {
            $("#Error").show();
              console.log(error)
          });
      },//End Update Goal Method  
      viewGoal:function(event){
        this.selectedGoal="";
        index=(event.target.parentElement.rowIndex)-1;
        this.selectedGoal=this.Goals[index];
        $('#VGEffect').val(this.selectedGoal.p_Impact);

      }  
    }//End  Methods
  
});//End Vue Goal 

//Add Risk Instance
var RiskApp = new Vue({
  el: '#nav-risks',
  data:{
    p_rName:'',
    Rtype:'',
    Reffect:'',
    rStatus:'',
    p_rOwner:'',
    Risks:'',
    p_Response:'',
    p_resolveDate:'',
    errors: {
      name: false,
      email: false
    },
    p_ProjectCode:'Project1Code',
    selectedRisk:''
  },
  mounted: function mounted () {
    this.readRisks()
  },
  methods: {
    readRisks:function (){
      axios.put('/rest/ReadProjectRisks',{
        p_ProjectCode: this.p_ProjectCode
      }).then(response => (this.Risks = response.data,
        showRisks(this.Risks)
        //console.log(response)
          )).catch(error => {
            console.log(error)
        })},
     AddProjectRisk: function (event) {
        axios.post('/rest/AddProjectRisk', {
          p_ProjectCode :this.p_ProjectCode,
          p_Title: this.p_rName,
          p_Severity: $('#Reffect :selected').val(), 
          p_Probability : $('#rProb :selected').val(),
          p_IsIssue: $('#Rtype :selected').val(),
          p_IsOpen: $('#rStatus :selected').val(),
          p_MitigationPlan:this.p_Response,
          p_ExpectedDeadline:(new Date(this.p_resolveDate).getTime() / 1000),  
          }).then(response => {
           //console.log(response)
           $("#Alert").show();
           this.readRisks()//Update Risk List
          }).catch(error => {
            $("#Error").show();
              console.log(error)
          });
      },//End Add Risk Method 
      viewRiskUpdate: function (event){
        //Get The selected element to update 
        this.selectedRisk="";
        index=(event.target.parentElement.parentElement.parentElement.parentElement.rowIndex)-1;
        if(isNaN(index)){// Depnding on if the user clicks button or li
          index=(event.target.parentElement.parentElement.parentElement.parentElement.parentElement.rowIndex)-1;
        }
        this.selectedRisk=this.Risks[index];
        //Fill the View Form
        this.p_rName=this.selectedRisk.p_Title;
        this.p_Response=this.selectedRisk.p_MitigationPlan;
        $('#UReffect').val(this.selectedRisk.p_Severity);
        $('#UrProb').val(this.selectedRisk.p_Probability);
        $('#URtype').val(this.selectedRisk.p_IsIssue);
        $('#UrStatus').val(this.selectedRisk.p_IsOpen);
        this.p_resolveDate=this.selectedRisk.p_ExpectedDeadline;
}, 
UpdateRisk: function (event) {
  axios.post('/rest/UpdateRisk', {
    p_OldTitle: this.selectedRisk.p_Title,
    p_ProjectCode :this.p_ProjectCode,
    p_Title: this.p_rName,
    p_Severity: $('#UReffect :selected').val(), 
    p_Probability : $('#UrProb :selected').val(),
    p_IsIssue: $('#URtype :selected').val(),
    p_IsOpen: $('#UrStatus :selected').val(),
    p_MitigationPlan:this.p_Response,
    p_ExpectedDeadline:(new Date(this.p_resolveDate).getTime() / 1000),
  }).then(response => {
   $("#Update").show();
   this.readRisks()//To Update List of Goals
  }).catch(error => {
    $("#Error").show();
      console.log(error)
  });
},//End Update Goal Method  
      viewRisk:function(event){
        this.selectedRisk='';
        item=(event.target.parentElement.rowIndex)-1;
        this.selectedRisk=this.Risks[item];
      }    
    }//End  Methods
  
});//End Vue risks


//Add Outcomes Instance
var OutsApp = new Vue({
  el: '#nav-outputs',
  data:{
    p_ODesc:'',
    p_OName:'',
    p_OStartDate:'',
    p_OEndDate:'',
    errors: {
      name: false,
      email: false
    },
    p_ProjectCode:'Project1Code',
    Outcomes:'',
    selectedOutcome:'',
    selectedDlevs:'',
    Dlivrables:''
  },
  mounted: function mounted () {
   this.readOutcomes()
  },
  methods: {
    readOutcomes:function (){
      axios.put('/rest/ReadProjectOutcome',{
        p_ProjectCode: this.p_ProjectCode
      }).then(response => (this.Outcomes = response.data,
        ProjOutcomes=this.Outcomes,
        //console.log("Proj outcomes defined"),
        showOutcomes(this.Outcomes)
          )).catch(error => {
            console.log(error)
        })},
        AddProjectOutcome: function (event) {
        axios.put('/rest/AddProjectOutcome', {
          p_Title :this.p_OName,
          p_ProjectCode: this.p_ProjectCode,
          p_Description:this.p_ODesc,
          p_StartDate: (new Date(this.p_OStartDate).getTime() / 1000),
          p_EndDate: (new Date(this.p_OEndDate).getTime() / 1000), 
          }).then(response => {   
            var delvs=$('ul[name^="Delv"]')
            for(i=0;i<delvs.length;i++){
              this.Dlivrables.push(delvs.text().replace('×',''));
            }       
          this.AddOutcomeDelivrable(response.data[0]['p_Title'])
           this.readOutcomes();
           $("#Alert").show();
          }).catch(error => {
            $("#Error").show();
              console.log(error)
          });
      },//End Add Outcome Method
      AddOutcomeDelivrable: function (OutcomeTitle) {      
        axios.post('/rest/AddOutcomeDelivrable', {
          p_ProjectCode:this.p_ProjectCode,
          p_Dlivrables : this.Dlivrables,
          p_OutcomeTitle: OutcomeTitle
          }).then(response => {
            //console.log(response)
          }).catch(error => {
              console.log(error)
          });
      },//End Add Delivrable Method   
      ReadOutcomeDeliverable: function (OutcomeTitle) {
        axios.put('/rest/ReadOutcomeDeliverable', {
          p_ProjectCode:this.p_ProjectCode,
          p_OutcomeTitle: OutcomeTitle
          }).then(response => {
            this.selectedDlevs=response.data;
            //ConvertDatetoJSON(this.selectedImpacts);
            //console.log(response.data)
          }).catch(error => {
              console.log(error)
          });
      },//End read GetImpact Method   
      viewOutcome:function(event){
        this.selectedOutcome='';
        item=(event.target.parentElement.rowIndex)-1;
        this.selectedOutcome=this.Outcomes[item];
        this.ReadOutcomeDeliverable(this.selectedOutcome.p_Title)

      } 
    }//End  Methods
  
});//End Vue Outcomes

//Add Procs Instance
var ProcsApp = new Vue({
  el: '#nav-procplan',
  data:{
    p_Scope:'',
    p_ExpectetCost:'',
    errors: {
      name: false,
      email: false
    },
    p_ProjectCode:'Project1Code',
    Purchases:'',
    p_Pmethod:'',
    selectedPurchase:''
  },
  mounted: function mounted () {
   this.readPurchase()
  },
  methods: {
    readPurchase:function (){
      axios.put('/rest/ReadProjectPurchase',{
        p_ProjectCode: this.p_ProjectCode
      }).then(response => (this.Purchases = response.data,
        showPurchase(this.Purchases)
          )).catch(error => {
            console.log(error)
        })},
    AddProjectPurchase: function (event) {
        axios.post('/rest/AddProjectPurchase', {
          p_ProjectCode :this.p_ProjectCode,
          p_ExpectetCost: this.p_ExpectetCost,
          p_Scope:  this.p_Scope,
          p_PurchaseMethod: this.p_Pmethod
          }).then(response => {
           this.readPurchase()
           $("#Alert").show();
          }).catch(error => {
            $("#Error").show();
              console.log(error)
          });
      },//End Add Purchase Method   
      viewPurchase:function(event){
        this.selectedPurchase='';
        item=(event.target.parentElement.rowIndex)-1;
        this.selectedPurchase=this.Purchases[item];
      } 
    }//End  Methods
  
});//End Vue procu

//Add Milestone Instance
var MilestoneApp = new Vue({
  el: '#nav-tasks',
  data:{
    p_tName:'',
    p_Mstatus:'',
    p_Mweight:'',
    p_MCweight:'',
    p_MExpDate:'',
    p_MAcutDate:'',
    Tasks:'',
    errors: {
      name: false,
      email: false
    },
    p_ProjectCode:'Project1Code',
    selectedTask:'',
    Outcomes:'',
    Risks:''
  },
  mounted: function mounted () {
    this.readMilestone()


  },
  methods: {
    readMilestone:function (){
      axios.put('/rest/ReadProjectMilestone',{
        p_ProjectCode: this.p_ProjectCode
      }).then(response => (this.Tasks = response.data,
        this.Outcomes = OutsApp.Outcomes,
        this.Risks=RiskApp.Risks,
        showTasks(this.Tasks),
        ProgressApp.Progress=Math.round(totalWeight/totalCMWeight*100),
        $(".progress-bar").width(ProgressApp.Progress+'%')
          )).catch(error => {
            console.log(error)
        })},
     AddProjectMilestone: function (event) {
        axios.post('/rest/AddProjectMilestone', {
          p_Name: this.p_tName,
          p_CompletePlannedDate: (new Date(this.p_MExpDate).getTime() / 1000) ,
          p_Weight: this.p_Mweight,
          p_CommulativeWeight:0,
          p_ProjectCode: this.p_ProjectCode
          }).then(response => {
           //console.log(response)
           $("#Alert").show();
           this.readMilestone()//Update MS
          }).catch(error => {
            $("#Error").show();
              console.log(error)
          });
      }//End Add Milestone Method 
      ,
      viewTask:function(event){
        item=(event.target.parentElement.rowIndex)-1;
        this.selectedTask=this.Tasks[item];

      }    
    }//End  Methods
  
});//End Vue Milestone 

//Add Progress Instance
var ProgressApp = new Vue({
  el: '#pProgress',
  data:{
    Progress:'',
    Delay:'',
    actualDays:''

  }
  
});//End Vue Progress 


//Add File Instance
var FilesApp = new Vue({
  el: '#nav-files',
  data:{
    p_Title:'',
    p_Path:'',
    p_State:'',
    Files:'',
    errors: {
      name: false,
      email: false
    },
    p_ProjectCode:'Project1Code',
    touploadfiles:''

  },
  mounted: function mounted () {
    //this.readFiles()
    //this.ReadStrategies()

  },
  methods: {
    readFiles:function (){
      axios.put('/rest/readDocs',{
        p_ProjectCode: this.p_ProjectCode
      }).then(response => (this.Files = response.data,
        showFiles(this.Files),
        console.log(response)
          )).catch(error => {
            console.log(error)
        })},
     UploadFiles: function (event) {
      let formData = new FormData();
      for( var i = 0; i < this.touploadfiles.length; i++ ){
        let file = this.touploadfiles[i];
        formData.append('files[' + i + ']', file);
      }
        axios.post('/rest/AddDoc', {
          p_Title:this.p_Title,
          formData,
          p_Path:this.p_Path,
          p_State:this.p_State,
          p_ProjectCode: this.p_ProjectCode
          }).then(response => {
           console.log(response)
           $("#Alert").show();
           //this.readFiles()//Update Files
          }).catch(error => {
            $("#Error").show();
              console.log(error)
          });
      },//End Add Files Method 
      handleFileUpload:function(event){
        //Enable the upload button
        $( "#uploadbtn" ).prop( "disabled", false );
        this.touploadfiles = this.$refs.files.files;
      }

    }//End  Methods
  
});//End Vue Files 

function showGoals(Goals){
  if(Goals.length>0){
$('#GoalsTable').show();
$('#NoGoals').hide();
  }else{
    $('#NoGoals').show();
    $('#GoalsTable').hide();
  }
}

function showTasks(Tasks){


  if(Tasks.length==0){
    $('#NoTasks').show();
    $('#TasksTable').hide();
    return;
  }
  //Convert Date For MileStones or Tasks
  //Convert date from Seconds to Date Format
  curdate = new Date(null);
  for(i=0;i<Tasks.length;i++){
    $("#TasksTable").show();
    $('#NoTasks').hide();
    curdate.setTime(Tasks[i].p_CompletePlannedDate*1000);
    Tasks[i].p_CompletePlannedDate=curdate.toLocaleDateString();
    curdate.setTime(Tasks[i].p_CompletedActualDate*1000);
    Tasks[i].p_CompletedActualDate=curdate.toLocaleDateString();
    totalWeight+=Tasks[i].p_Weight;
    totalCMWeight+=Tasks[i].p_CommulativeWeight;
}

return;
}

function showPurchase(Purchases){
  if(Purchases.length==0){
  $('#NoProcs').show();
  $('#ProcsTable').hide();
    return;
  }else{
    $('#NoProcs').hide();
    $('#ProcsTable').show();
  }
  }
  
  function showOutcomes(Outcomes){
    if(Outcomes.length==0){
      $('#NoOutcomes').show();
      $('#OutcomesTable').hide();
      return;
    }
    //Convert Date For Outcomes
    //Convert date from Seconds to Date Format
    str = new Date(null);
    end = new Date(null);
    for(i=0;i<Outcomes.length;i++){
      $("#OutcomesTable").show();
      $('#NoOutcomes').hide();
      str.setTime(Outcomes[i].p_StartDate*1000);
      end.setTime(Outcomes[i].p_EndDate*1000);
      Outcomes[i].duration=(end-str)/(1000 * 3600 * 24);
      Outcomes[i].p_StartDate=str.toLocaleDateString();
      Outcomes[i].p_EndDate=end.toLocaleDateString();
  }
  return;
  }
function showRisks(Risks){
  ProjRisks=Risks;
    if(Risks.length==0){
    $('#NoRisks').show();
    $('#RisksTable').hide();
    return;
  }
  //Convert Date For Risks
  //Convert date from Seconds to Date Format
  curdate = new Date(null);
  for(i=0;i<Risks.length;i++){
    $("#RisksTable").show();
    $('#NoRisks').hide();
    curdate.setTime(Risks[i].p_ExpectedDeadline*1000);
    Risks[i].p_ExpectedDeadline=curdate.toLocaleDateString();
}
return;
}
function showFiles(Files){
  if(Files.length>0){
    $("#FilesTable").show();
    $('#NoFiles').hide();
      }else{
        $('#NoFiles').show();
        $('#FilesTable').hide();
      }
    }

function ConvertDatetoJSON(Impacts){
  for(i=0;i<Impacts.length;i++){
    if(Impacts[i].p_ImpactDetails){
return;
    }
  }
}

// Process the requests returned from backend
function ProcessRequest(Requests){
  //Show Table of request if there is available Requests
  //Hide Message
  //Convert date from Seconds to Date Format
  curdate = new Date(null);
  for(i=0;i<Requests.length;i++){
    document.getElementById('NoReqts').style.display="none";
    document.getElementById('RequestsTable').style.display="block";
    curdate.setTime(Requests[i].p_CreationDate*1000);
    Requests[i].p_CreationDateConverted=curdate.toLocaleDateString();
}
return;
}

function GetImpact(){
 // Get All selected Impact
// then Get Associated Details
 $.each($("input[name='p_ImpactCategory']:checked"), function(){
  Impacts.push($(this).val());
  if($(this).val()=="cost"){
    Details.push($('#AssocCost').val());
  }else if($(this).val()=="quality"){
    Details.push($('#qualityDetails').val());  
  }
  else if($(this).val()=="scope"){
    Details.push($('#scopeDetails').val()); 
  }
  else if($(this).val()=="time"){
    // Get Start and End Date and push to details
    StartDate=$('#CStartDate').val();
    EndDate=$('#CEndDate').val();
    text = StartDate+","+EndDate;
    Details.push(text);
  }
});
 return;
}
