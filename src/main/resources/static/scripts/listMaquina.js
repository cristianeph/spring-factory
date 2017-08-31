/**
 * 
 */

var urlPage = location.pathname;
var urlResource = "/api/production/maquina";
var urlForm = "/production/maquina/form";
var list = null;
var listData = null;

document.addEventListener("DOMContentLoaded", function(){
	list = new Vue({
		el: "#list",
		data: {
			machines: listData
		},
		methods: {
			getEditionForm: function(input){
				return urlForm + "?id=" + input.id;
			}
		},
		beforeMount: function(){
			let that = this;
			let url = urlResource + "?page=1&size=15"
			getData(url).onload = function(){
				var response = JSON.parse(this.responseText);
				console.log("valor antes de carga", that.machines);
				that.machines = response.content;
				console.log("valor despues de carga", that.machines);
			}
		}
	});
});