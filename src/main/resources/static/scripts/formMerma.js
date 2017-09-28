/**
 * 
 */
var urlPage = location.pathname;
var Loss = null;
var urlResource = "/api/production/merma";
var urlParametersResource = "/api/production/merma/parameters";
var ordenList = null;

document.addEventListener("DOMContentLoaded", function () {

    Loss = new Vue({
		el: "#loss",
		data: {
            id: 0,
            cantidad: "",
            orden: {
                selected: "",
                options: ordenList
            }
		},
		methods: {
			setData: function(data) {
				this.id = data.id;
				this.cantidad = data.cantidad;
			},
			getData: function() {
			    var object = JSON.parse(JSON.stringify(this.$data))
                delete object.orden;
			    var composite = {
			        "merma": object,
                    "trabajo": {"id" : document.querySelector("select[name='orden']").value}
                }
				return composite;
			}
		},
		beforeMount: function() {
            getData(urlParametersResource).onload = function() {
                var response = JSON.parse(this.responseText);
                Loss.orden.options = response.content;
                Loss.orden.selected = "";

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