/**
 * 
 */
var urlPage = location.pathname;
var Machine = null;
var urlResource = "/api/production/maquina";

document.addEventListener("DOMContentLoaded", function () {
	
	Machine = new Vue({
		el: "#machine",
		data: {
			id: 0,
			descripcion: "",
			costo: ""
		},
		methods: {
			setData: function(data) {
				this.id = data.id;
				this.descripcion = data.descripcion;
				this.costo = data.costo;
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
					Machine.setData(response);
				};
			}
		}
	});
	
	document.querySelector("[data-operation='grabarDocumento']").onclick = function(event){
		event.preventDefault();
		var object = Machine.getData();
		var url = urlResource;
		postData(url, object).onload = function() {
			var response = JSON.parse(this.responseText);
            if (handleHttpStatus(response) == true) {
            		console.log(response);
            		Machine.setData(response);
                alert("El registro ha sido grabado correctamente");
            }
		}
	}
	
});