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

Impacts=[];
Details=[];    
var app = new Vue({
    el: '#nav-requests',
    data:{
      p_Description: '',
      p_Justification: '',
      p_ProjectCode: '0',
      p_ImpactCategory: Impacts[0],
      p_ImpactDetails: Details[0],
     Requests:'',
      errors: {
        name: false,
        email: false
      }
    },
    mounted: function readChnages(){
      axios.get('/rest/ReadAllChangeRequest')
      .then(response2 => (this.Requests = response2.data,
        ProcessRequest(this.Requests)
          )).catch(error => {
            console.log(error.response)
        })

    },
    methods: {
      submitValues: function (event) {
        GetImpact();
          axios.post('/rest/CreateChangeRequest', {
            p_Description: this.p_Description,
            p_Justification: this.p_Justification,
            p_ProjectCode: this.p_ProjectCode,
            p_ImpactCategory: this.p_ImpactCategory,
            p_ImpactDetails: this.p_ImpactDetails
            }).then(response => {
              readChnages()
            }).catch(error => {
                console.log(error)
            });
        },//End SubmitValues Method  
        submitImpact: function (event) {
          GetImpact();
            axios.post('/rest/SendChangeRequestImpact', {
              p_ImpactCategory: this.p_ImpactCategory,
              p_ImpactDetails: this.p_ImpactDetails,
              p_ChangeRequestCreationDate:this.p_ChangeRequestCreationDate

              }).then(response => {
                console.log(response)
              }).catch(error => {
                  console.log(error)
              });
          }//End SubmitValues Method  
      }//End  Methods
    
});//End Vue Temp



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
    Details.push(document.getElementById('AssocCost').val);
  }else if($(this).val()=="quality"){
    Details.push(document.getElementById('qualityDetails').val);  
  }
  else if($(this).val()=="scope"){
    Details.push(document.getElementById('scopeDetails').val); 
  }
  else if($(this).val()=="time"){
    // Get Start and End Date and push to details
    StartDate=document.getElementById('CStartDate').val;
    EndDate=document.getElementById('CEndDate').val;
     text = '{ "NewTimeline" : [' +
    '{ "StartDate":"'+StartDate+'" },' +
    '{ "EndDate":"'+EndDate+'" } ]}';
    Details.push(text);
  }
});
 return;
}
