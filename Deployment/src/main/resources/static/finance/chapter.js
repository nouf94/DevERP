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
'<div class="container-fluid padding"> '+
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
'</div>'
  });
 






// vue ==========================================
    var app = new Vue({
      el: '#body',
      data: {
    	allChapters:null
      },
      mounted () {
    	  axios
	      .get('readChapters')
	      .then(response => (this.allChapters = response.data))
    	  },
    	 methods: {
    	      getChapter: function(event) {
    	      	targetId = event.currentTarget.id;
    	    	  console.log( {targetId: targetId} );
    	    	  axios.post('getChapterInfo', {
    	    		  code: targetId
    	            }).then(response => {
    	                window.location.href = 'section';
    	            })
    	        }
    	      }
    });
    
    