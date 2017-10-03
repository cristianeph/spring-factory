/**
 * 
 */
var urlPage = location.pathname;
var Sample = null;
var urlResource = BASE_PATH + "/api/production/muestra";

document.addEventListener("DOMContentLoaded", function () {
	
	Sample = new Vue({
		el: "#sample",
		data: {
			id: 0,
			descripcion: "",
            resultado: "",
            viscocidad: "",
            rendimiento: "",
			recipiente: ""
		},
		methods: {
			setData: function(data) {
				this.id = data.id;
				this.descripcion = data.descripcion;
				this.resultado = data.resultado;
				this.viscocidad = data.viscocidad;
				this.rendimiento = data.rendimiento;
				this.recipiente = data.recipiente;
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
					Sample.setData(response);
				};
			}
		}
	});
	
	document.querySelector("[data-operation='grabarDocumento']").onclick = function(event){
		event.preventDefault();
		var object = Sample.getData();
		var url = urlResource;
		postData(url, object).onload = function() {
			var response = JSON.parse(this.responseText);
            if (handleHttpStatus(response) == true) {
				console.log(response);
				Sample.setData(response);
                alert("El registro ha sido grabado correctamente");
            }
		}
	}
	
});