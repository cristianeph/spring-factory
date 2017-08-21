/**
 * 
 */

var entity = pedido;

var parameters = {
	"entity" : entity,
	"action" : ""
};

$(document).ready(function($){
	
});

function updateLista(data){
	
	$("table tbody").empty();
	
	$(data.content).each(function(i, item){
		
		$("table tbody").append(
			"<tr>" +
				"<td data-field='codigo'><span>" + item.id + "</span></td>" +
				"<td><span>" + item.codigo + "</span></td>" +
				"<td><span>" + item.producto.descripcion + "</span></td>" +
				"<td><span>" + item.cliente.razonsocial + "</span></td>" +
				"<td><span>" + item.cantidad + "</span></td>" +
				"<td><span>" + item.fecha + "</span></td>" +
				"<td><a href='" + $("#documentView").attr("href") + "?id=" + item.id + "'><span class='icon-pencil'></span><span>actualizar pedido</span></a></td>" +
				"<td><a href='" + $("#documentDelete").attr("href") + "/" + item.id + "' data-operation='deleteDocument'><span class='icon-bin'></span><span> eliminar pedido</span></a></td>" +
			"</tr>"
		);
		
		$("#list").trigger("update");
		
	});

	$(".titl ul li:nth-child(2) a span:nth-child(2)").text(" " + data.content.length.toString() + " registros listados");
	$("table tfoot tr td span").empty().html(data.content.length.toString() + " registros listados...");
	
}

function deleteDocument(event){
	
	event.preventDefault();
	
	var check = confirm("Esta seguro de eliminar el pedido?");
	
	if(check == true){
	
		var element = $(this);
		var container = element.parent().parent();
		var action = element.attr("href");
		var row = parseInt(action.split("/")[6]);
		console.log(action);
		console.log(action.split("/")[6]);
		console.log(row);
		
		getData(action).onload = function(){
			var response = JSON.parse(this.responseText);
			console.log("termino de eliminar el objeto");
			console.log(response);
			$("#list tbody tr[data-code='" + response + "']").remove();
			
		};
		
	}
	
}