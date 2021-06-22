var Impacts=[];
var Details=[]; 
var Project="";

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
        console.log(response),
        $("#projectName").html(this.ProjectInfo.p_Name),
        $("#ProjectDesc").html(this.ProjectInfo.p_Description)
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
             this.submitImpact(response.data[0]['p_CreationDate'])}
             $("#Alert").show();
             this.readChnages()//To Update List of Requests
            }).catch(error => {
              $("#Error").show()
                console.log(error)
            });
        },//End SubmitValues Method  
        submitImpact: function (creationDate) {
          console.log(creationDate)
            axios.post('/rest/AddChangeRequestImpact', {
              p_Impacts: Impacts,
              p_Details: Details,
              p_CreationDate:creationDate
              }).then(response => {
                console.log(response)
              }).catch(error => {
                  console.log(error)
              });
          },//End submitImpact Method  
          ReadChangeRequestImpact: function (selectedDate) {
            console.log(selectedDate)
            axios.put('/rest/ReadChangeRequestImpact', {
              p_CreationDate:selectedDate
              }).then(response => {
                this.selectedImpacts=response.data;
                //ConvertDatetoJSON(this.selectedImpacts);
                console.log(response.data)

              }).catch(error => {
                  console.log(error)
              });
          },//End read GetImpact Method 
          viewRequest:function(event){
            index=(event.target.parentElement.rowIndex)-1;
            this.selectedRequest=this.Requests[index];
            this.ReadChangeRequestImpact(this.selectedRequest.p_CreationDate)

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
        //console.log(response2),
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
          p_Impact: this.p_GoalEffect,
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
      viewGoal:function(event){
        index=(event.target.parentElement.rowIndex)-1;
        this.selectedGoal=this.Goals[index];
      }  
    }//End  Methods
  
});//End Vue Goal 


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
    selectedTask:''
  },
  mounted: function mounted () {
    this.readMilestone()
    //this.ReadStrategies()

  },
  methods: {
    readMilestone:function (){
      axios.put('/rest/ReadProjectMilestone',{
        p_ProjectCode: this.p_ProjectCode
      }).then(response => (this.Tasks = response.data,
        showTasks(this.Tasks)
        //console.log(response)
          )).catch(error => {
            console.log(error)
        })},
     AddProjectMilestone: function (event) {
        axios.post('/rest/AddProjectMilestone', {
          p_Name: this.p_tName,
          p_CompletePlannedDate: (new Date(this.p_MExpDate).getTime() / 1000) ,
          p_Weight: this.p_Mweight,
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
      }//End Add Risk Method 
      ,
      viewRisk:function(event){
        item=(event.target.parentElement.rowIndex)-1;
        this.selectedRisk=this.Risks[item];
      }    
    }//End  Methods
  
});//End Vue risks 
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
}
return;
}

function showRisks(Risks){
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
    Requests[i].p_CreationDate=curdate.toLocaleDateString();
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
