Vue.component('mynavbar', {
	template:' <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">'+
	'<div class="container">'+
	  '<a class="navbar-brand" href="/home.html">نظام إدارة الموارد</a>'+
	  '<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">'+
		'<span class="navbar-toggler-icon"></span>'+
	  '</button>'+
	  '<div class="collapse navbar-collapse" id="navbarResponsive">'+
		'<ul class="navbar-nav ml-auto" style="width: 100%"> <!-- style="width: 100%" -->'+
					  '<li class="nav-item">'+
  
			
  
			   
  
  
			'<a class="nav-link" href="/home.html">'+
  
			 ' الرئيسية'+
			  '<span class="sr-only">(current)</span>'+
			'</a>'+
		  '<li class="nav-item">'+
			'<a class="nav-link" href="/finance/home.html"> النظام المالي</a>'+
		  '</li>'+
		   '<li class="nav-item">'+
			'<a class="nav-link" href="/hrs/home.html">نظام الموارد البشرية</a>'+
		  '</li>'+
  
		  '<li class="nav-item">'+
			'<a class="nav-link" href="/project/home.html">إدارة المشاريع</a>'+
		  '</li>'+
			
			'<li style="margin-right: 30%" class="nave-item"><a class="nav-link" href="/finance/messages.html"> الرسائل   </a></li>'+
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

  Vue.component('myheader',{
	  template: '<div id="header">'+

	  '<nav class="navbar navbar-expand-lg navbar-light bg-light static-top">'+

	      '<div class="container">'+
	       ' <a class="navbar-brand" href="/finance/home.html"> النظام المالي</a>'+
	       ' <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#home-navbarResponsive" aria-controls="home-navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">'+
	     ' <span class="navbar-toggler-icon"></span>'+
	    '</button>'+
	        '<div class="collapse navbar-collapse" id="home-navbarResponsive">'+
	        '  <ul class="navbar-nav ml-auto">'+
	               	
	          '    <li class="nav-item">'+
	            '  <a class="nav-link" href="./budget_home.html">الميزانية</a>'+
	          '  </li>'+
	             ' <li class="nav-item">'+
	            '  <a class="nav-link" href="./expenses_home.html">التقرير المالي</a>'+
	            '</li>'+
	             '  <li class="nav-item">'+
	            '  <a class="nav-link" href="./program_home.html">البرامج</a>'+
	           ' </li>'+

	            '<li class="nav-item">'+
	             ' <a class="nav-link" href="./journal_home.html">اليوميات</a>'+
	           ' </li>'+
	           
	           '<li class="nav-item">'+
	             ' <a class="nav-link" href="./account_home.html">الحسابات</a>'+
	           ' </li>'+
	           
	           '<li class="nav-item">'+
	             ' <a class="nav-link" href="./pay_review.html">الرواتب</a>'+
	           ' </li>'+


	             
	         ' </ul>'+
	       ' </div>'+
	     ' </div>'+
	   ' </nav>'+
	 
	         ' </div>'+
	           
		  '</div>'});






// vue ==========================================
    var app = new Vue({
      el: '#body',
      data: {
    	  message:'',
		  date: ''
      },
      mounted () {
		  let vm = this;
    	  axios
	      .get('/rest/sendFinanceMessage')
	      .then(function (response) {
					vm.message = response.data;
					var messageDate = vm.message.p_Date;
					var slicedDate = messageDate.split("T");
					vm.date = slicedDate[0];
		  		}
			)
    	  },
    	methods:{
    		submitPay: function(val){
    			if(this.message.p_Type == 'ADDITION'){
    				axios.post('/rest/addFunds',{
    					p_Code: this.message.p_Account,
						p_Amount: this.message.p_Amount
    				}).then(
    						window.location.href = "./accounts.html"
    				)
    			}else{
    				if(this.message.p_Type == 'REDUCTION'){
    					axios.post('/rest/reduceFunds',{
    						p_Code: this.message.p_Account,
    						p_Amount: this.message.p_Amount
    					}).then(
    						window.location.href = "./accounts.html"
    					)
    				}else{
    					axios.post('/rest/accountTransfers',{
    						p_Code: this.message.p_Content,
    						p_CodeTo: this.message.p_Account,
    						p_Amount: this.message.p_Amount,
    						p_Date: Date.parse(this.message.p_Date),
    						p_Description: this.message.p_Description
    					}).then(
    							window.location.href = "./accounts.html"
    								)
    				}
    			}
    		}
    	}
    });
    
    