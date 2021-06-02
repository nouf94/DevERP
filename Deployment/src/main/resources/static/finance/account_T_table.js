

 var app = new Vue({
      el: '#body',
      data:{
    	  account: [],
      },
      mounted () {
		  let vm = this;
    	  axios.post('/rest/saveBudgetYear', {
    	    		  p_Year: "2021"
    	            }).then(axios
    	          	      .get('/rest/readTAccount')
    	        	      .then(function (response) {
								vm.account = response.data;
								console.log(this.account);
								for(var i = 0; i < (vm.account).length; i++){
									var messageDate = vm.account[i].p_Date;
									var slicedDate = messageDate.split("T");
									vm.account[i].p_Date = slicedDate[0];
								}
						  }))
	      
		  }
})