/**
 * 
 */

var urlPage = location.pathname;
var urlResource = "/api/production/insumo";
var list = null;
var listData = null;

document.addEventListener("DOMContentLoaded", function(){
	list = new Vue({
		el: "#list",
		data: {
			inputs: listData
		},
		beforeMount: function(){
			let that = this;
			let url = urlResource + "?page=1&size=15"
			getData(url).onload = function(){
				var response = JSON.parse(this.responseText);
				console.log("valor original", that.inputs);
				that.inputs = response.content;
				console.log("valor despues de carga", that.inputs);
			}
		}
	});
});