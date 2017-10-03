/**
 * 
 */
var urlPage = location.pathname;
var User = null;
var urlResource = BASE_PATH + "/api/security/usuario";

document.addEventListener("DOMContentLoaded", function () {

    User = new Vue({
		el: "#loss",
		data: {
            id: 0,
            user: "",
            password: "",
            email: "",
            rol: "",
            active: 0
		},
		methods: {
			setData: function(data) {
				this.id = data.id;
				this.user = data.user;
                this.password = data.password;
                this.email = data.email;
                this.rol = data.rol;
                this.active = data.active;
			},
			getData: function() {
				return JSON.parse(JSON.stringify(this.$data));
			}
		},
		beforeMount: function() {
			if(getUrlValue().id != undefined){
				var url = urlResource + "/" + getUrlValue().id;
				getData(url).onload = function(data){
					var response = JSON.parse(this.responseText);
                    User.setData(response);
				};
			}
		}
	});
	
	document.querySelector("[data-operation='grabarDocumento']").onclick = function(event){
		event.preventDefault();
		var object = User.getData();
		console.log(object);
		var url = urlResource;
		postData(url, object).onload = function() {
			var response = JSON.parse(this.responseText);
            if (handleHttpStatus(response) == true) {
				console.log(response);
                User.setData(response);
                alert("El registro ha sido grabado correctamente");
            }
		}
	}
	
});