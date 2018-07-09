/**
 * 
 */

var urlPage = location.pathname;
var urlResource = BASE_PATH + "/api/produccion/solicitudformula";
var urlForm = BASE_PATH + "/modulos/quimico/solicitudformula/form";
var list = null;
var listData = null;

document.addEventListener("DOMContentLoaded", function(){
	list = new Vue({
		el: "#list",
		data: {
            requests: listData
		},
		methods: {
			getEditionForm: function(input){
				return urlForm + "?id=" + input.id;
			}
		},
		beforeMount: function(){
			var that = this;
			var url = urlResource + "?page=1&size=15"
			getData(url).onload = function(){
				var response = JSON.parse(this.responseText);
				console.log("valor antes de carga", that.requests);
				that.requests = response.content;
				console.log("valor despues de carga", that.requests);
			}
		}
	});
});