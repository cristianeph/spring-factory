/**
 * 
 */
var urlPage = location.pathname;
var Testformula = null;
var urlResource = BASE_PATH + "/api/production/pruebaformula";

document.addEventListener("DOMContentLoaded", function () {
	
	Testformula = new Vue({
		el: "#test",
		data: {
			id: 0,
			codigo: "",
			estado: "",
            observaciones: "",
            sugerencias: "",
            merma: ""
		},
		methods: {
			setData: function(data) {
				this.id = data.id;
				this.codigo = data.codigo;
				this.estado = data.estado;
                this.observaciones = data.observaciones;
                this.sugerencias = data.sugerencias;
                this.merma = data.merma;
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
					Testformula.setData(response);
				};
			}
		}
	});
	
	document.querySelector("[data-operation='grabarDocumento']").onclick = function(event){
		event.preventDefault();
		var object = Testformula.getData();
		var url = urlResource;
		postData(url, object).onload = function() {
			var response = JSON.parse(this.responseText);
            if (handleHttpStatus(response) == true) {
            		console.log(response);
            		Testformula.setData(response);
                alert("El registro ha sido grabado correctamente");
            }
		}
	}
	
});