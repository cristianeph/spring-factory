/**
 * 
 */

var entity = tarjeta;

var parameters = {
	"entity" : entity,
	"action" : ""
};

$(document).ready(function($){
	
});

function updateLista(data){
	
	$("table tbody").empty();
	
	$(data.content).each(function(i, item){
		
		var planes = "";
		var list = item.tarjetaTrabajos;
		
		$(item.tarjetaTrabajos).each(function(i, item){
			
			var semicollon = ((list.length-1) == i) ? "" : ", ";
			planes += "<strong>" + item.plan.codigo + "</strong> - " + item.plan.fecha + semicollon;
			
		});

		var horas = 0;
		
		$(item.tarjetaTrabajos).each(function(i, item){
			horas += parseInt(item.horas);
		});
		
		$("table tbody").append(
			"<tr data-code='" + item.id + "'>" +
			"<td data-field='codigo'><span>" + item.id + "</span></td>" +
			"<td><span>" + planes + "</span></td>" +
			"<td><span>" + horas + "</span></td>" +
			"<td><a href='" + $("#documentView").attr("href") + "?id=" + item.id + "'><span class='icon-pencil'></span><span> actualizar tarjeta</span></a></td>" +
			"<td><a href='" + $("#documentDelete").attr("href") + "/" + item.id + "' data-operation='deleteDocument'><span class='icon-bin'></span><span> eliminar tarjeta</span></a></td>" +
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
	
	var check = confirm("Esta seguro de eliminar la tarjeta de horario?");
	
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