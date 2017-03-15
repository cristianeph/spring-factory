/**
 * 
 */

$(document).ready(function($){
	
	if($.getUrlVar("id") != null){
		
		var parameters = {
			"id" : parseInt($.getUrlVar("id"))
		};
		
		getData($("#documentView").attr("href"), parameters).done(function(data){
			
			console.log(data);
			
			$("input[id='producto.id']").val(data.id);
			$("input[id='producto.descripcion']").val(data.descripcion);
			
			$("input[id='producto.formula']").val(data.formula.codigo);
			$("input[id='producto.formula']").attr("data-code", data.formula.id);
			
		});
		
	}
	
	$("a[data-operation='grabarDocumento']").click(function(e){
		
		e.preventDefault();
		
		var parameters = {
			"id" : parseInt($("input[id='producto.id']").val()),
			"descripcion": $("input[id='producto.descripcion']").val(),
			"formula": {
				"id" : parseInt($("input[id='producto.formula']").attr("data-code"))
			}
		};
		
		console.log(parameters);
		
		getData($("#documentSave").attr("href"), parameters).done(function(data){
			
			console.log("grabo/actualizo toda el registro!");
			
			alert("El sistema grabo el Producto");
			
			if($.getUrlVar("id") == null){
				
				console.log(data.id.toString());
				history.pushState(data.id, null, actual + "?id=" + data.id.toString());
				
			}
			
		});
		
	});
	
});

function updateReferences(data){
	
	$(".moda .find table tbody").empty()
	
	$(data.content).each(function(i, item){
		
		var description = (item.codigo != null) ? item.codigo : item.descripcion;
		var value = (item.fecha != null) ? item.fecha : item.id;
		
		$(".moda .find table tbody").append( 
			"<tr data-code='" + item.id + "'>" +
				"<td data-name='" + description + "'><span>" + description + "</span></td>" +
				"<td data-value='" + value + "'><span>" + value + "</span></td>" +
				"<td data-action='" + item.id + "'></td>" +
				"<td data-action='" + item.id + "'><a href='" + item.id + "' data-operation='selectItem'><span>Elegir</span></a></td>" +
			"</tr>"
		);
		
	});
	
	if($(".moda .find table tbody tr").length > 0){
		
		$(".moda .find table tbody tr a[data-operation='selectItem']").on("click", function(event){
			
			event.preventDefault();
			
		});
		
		$(".moda .find table tbody tr").on("dblclick", function(){

			var element = $(this);
			var input = $("input[id='relacion.criterio']").attr("data-input");
			console.log(input);console.log(element.attr("data-code"));
			$("input[id='" + input + "']").val(element.find("td[data-name]").attr("data-name"));
			$("input[id='" + input + "']").attr("data-reference", element.attr("data-code"));
			$("input[id='" + input + "']").attr("data-code", element.attr("data-code"));
			
			$(".moda").hide();
			
		});
		
	}
	
}