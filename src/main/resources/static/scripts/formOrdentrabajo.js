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
			merma: 0,
			comentarios: "",
			incidencias: "",
            maquina: {
			    selected: "",
                options: machineList
            },
            actividad: {
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
                this.merma = data.merma;
                this.comentarios = data.comentarios;
                this.incidencias = data.incidencias;
                this.maquina.selected = data.maquina.id;
                this.actividad.selected = data.actividad.id;
			},
			getData: function() {
			    var object = JSON.parse(JSON.stringify(this.$data));
                object.maquina = {"id" : document.querySelector("select[name='maquina']").value};
                object.actividad = {"id" : document.querySelector("select[name='actividad']").value};
				return object;
			}
		},
		beforeMount: function() {
			getData(urlParametersResource).onload = function() {
			    var response = JSON.parse(this.responseText);
			    Job.actividad.options = response.maquinas.content;
			    Job.actividad.selected = "";
                Job.maquina.options = response.actividades.content;
                Job.maquina.selected = "";

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