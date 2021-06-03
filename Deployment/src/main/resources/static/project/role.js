var ap = new Vue({
    el: '#body',
    data: {
      allEmployees:[],
      role: null,
      id: null
    },
    mounted () {
        axios.all([
            axios.get('/rest/role'),
            axios.get('/rest/id')
            ])
            .then(axios.spread((roleResponse, idResponse) => {
                this.role = roleResponse.data
                if(this.role === "Managers")
                  $("#base").attr("src", "../base.js");
                if(this.role === "Employees")
                  $("#base").attr("src", "../base_employee_role.js");
                      this.id = idResponse.data
                      })
                    )
        },
    methods: {
      processForm: function(event) {
          targetId = event.currentTarget.id;
        console.log({ value: targetId});
      },
    seeID: function(event) {
        targetId = event.currentTarget.id;
        console.log( {targetId: targetId} );
        axios.post('/rest/saveID', {
            p_EmployeeID: targetId
          }).then(response => {
              window.location.href = '/hrs/staff_detail.html';
          })
      }
    }
  });


