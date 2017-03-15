/**
 * 
 */

var entity = plan;

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
			"<td><span>" + item.pedido.codigo + "</span></td>" +
			"<td><span>" + item.fecha + "</span></td>" +
			"<td><span>" + item.estado + "</span></td>" +
			"<td><a href='" + $("#documentView").attr("href") + "?id=" + item.id + "'><span>validar plan</span></a></td>" +
			"</tr>"
		);
		
		$("#list").trigger("update");
		
	});

	$(".titl ul li:nth-child(2) a span:nth-child(2)").text(" " + data.content.length.toString() + " registros listados");
	$("table tfoot tr td span").empty().html(data.content.length.toString() + " registros listados...");
	
}