/**
 * 
 */
var urlPage = location.pathname;
var Warehouse = null;
var urlResource = BASE_PATH + "/api/production/merma";
var urlParametersResource = BASE_PATH + "/api/production/merma/parameters";
var kardexList = null;

document.addEventListener("DOMContentLoaded", function () {

    Warehouse = new Vue({
		el: "#warehouse",
		data: {
            id: 0,
            fecha: "",
            tipo: 0,
            kardex: {
                selected: "",
                options: kardexList
            }
		},
		methods: {
			setData: function(data) {
				this.id = data.id;
				this.fecha = data.fecha;
                this.tipo = data.tipo;
			},
			getData: function() {
				return JSON.parse(JSON.stringify(this.$data));
			}
		},
		beforeMount: function() {
            getData(urlParametersResource).onload = function() {
                var response = JSON.parse(this.responseText);
                Loss.kardex.options = response.content;
                Loss.kardex.selected = "";

                if(getUrlValue().id != undefined){
                    var url = urlResource + "/" + getUrlValue().id;
                    getData(url).onload = function(data){
                        var response = JSON.parse(this.responseText);
                        Loss.setData(response);
                    };
                }
            };
		}
	});
	
	document.querySelector("[data-operation='grabarDocumento']").onclick = function(event){
		event.preventDefault();
		var object = Loss.getData();
		console.log(object);
		var url = urlResource;
		postData(url + "/compositesave", object).onload = function() {
			var response = JSON.parse(this.responseText);
            if (handleHttpStatus(response) == true) {
				console.log(response);
                Loss.setData(response);
                alert("El registro ha sido grabado correctamente");
            }
		}
	}
	
});