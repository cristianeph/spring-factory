/**
 * 
 */
var urlPage = location.pathname;
var Test = null;
var urlResource = BASE_PATH + "/api/production/pruebaproduccion";
var urlParametersResource = BASE_PATH + "/api/production/merma/parameters";
var ordenList = null;

document.addEventListener("DOMContentLoaded", function () {

    Test = new Vue({
		el: "#test",
		data: {
		    master: {
                id: 0,
                descripcion: "",
                observaciones: ""
            },
            relation: {
		        selected: "",
                options: ordenList
            }
		},
		methods: {
			setData: function(data) {
				this.master.id = data.id;
				this.master.descripcion = data.descripcion;
                this.master.observaciones = data.observaciones;
			},
			getData: function() {
				return JSON.parse(JSON.stringify(this.$data.master));
			}
		},
		beforeMount: function() {
			if(getUrlValue().id != undefined){
				var url = urlResource + "/" + getUrlValue().id; 
				getData(url).onload = function(data){
					var response = JSON.parse(this.responseText);
                    Test.setData(response);
				};
			}
		}
	});
	
	document.querySelector("[data-operation='grabarDocumento']").onclick = function(event){
		event.preventDefault();
		var object = Test.getData();
		var url = urlResource;
		postData(url, object).onload = function() {
			var response = JSON.parse(this.responseText);
            if (handleHttpStatus(response) == true) {
				console.log(response);
                Test.setData(response);
                alert("El registro ha sido grabado correctamente");
            }
		}
	}
	
});