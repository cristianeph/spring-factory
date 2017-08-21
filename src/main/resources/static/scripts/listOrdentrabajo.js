/**
 * 
 */

var urlPage = location.pathname;
var urlResource = "/api/production/trabajo";
var list = null;
var listData = null;

document.addEventListener("DOMContentLoaded", function(){
	list = new Vue({
		el: "#list",
		data: {
			jobs: listData
		},
		beforeMount: function(){
			let that = this;
			let url = urlResource + "?page=1&size=15"
			getData(url).onload = function(){
				var response = JSON.parse(this.responseText);
				console.log("valor antes de carga", that.inputs);
				that.jobs = response.content;
				console.log("valor despues de carga", that.inputs);
			}
		}
	});
});