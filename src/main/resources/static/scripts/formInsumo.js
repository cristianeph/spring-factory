/**
 * 
 */
var urlPage = location.pathname;
var Input = null;
var urlResource = "/api/production/insumo";

document.addEventListener("DOMContentLoaded", function () {
	
	Input = new Vue({
		el: "#input",
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
				let url = urlResource + "/" + getUrlValue().id; 
				getData(url).onload = function(data){
					var response = JSON.parse(this.responseText);
					Input.setData(response);
				};
			}
		}
	});
	
	document.querySelector("[data-operation='grabarDocumento']").onclick = function(event){
		event.preventDefault();
		let object = Input.getData();
		let url = urlResource;
		postData(url, object).onload = function() {
			var response = JSON.parse(this.responseText);
            if (handleHttpStatus(response) == true) {
            		console.log(response);
            		Input.setData(response);
                alert("El registro ha sido grabado correctamente");
            }
		}
	}
	
});