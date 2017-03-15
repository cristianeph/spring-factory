/**
 * 
 */
$(document).ready(function($){
	
	$("input[id='pedido.fecha']").datetimepicker({
		dateFormat:"dd/mm/yy",
		timeFormat: "HH:mm:ss"
	});
	
	if($.getUrlVar("id") != null){
		
		var parameters = {
			"id" : parseInt($.getUrlVar("id"))
		};
		
		getData($("#documentView").attr("href"), parameters).done(function(data){
			
			console.log(data);
			
			$("input[id='pedido.id']").val(data.id);
			$("input[id='pedido.codigo']").val(data.codigo);
			$("input[id='pedido.cantidad']").val(data.cantidad);
			$("input[id='pedido.fecha']").val(data.fecha);
			
			$("input[id='pedido.producto']").val(data.producto.descripcion);
			$("input[id='pedido.producto']").attr("data-code", data.producto.id);
			
			$("input[id='pedido.cliente']").val(data.cliente.razonsocial);
			$("input[id='pedido.cliente']").attr("data-code", data.cliente.id);
			
		});
		
	}
	
	$("a[data-operation='grabarDocumento']").click(function(e){
		
		e.preventDefault();

		var ahora = new Date();
		
		var parameters = {
            "rows": 0,
            "resume": 0,
			"id" : parseInt($("input[id='pedido.id']").val()),
			"codigo" : $("input[id='pedido.codigo']").val(),
			"cantidad" : $("input[id='pedido.cantidad']").val(),
			"fecha" : $("input[id='pedido.fecha']").val(),
			"producto" : {
				"id" : $("input[id='pedido.producto']").attr("data-code")
			},
			"cliente" : {
				"id" : $("input[id='pedido.cliente']").attr("data-code")
			}
		};
		
		console.log(parameters);
		
		getData($("#documentSave").attr("href"), parameters).done(function(data){
			
			console.log("grabo/actualizo toda el registro!");
			
			alert("El sistema grabo el Pedido");
			
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
		description = (description != null) ? description : item.razonsocial;
		
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