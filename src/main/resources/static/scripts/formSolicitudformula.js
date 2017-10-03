/**
 * 
 */
var urlPage = location.pathname;
var Requestformula = null;
var urlResource = BASE_PATH + "/api/production/solicitudformula";
var urlParametersResource = BASE_PATH + "/api/production/solicitudformula/parameters";
var pruebaList = null;
var muestraList = null;

document.addEventListener("DOMContentLoaded", function () {
	
	Requestformula = new Vue({
		el: "#request",
		data: {
			id: 0,
			fecha: "",
			observacion: "",
			prueba: {
				selected: "",
                options: pruebaList
			},
            muestra: {
                selected: "",
                options: muestraList
            }
		},
		methods: {
			setData: function(data) {
				this.id = data.id;
				this.fecha = data.fecha;
				this.observacion = data.observacion;
				this.prueba.selected = data.prueba.id;
				this.muestra.selected = data.muestra.id;
			},
			getData: function() {
			    var object = JSON.parse(JSON.stringify(this.$data));
                object.prueba = {"id" : document.querySelector("select[name='prueba']").value};
                object.muestra = {"id" : document.querySelector("select[name='muestra']").value};
				return object;
			}
		},
		beforeMount: function() {
            getData(urlParametersResource).onload = function() {
                var response = JSON.parse(this.responseText);
                Requestformula.prueba.options = response.pruebas.content;
                Requestformula.prueba.selected = "";
                Requestformula.muestra.options = response.muestras.content;
                Requestformula.muestra.selected = "";

                if(getUrlValue().id != undefined){
                    var url = urlResource + "/" + getUrlValue().id;
                    getData(url).onload = function(data){
                        var response = JSON.parse(this.responseText);
                        Requestformula.setData(response);
                    };
                }
            };
		}
	});
	
	document.querySelector("[data-operation='grabarDocumento']").onclick = function(event){
		event.preventDefault();
		var object = Requestformula.getData();
		var url = urlResource;
		postData(url, object).onload = function() {
			var response = JSON.parse(this.responseText);
            if (handleHttpStatus(response) == true) {
            		console.log(response);
            		Requestformula.setData(response);
                alert("El registro ha sido grabado correctamente");
            }
		}
	}
	
});