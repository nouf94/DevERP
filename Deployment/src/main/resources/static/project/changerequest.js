Vue.component('mynavbar', {
  template:' <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">'+
  '<div class="container">'+
    '<a class="navbar-brand" href="/Deployment/home/ar/">نظام إدارة الموارد</a>'+
    '<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">'+
      '<span class="navbar-toggler-icon"></span>'+
    '</button>'+
    '<div class="collapse navbar-collapse" id="navbarResponsive">'+
      '<ul class="navbar-nav ml-auto">'+
                    '<li class="nav-item">'+

          

             


          '<a class="nav-link" href="/home.html">'+

           ' الرئيسية'+
            '<span class="sr-only">(current)</span>'+
          '</a>'+
        '<li class="nav-item">'+
          '<a class="nav-link" href="./home.html"> النظام المالي</a>'+
        '</li>'+
         '<li class="nav-item">'+
          '<a class="nav-link" href="/hrs/home.html">نظام الموارد البشرية</a>'+
        '</li>'+
        '<li class="nav-item">'+
        '<a class="nav-link" href="/project/home.html">إدارة المشاريع</a>'+
      '</li>'+
           '<li class="nave-item"><a class="nav-link" href="/logout"> تسجيل خروج   </a></li>'+

                       

         
      '</ul>'+
    '</div>'+
 '</div>'+
'</nav>' });

Vue.component('myfooter', {
  template:' <div> '+
'<div class="container-fluid padding" style="background-color:#0b2047"> '+
      '<div class="row text-center"> '+
          '<div class="col-md-4"> '+
             '<hr class="light"> '+
              '<p>عن المدينة</p> '+

          '</div> '+
          '<div class="col-md-4"> '+
              '<hr class="light"> '+
              '<p>السياسات</p> '+
              '<hr class="light"> '+

          '</div> '+
          '<div class="col-md-4"> '+
              '<hr class="light"> '+
              '<p>الابتكار</p> '+
              '<hr class="light"> '+

          '</div> '+
          '<div class="col-12 footer-copyright"> '+
              '<br> '+
              '<p> مدينة الملك عبدالعزيز للعلوم والتقنية &copy; 2020</p> '+
          '</div> '+

      '</div> '+
  '</div> '+
'</div>'});

var Impacts=[];
var Details=[]; 

//Change Request Instance
var app = new Vue({
    el: '#nav-requests',
    data:{
      p_Title:'',
      p_Description: '',
      p_Justification: '',
      p_ProjectCode: '0',
      p_ImpactCategory: 'Time',
      p_ImpactDetails: 'Card',
     Requests:'',
      errors: {
        name: false,
        email: false
      }
    },
    mounted: function mounted () {
      this.readChnages()

    },
    methods: {
      readChnages:function (){
        axios.get('/rest/ReadAllChangeRequest')
        .then(response2 => (this.Requests = response2.data,
          console.log(response2),
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
            p_ImpactCategory: this.p_ImpactCategory,
            p_ImpactDetails: this.p_ImpactDetails
            }).then(response => {
              //If the Change request has more than one Impact submit the rest Impacts here
             this.submitImpact(response.data[0]['p_CreationDate'])
             $("#Alert").show();
             this.readChnages()
            }).catch(error => {
              $("#Error").show()
                console.log(error)
            });
        },//End SubmitValues Method  
        submitImpact: function (creationDate) {
            axios.post('/rest/AddChangeRequestImpact', {
              p_Impacts: Impacts,
              p_Details: Details,
              p_ChangeRequestCreationDate:creationDate
              }).then(response => {
                console.log(response)
              }).catch(error => {
                  console.log(error)
              });
          },//End submitImpact Method  
          GetImpact: function (event) {
            axios.get('/rest/ReadChangeRequestImpact', {
              }).then(response => {
                console.log(response)
              }).catch(error => {
                  console.log(error)
              });
          }//End read CreationDate Method  
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
    }
  },
  mounted: function mounted () {
    this.readGoals()
    this.ReadStrategies()

  },
  methods: {
    readGoals:function (){
      axios.put('/rest/ReadProjectGoals',{
        p_ProjectCode: this.p_ProjectCode
      }).then(response2 => (this.Goals = response2.data,
        console.log(response2),
        showGoals(this.Goals)
          )).catch(error => {
            console.log(error.response2)
        })},
        ReadStrategies:function (){
          axios.get('/rest/readStrategies')
          .then(response2 => (this.Strategies = response2.data,
            console.log(response2)
              )).catch(error => {
                console.log(error.response)
            })},
    AddGoal: function (event) {
        axios.post('/rest/AddGoal', {
          p_KPI: this.p_GoalEffect,
          p_Description: this.p_gName,
          p_ProjectCode: this.p_ProjectCode,
          }).then(response => {
           console.log(response)
           $("#Alert").show();
          }).catch(error => {
            $("#Error").show();
              console.log(error)
          });
      }//End Add Goal Method   
    }//End  Methods
  
});//End Vue Goal 


//Add Milestone Instance
var MilestoneApp = new Vue({
  el: '#nav-tasks',
  data:{
    p_tName:'',
    p_Mstatus:'',
    p_Mweight:'',
    p_MExpDate:'',
    p_MAcutDate:'',
    Tasks:'',
    errors: {
      name: false,
      email: false
    },
    p_ProjectCode:'Project1Code'
  },
  mounted: function mounted () {
    //this.readMilestone()
    //this.ReadStrategies()

  },
  methods: {
    readMilestone:function (){
      axios.put('/rest/ReadProjectMilestone',{
        p_ProjectCode: this.p_ProjectCode
      }).then(response2 => (this.Tasks = response2.data,
        showTasks(this.Tasks),
        console.log(response),
        showGoals()
          )).catch(error => {
            console.log(error.response2)
        })},
     AddProjectMilestone: function (event) {
        axios.post('/rest/AddProjectMilestone', {
          p_Name: this.p_Name,
          p_CompletePlannedDate: '0',
          p_Weight: this.p_Mweight,
          p_ProjectCode: this.p_ProjectCode
          }).then(response => {
           console.log(response)
           $("#Alert").show();
          }).catch(error => {
            $("#Error").show();
              console.log(error)
          });
      }//End Add Milestone Method   
    }//End  Methods
  
});//End Vue Milestone 

function showGoals(Goals){
  if(Goals.length>0){
$('#GoalsTable').show();
$('#NoGoals').hide();
  }
}

function showTasks(Tasks){
  if(Tasks.length>0){
$('#TasksTable').show();
$('#NoTasks').hide();
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
    Requests[i].p_CreationDate=curdate.toLocaleString();
}
//this.$refs.Requests.refresh();

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
     text = '{ "NewTimeline" : [' +
    '{ "StartDate":"'+StartDate+'" },' +
    '{ "EndDate":"'+EndDate+'" } ]}';
    Details.push(text);
  }
});
 return;
}
