/**
 * 
 */
var urlPage = location.pathname;
var Job = null;
var urlResource = "/api/production/trabajo";

document.addEventListener("DOMContentLoaded", function () {
	
	Job = new Vue({
		el: "#job",
		data: {
			id: 0,
			maquina: {
				descripcion: ""
			},
			actividad: {
				descripcion: ""
			},
			plan: {
				codigo: ""
			},
			horas: 0,
			cantidad: 0
		},
		methods: {
			setData: function(data) {
				this.id = data.id;
				this.plan.codigo = data.plan.codigo;
				this.maquina.descripcion = data.maquina.descripcion;
				this.actividad.descripcion = data.actividad.descripcion;
				this.horas = data.horas;
				this.cantidad = data.cantidad;
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
					Job.setData(response);
				};
			}
		}
	});
	
});