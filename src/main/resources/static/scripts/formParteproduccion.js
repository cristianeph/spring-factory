/**
 * 
 */
$(document).ready(function($){
	
	$("input[id='tarjeta.fecha']").datetimepicker({
		dateFormat:"dd/mm/yy",
		timeFormat: "HH:mm:ss"
	});
	
	if($.getUrlVar("id") != null){
		
		var parameters = {
			"id" : parseInt($.getUrlVar("id"))
		};
		
		getData($("#documentView").attr("href"), parameters).done(function(data){
			
			console.log(data);
			
			$("input[id='tarjeta.id']").val(data.id);
			
			if(data.tarjetaTrabajos != null){
				
				$(data.tarjetaTrabajos).each(function(i, item){
					
					$(".form.list tbody").append(
						"<tr data-row-code='" + item.id + "'>" +
							"<td data-field='item' ><span>" + item.item + "</span></td>" +
							"<td data-field='plan' data-value='" + item.plan.id + "'>" +
								"<span>" + item.plan.codigo + "</span>" +
							"</td>" +
							"<td data-field='horas'><span>" + item.horas + "</span></td>" +
							"<td data-field='cantidad'><span>" + item.cantidad + "</span></td>" +
							"<td data-field='merma'><span>" + item.merma + "</span></td>" +
							"<td data-field='comentarios'><span>" + item.comentarios + "</span></td>" +
							"<td data-field='incidencias'><span>" + item.incidencias + "</span></td>" +
							"<td><a data-operation='editarDetalle' href='item=" + item.item + "'><span class='icon-pencil'></span><span>editar detalle</span></a></td>" +
							"<td><a data-operation='eliminarDetalle' href='item=" + item.item + "'><span class='icon-bin'></span><span>eliminar detalle</span></a></td>" +
						"</tr>"
					);
					
				})
				
				$("a[data-operation='editarDetalle']").on("click", editDetail);
				$("a[data-operation='eliminarDetalle']").on("click", deleteDetail);
				
			}
			
		});
		
	}
	
	$("a[data-operation='grabarDocumento']").click(function(e){
		
		e.preventDefault();
		
		var parametersDetails = [];
		
		$(".form.list tbody tr").each(function(i, item){
			
			var element = $(item);
			
			var detail = {
				"id" : parseInt(element.attr("data-row-code")),
				"plan" : {
					"id" : element.find("td[data-field='plan']").attr("data-value")
				},
				"item" : element.find("td[data-field='item']").text(),
				"horas" : element.find("td[data-field='horas']").text(),
				"cantidad" : element.find("td[data-field='cantidad']").text(),
				"merma" : element.find("td[data-field='merma']").text(),
				"comentarios" : element.find("td[data-field='comentarios']").text(),
				"incidencias" : element.find("td[data-field='incidencias']").text()
			};
			
			console.log(detail);
			parametersDetails.push(detail);
			
		});

		var ahora = new Date();
		
		var parameters = {
            "rows": 0,
            "resume": 0,
			"id" : parseInt($("input[id='tarjeta.id']").val()),
			"tarjetaTrabajos" : parametersDetails
		};
		
		console.log(parameters);
		
		getData($("#documentSave").attr("href"), parameters).done(function(data){
			
			console.log("grabo/actualizo toda el registro!");
			
			alert("El sistema grabo la Tarjeta de Horario");
			
			if($.getUrlVar("id") == null){
				
				console.log(data.id.toString());
				history.pushState(data.id, null, actual + "?id=" + data.id.toString());
				
			}
			
		});
		
	});
	
	$("a[data-operation='grabarDetalle']").click(function(e){
		
		e.preventDefault();
		
		if($("input[id='tarjetadetalle.item']").val() == ""){
			
			var hoy = new Date();
			
			$(".form.list tbody").append(
				"<tr data-row-code=''>" +
					"<td data-field='item'><span>" + ($(".form.list tbody tr").length + 1) + "</span></td>" +
					"<td data-field='plan' data-value='" + $("input[id='tarjetadetalle.plan']").attr("data-code") + "'>" +
						"<span>" + $("input[id='tarjetadetalle.plan']").val() + "</span>" +
					"</td>" +
					"<td data-field='horas'><span>" + $("input[id='tarjetadetalle.horas']").val() + "</span></td>" +
					"<td data-field='cantidad'><span>" + $("input[id='tarjetadetalle.cantidad']").val() + "</span></td>" +
					"<td data-field='merma'><span>" + $("input[id='tarjetadetalle.merma']").val() + "</span></td>" +
					"<td data-field='comentarios'><span>" + $("input[id='tarjetadetalle.comentarios']").val() + "</span></td>" +
					"<td data-field='incidencias'><span>" + $("input[id='tarjetadetalle.incidencias']").val() + "</span></td>" +
					"<td><a data-operation='editarDetalle' href='item=" + $("input[id='tarjetadetalle.item']").val() + "'><span class='icon-pencil'></span><span>editar detalle</span></a></td>" +
					"<td><a data-operation='eliminarDetalle' href='item=" + $("input[id='tarjetadetalle.item']").val() + "'><span class='icon-bin'></span><span>eliminar detalle</span></a></td>" +
				"</tr>"
			);
			
			$("a[data-operation='editarDetalle']").on("click", editDetail);
			$("a[data-operation='eliminarDetalle']").on("click", deleteDetail);
			
		}else{
			
			console.log($(".form.list tbody tr[data-row-code='" + $("input[id='tarjetadetalle.id']").val() + "']"));
			
			var container = $(".form.list tbody tr[data-row-code='" + $("input[id='tarjetadetalle.id']").val() + "']");
			
			container.attr("data-row-code", $("input[id='tarjetadetalle.id']").val());
			
			container.find("[data-field='item']").html("<span>" + $("input[id='tarjetadetalle.item']").val() + "</span>");
			
			container.find("[data-field='plan']").html("<span>" + $("input[id='tarjetadetalle.plan']").val() + "</span>");
			container.find("[data-field='plan']").attr("data-value", $("input[id='tarjetadetalle.plan']").attr("data-code"));
			
			container.find("[data-field='horas']").html("<span>" + $("input[id='tarjetadetalle.horas']").val() + "</span>");
			container.find("[data-field='cantidad']").html("<span>" + $("input[id='tarjetadetalle.cantidad']").val() + "</span>");
			container.find("[data-field='merma']").html("<span>" + $("input[id='tarjetadetalle.merma']").val() + "</span>");
			container.find("[data-field='comentarios']").html("<span>" + $("input[id='tarjetadetalle.comentarios']").val() + "</span>");
			container.find("[data-field='incidencias']").html("<span>" + $("input[id='tarjetadetalle.incidencias']").val() + "</span>");
			
		}
		
		$("input[id='tarjetadetalle.id']").val("")
		$("input[id='tarjetadetalle.item']").val("")
		
		$("input[id='tarjetadetalle.plan']").val("")
		$("input[id='tarjetadetalle.plan']").attr("data-code","")
		
		$("input[id='tarjetadetalle.horas']").val("")
		$("input[id='tarjetadetalle.cantidad']").val("")
		$("input[id='tarjetadetalle.merma']").val("")
		$("input[id='tarjetadetalle.comentarios']").val("")
		$("input[id='tarjetadetalle.incidencias']").val("")
		
	});
	
});

function editDetail(event){
	
	event.preventDefault();
	
	var element = $(this);
	var container = element.parent().parent();

	console.log(element.attr("href"));
	
	$("input[id='tarjetadetalle.id']").val(container.attr("data-row-code"));
	
	$("input[id='tarjetadetalle.item']").val(container.find("td[data-field='item']").text());
	$("input[id='tarjetadetalle.horas']").val(container.find("td[data-field='horas']").text());
	$("input[id='tarjetadetalle.cantidad']").val(container.find("td[data-field='cantidad']").text());
	$("input[id='tarjetadetalle.merma']").val(container.find("td[data-field='merma']").text());
	$("input[id='tarjetadetalle.comentarios']").val(container.find("td[data-field='comentarios']").text());
	$("input[id='tarjetadetalle.incidencias']").val(container.find("td[data-field='incidencias']").text());
	
	$("input[id='tarjetadetalle.plan']").val(container.find("td[data-field='plan']").text());
	$("input[id='tarjetadetalle.plan']").attr("data-code", container.find("td[data-field='plan']").attr("data-value"));
	
}

function deleteDetail(event){
	
	event.preventDefault();
	
	var condicion = confirm("esta seguro de eliminar el detalle?");
	
	if(condicion == true){
		
		var element = $(this);
		var container = element.parent().parent();

		console.log(element.attr("href"));
		
		var item = (parseInt(element.attr("href").split("=")[1]) - 1)
		
		$(".form.list tbody tr").eq(item).remove();
		
		$(".form.list tbody tr").each(function(i, item){
			
			$(item).find("[data-field='item']").html("<span>" + (i+1) + "</span>");
			$(item).find("[data-operation='editarDetalle']").attr("href", "item=" + (i+1));
			$(item).find("[data-operation='eliminarDetalle']").attr("href", "item=" + (i+1));
			
		});
		
	}
	
}

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