/**
 * 
 */
var urlPage = location.pathname;
var Job = null;
var urlResource = BASE_PATH + "/api/production/trabajo";
var urlParametersResource = BASE_PATH + "/api/production/trabajo/parameters";
var machineList = null;
var activityList = null;

document.addEventListener("DOMContentLoaded", function () {
	
	Job = new Vue({
		el: "#job",
		data: {
			id: 0,
			item: 0,
			horas: 0,
			cantidad: 0,
			comentarios: "",
			incidencias: "",
            merma: {
			    selected: "",
                options: machineList
            },
            parte: {
                selected: "",
                options: activityList
            },
            prueba: {
                selected: "",
                options: activityList
            }
		},
		methods: {
			setData: function(data) {
				this.id = data.id;
				this.item = data.item;
				this.horas = data.horas;
                this.cantidad = data.cantidad;
                this.comentarios = data.comentarios;
                this.incidencias = data.incidencias;
                this.merma.selected = data.merma.id;
                this.parte.selected = data.parte.id;
                this.prueba.selected = data.prueba.id;
			},
			getData: function() {
			    var object = JSON.parse(JSON.stringify(this.$data));
                object.merma = {"id" : document.querySelector("select[name='merma']").value};
                object.parte = {"id" : document.querySelector("select[name='parte']").value};
                object.prueba = {"id" : document.querySelector("select[name='prueba']").value};
				return object;
			}
		},
		beforeMount: function() {
			getData(urlParametersResource).onload = function() {
			    var response = JSON.parse(this.responseText);
			    Job.merma.options = response.merma.content;
			    Job.merma.selected = "";
                Job.parte.options = response.parte.content;
                Job.parte.selected = "";
                Job.prueba.options = response.prueba.content;
                Job.prueba.selected = "";

                if(getUrlValue().id != undefined){
                    var url = urlResource + "/" + getUrlValue().id;
                    getData(url).onload = function(data){
                        var response = JSON.parse(this.responseText);
                        Job.setData(response);
                    };
                }
            }
		}
	});
	
	document.querySelector("[data-operation='grabarDocumento']").onclick = function(event){
		event.preventDefault();
		var object = Job.getData();
		var url = urlResource;
		postData(url, object).onload = function() {
			var response = JSON.parse(this.responseText);
            if (handleHttpStatus(response) == true) {
                console.log(response);
                Job.setData(response);
                alert("El registro ha sido grabado correctamente");
            }
		}
	}
	
});