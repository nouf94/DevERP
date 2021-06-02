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


	             
	         ' </ul>'+
	       ' </div>'+
	     ' </div>'+
	   ' </nav>'+
	 
	         ' </div>'+
	           
		  '</div>'});



 var app = new Vue({
      el: '#body',
      data:{
    	  account: [],
    	  fundsAmount: '',
    	  reduceAmount: '',
    	  transferAmount: '',
    	  transferFrom: '',
    	  transferDate: '',
    	  transferDescription: '',
    	  allAccounts: [],
    	  accountTable: [],
      },
      mounted () {
			let vm = this;
    	  	axios.get('/rest/readSavedAccount')
    	        	      .then(response => (
								  this.account = response.data,
								  console.log(this.account)
								  )),
		  	axios.get('/rest/readTAccount')
		    	        .then(function (response) {
								vm.accountTable = response.data;
		    	        	    console.log(this.accountTable);
								for(var i = 0; i < (vm.accountTable).length; i++){
									var messageDate = vm.accountTable[i].p_Date;
									var slicedDate = messageDate.split("T");
									vm.accountTable[i].p_Date = slicedDate[0];
								}
						}),
		  	axios.get('/rest/readChartAccounts')
		  				.then(response => (
		  						this.allAccounts = response.data,
		  						console.log(this.allAccounts)
		  					))
	      
		  },
	methods: {
		submitFunds: function(val){
			axios.post('/rest/postFinanceMessage', {
				p_Content: ' ',
				p_Type:  1,
				p_Amount: this.fundsAmount,
				p_Account: this.account[0].p_Code,
				p_Description: 'Funds',
			})
		},
		submitReduce: function(val){
			axios.post('/rest/postFinanceMessage', {
				p_Content: ' ',
				p_Type:  2,
				p_Amount: this.reduceAmount,
				p_Account: this.account[0].p_Code,
				p_Description: 'Reduce',
			})
		},
		Taccount: function(val){
			axios.post('/rest/saveTaccount', {
				p_Code: this.account[0].p_Code
			}).then(
			window.location.href = "./account_T_table.html"
		)
		},
		submitTransfer: function(val){
			axios.post('/rest/postFinanceMessage',{
				p_Content: this.account[0].p_Code,
				p_Type: 3,
				p_Amount: this.transferAmount,
				p_Account: this.transferFrom,
				p_Description: this.transferDescription
			})
		}
	}
		  
})

