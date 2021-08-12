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
      axios.put('/rest/ReadProject',{
        p_Code: this.p_Code
      }).then(response => (this.ProjectInfo = response.data[0],
        $("#projectName").html(this.ProjectInfo.p_Name),
        $("#ProjectDesc").html(this.ProjectInfo.p_Description),
         date1 = new Date(null),
        date1.setTime(this.ProjectInfo.p_StartDate*1000),
        this.ProjectInfo.p_StartDate=date1.toLocaleDateString(),
        $("#PStartDate").val(this.ProjectInfo.p_StartDate),
        date1.setTime(this.ProjectInfo.p_EndDate*1000),
        this.ProjectInfo.p_EndDate=date1.toLocaleDateString(),
        $("#PEndDate").val(this.ProjectInfo.p_EndDate)
        )).catch(error => {
            console.log(error)
        })
      }
    }//End Methods
});//End Vue ProjectInfo

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
          p_Impact: $('#UGEffect :selected').val(),
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
//Add Scope Instance
var ScopeApp = new Vue({
    el: '#nav-scops',
    data:{
      p_ProjectCode:'Project1Code',
      p_DescScope:'',
      p_tscope:'',
      Scopes:'',
      Strategies:'',
      errors: {
        name: false,
        email: false
      },
      selectedScope:''
    },
    mounted: function mounted () {
      this.readScopes()
      //this.ReadStrategies()
  
    },
    methods: {
      readScopes:function (){
        axios.put('/rest/ReadProjectScope',{
          p_ProjectCode: this.p_ProjectCode
        }).then(response2 => (this.Scopes = response2.data,
          showScopes(this.Scopes)
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
      AddScope: function (event) {
          axios.post('/rest/AddGoal', {
            p_Description: this.p_gName,
            p_Impact: $('#GEffect :selected').val(),
            p_ProjectCode: this.p_ProjectCode,
            p_KPI: this.p_GoalEffect,
            }).then(response => {
             //console.log(response)
             $("#Alert").show();
             this.readScopes()//To Update List of Scopes
            }).catch(error => {
              $("#Error").show();
                console.log(error)
            });
        },//End Add Scope Method  
        viewScopeUpdate: function (event){
                  //Get The selected element to update 
                  this.selectedScope="";
                  index=(event.target.parentElement.parentElement.parentElement.parentElement.rowIndex)-1;
                  this.selectedScope=this.Scopes[index];
                  this.p_gName=this.selectedScope.p_Description;
                  $('#UGEffect').val(this.selectedScope.p_Impact);
        }, 
        UpdateScope: function (event) {
            axios.post('/rest/UpdateGoal', {
            p_OldDescription:this.selectedGoal.p_Description,
            p_Description: this.p_gName,
            p_Impact: $('#UGEffect :selected').val(),
            p_ProjectCode: this.p_ProjectCode,
            p_KPI: this.p_GoalEffect,
            }).then(response => {
             $("#Update").show();
             this.readScopes()//To Update List of Scope
            }).catch(error => {
              $("#Error").show();
                console.log(error)
            });
        },//End Update Scope Method  
        viewScope:function(event){
          this.selectedScope="";
          index=(event.target.parentElement.rowIndex)-1;
          this.selectedScope=this.Scopes[index];
          $('#VGEffect').val(this.selectedScope.p_Impact);
  
        }  
      }//End  Methods
    
  });//End Vue Scope 
//Add Risk Instance
var RiskApp = new Vue({
  el: '#nav-risks',
  data:{
    p_rName:'',
    Rtype:'',
    Reffect:'',
    p_rOwner:'',
    Risks:'',
    p_Response:'',
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
          p_IsOpen: '',
          p_MitigationPlan:this.p_Response,
          p_ExpectedDeadline:'',  
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
        this.selectedRisk=this.Risks[index];
        //Fill the View Form
        this.p_rName=this.selectedRisk.p_Title;
        this.p_Response=this.selectedRisk.p_MitigationPlan;
        $('#UReffect').val(this.selectedRisk.p_Severity);
        $('#UrProb').val(this.selectedRisk.p_Probability);
        $('#URtype').val(this.selectedRisk.p_IsIssue);

}, 
UpdateRisk: function (event) {
  axios.post('/rest/UpdateRisk', {
    p_OldTitle: this.selectedRisk.p_Title,
    p_ProjectCode :this.p_ProjectCode,
    p_Title: this.p_rName,
    p_Severity: $('#Reffect :selected').val(), 
    p_Probability : $('#rProb :selected').val(),
    p_IsIssue: $('#Rtype :selected').val(),
    p_MitigationPlan:this.p_Response,
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
        console.log(item)
        this.selectedRisk=this.Risks[item];
      }    
    }//End  Methods
  
});//End Vue risks



//Add Milestone Instance
var MilestoneApp = new Vue({
  el: '#nav-tasks',
  data:{
    p_tName:'',
    p_MDuration:'',
    Tasks:'',
    errors: {
      name: false,
      email: false
    },
    p_ProjectCode:'Project1Code',
    selectedTask:'',
    
  },
  mounted: function mounted () {
    this.readMilestone()
  },
  methods: {
    readMilestone:function (){
      axios.put('/rest/ReadProjectMilestone',{
        p_ProjectCode: this.p_ProjectCode
      }).then(response => (this.Tasks = response.data,
        showTasks(this.Tasks)
          )).catch(error => {
            console.log(error)
        })},
     AddProjectMilestone: function (event) {
        axios.post('/rest/AddProjectMilestone', {
          p_Name: this.p_tName,
          //p_Weight: this.p_Mweight, //this for Duration
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




function showGoals(Goals){
  if(Goals.length>0){
$('#GoalsTable').show();
$('#NoGoals').hide();
  }else{
    $('#NoGoals').show();
    $('#GoalsTable').hide();
  }
}


function showScopes(Scopes){
    if(Scopes.length>0){
  $('#ScopesTable').show();
  $('#NoScopes').hide();
    }else{
      $('#NoScopes').show();
      $('#ScopesTable').hide();
    }
  }

function showTasks(Tasks){


  if(Tasks.length==0){
    $('#NoTasks').show();
    $('#TasksTable').hide();
    return;
  }else{
    $("#TasksTable").show();
    $('#NoTasks').hide();
  }
}
 
function showRisks(Risks){
  ProjRisks=Risks;
    if(Risks.length==0){
    $('#NoRisks').show();
    $('#RisksTable').hide();
    return;
  }else{
    $("#RisksTable").show();
    $('#NoRisks').hide();
  }
}


function ConvertDatetoJSON(Impacts){
  for(i=0;i<Impacts.length;i++){
    if(Impacts[i].p_ImpactDetails){
return;
    }
  }
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
