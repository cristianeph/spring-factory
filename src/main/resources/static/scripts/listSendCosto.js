/**
 * 
 */

var entity = periodo;

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
			"<tr data-code='" + item.id + "'>" +
			"<td data-field='codigo'><span>" + item.id + "</span></td>" +
			"<td><span>" + item.ano + " - " + item.mes + "</span></td>" +
			"<td><span>" + item.estado + "</span></td>" +
			"<td><a href='" + $("#documentView").attr("href") + "?id=" + item.id + "'><span class='icon-pencil'></span><span> ver periodo</span></a></td>" +
			"</tr>"
		);
		
		$("#list").trigger("update");
		
	});

	$(".titl ul li:nth-child(2) a span:nth-child(2)").text(" " + data.content.length.toString() + " registros listados");
	$("table tfoot tr td span").empty().html(data.content.length.toString() + " registros listados...");
	
}