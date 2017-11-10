/**
 * 
 */
var urlPage = location.pathname;
var Customer = null;
var urlResource = BASE_PATH + "/api/produccion/cliente";

document.addEventListener("DOMContentLoaded", function () {
	
	Customer = new Vue({
		el: "#customer",
		data: {
			id: 0,
			razonsocial: "",
			direccion: "",
			ruc: ""
		},
		methods: {
			setData: function(data) {
				this.id = data.id;
				this.razonsocial = data.razonsocial;
				this.direccion = data.direccion;
				this.ruc = data.ruc;
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
					Customer.setData(response);
				};
			}
		}
	});
	
	document.querySelector("[data-operation='grabarDocumento']").onclick = function(event){
		event.preventDefault();
		var object = Customer.getData();
		var url = urlResource;
		postData(url, object).onload = function() {
			var response = JSON.parse(this.responseText);
            if (handleHttpStatus(response) == true) {
            		console.log(response);
            		Customer.setData(response);
                alert("El registro ha sido grabado correctamente");
            }
		}
	}
	
});