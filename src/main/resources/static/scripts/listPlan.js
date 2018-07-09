/**
 * 
 */

var urlPage = location.pathname;
var urlResource = BASE_PATH + "/api/produccion/plan";
var urlForm = BASE_PATH + "/modulos/jefeproduccion/plan/form";
var list = null;
var listData = null;

document.addEventListener("DOMContentLoaded", function() {
    list = new Vue({
        el: "#list",
        data: {
            plans: listData
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
                console.log("valor antes de carga", that.plans);
                that.plans = response.content;
                console.log("valor despues de carga", that.plans);
            }
        }
    });
});

/*var entity = plan;
var parameters = {
	"entity" : entity,
	"action" : ""
};
$(document).ready(function($){	});*/

function updateLista(data){
	$("table tbody").empty();
	$(data.content).each(function(i, item){
		$("table tbody").append(
			"<tr>" +
			"<td data-field='codigo'><span>" + item.id + "</span></td>" +
			"<td><span>" + item.codigo + "</span></td>" +
			"<td><span>" + item.pedido.codigo + "</span></td>" +
			"<td><span>" + item.fecha + "</span></td>" +
			"<td><span>" + item.estado + "</span></td>" +
			"<td><a href='" + $("#documentView").attr("href") + "?id=" + item.id + "'><span class='icon-pencil'></span><span>actualizar orden</span></a></td>" +
			"<td><a href='" + $("#documentDelete").attr("href") + "/" + item.id + "' data-operation='deleteDocument'><span class='icon-bin'></span><span>eliminar orden</span></a></td>" +
			"</tr>"
		);
		$("#list").trigger("update");
	});
	$("a[data-operation='deleteDocument']").on("click", deleteDocument);
	$(".titl ul li:nth-child(2) a span:nth-child(2)").text(" " + data.content.length.toString() + " registros listados");
	$("table tfoot tr td span").empty().html(data.content.length.toString() + " registros listados...");
}

function deleteDocument(event){
	
	event.preventDefault();
	
	var check = confirm("Esta seguro de eliminar el plan?");
	
	if(check == true){
	
		var element = $(this);
		var container = element.parent().parent();
		var action = element.attr("href");
		var row = parseInt(action.split("/")[6]);
		console.log(action);
		console.log(action.split("/")[6]);
		console.log(row);
		
		getData(action, null).done(function(data){
			
			console.log("termino de eliminar el objeto");
			console.log(row);
			$("#list tbody tr[data-code='" + row + "']").remove();
			
		});
		
	}
	
}