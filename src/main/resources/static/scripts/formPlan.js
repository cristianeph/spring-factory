/**
 * 
 */
$(document).ready(function($){
	
	$("input[id='plan.fecha']").datetimepicker({
		dateFormat:"dd/mm/yy",
		timeFormat: "HH:mm:ss",
		changeMonth:true,
		changeYear:true
	});
	
	if($.getUrlVar("id") != null){
		
		var parameters = {
			"id" : parseInt($.getUrlVar("id"))
		};
		
		getData($("#documentView").attr("href"), parameters).done(function(data){
			
			console.log(data);
			
			$("input[id='plan.id']").val(data.id);
			$("input[id='plan.codigo']").val(data.codigo);
			$("input[id='plan.estado']").val(data.estado);
			$("input[id='plan.fecha']").val(data.fecha);
			
			$("input[id='plan.pedido']").val(data.pedido.codigo);
			$("input[id='plan.pedido']").attr("data-code", data.pedido.id);
			
			if(data.planProducidos != null){
				
				$(data.planProducidos).each(function(i, item){
					
					$(".form.list tbody").append(
						"<tr data-row-code='" + item.id + "'>" +
							"<td data-field='item' ><span>" + item.item + "</span></td>" +
							"<td data-field='personal' data-value='" + item.personal.id + "'>" +
								"<span>" + item.personal.apellidos + ", " + item.personal.nombres + "</span>" +
							"</td>" +
							"<td data-field='horas'><span>" + item.horas + "</span></td>" +
							"<td data-field='total'><span>" + item.total + "</span></td>" +
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
				"personal" : {
					"id" : element.find("td[data-field='personal']").attr("data-value")
				},
				"item" : element.find("td[data-field='item']").text(),
				"horas" : element.find("td[data-field='horas']").text(),
				"total" : element.find("td[data-field='total']").text()
			};
			
			console.log(detail);
			parametersDetails.push(detail);
			
		});

		var ahora = new Date();
		var estado = ($.getUrlVar("id") != null) ? $("input[id='plan.estado']").val(): "No validado";
		
		var parameters = {
            "rows": 0,
            "resume": 0,
			"id" : parseInt($("input[id='plan.id']").val()),
			"codigo" : $("input[id='plan.codigo']").val(),
			"estado" : estado,
			"fecha" : $("input[id='plan.fecha']").val(),
			"pedido" : {
				"id" : $("input[id='plan.pedido']").attr("data-code")
			},
			"planProducidos" : parametersDetails
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
		
		if($("input[id='planproducido.item']").val() == ""){
			
			var hoy = new Date();
			
			$(".form.list tbody").append(
				"<tr data-row-code=''>" +
					"<td data-field='item'><span>" + ($(".form.list tbody tr").length + 1) + "</span></td>" +
					"<td data-field='personal' data-value='" + $("input[id='planproducido.personal']").attr("data-code") + "'>" +
						"<span>" + $("input[id='planproducido.personal']").val() + "</span>" +
					"</td>" +
					"<td data-field='horas'><span>" + $("input[id='planproducido.horas']").val() + "</span></td>" +
					"<td data-field='total'><span>" + $("input[id='planproducido.total']").val() + "</span></td>" +
					"<td><a data-operation='editarDetalle' href='item=" + $("input[id='planproducido.item']").val() + "'><span class='icon-pencil'></span><span>editar detalle</span></a></td>" +
					"<td><a data-operation='eliminarDetalle' href='item=" + $("input[id='planproducido.item']").val() + "'><span class='icon-bin'></span><span>eliminar detalle</span></a></td>" +
				"</tr>"
			);
			
			$("a[data-operation='editarDetalle']").on("click", editDetail);
			$("a[data-operation='eliminarDetalle']").on("click", deleteDetail);
			
		}else{
			
			console.log($(".form.list tbody tr[data-row-code='" + $("input[id='planproducido.id']").val() + "']"));
			
			var container = $(".form.list tbody tr[data-row-code='" + $("input[id='planproducido.id']").val() + "']");
			
			container.attr("data-row-code", $("input[id='planproducido.id']").val());
			
			container.find("[data-field='item']").html("<span>" + $("input[id='planproducido.item']").val() + "</span>");
			
			container.find("[data-field='personal']").html("<span>" + $("input[id='planproducido.personal']").val() + "</span>");
			container.find("[data-field='personal']").attr("data-value", $("input[id='planproducido.personal']").attr("data-code"));
			
			container.find("[data-field='horas']").html("<span>" + $("input[id='planproducido.horas']").val() + "</span>");
			container.find("[data-field='total']").html("<span>" + $("input[id='planproducido.total']").val() + "</span>");
			
		}
		
		$("input[id='planproducido.id']").val("");
		$("input[id='planproducido.item']").val("");
		
		$("input[id='planproducido.personal']").val("");
		$("input[id='planproducido.personal']").attr("data-code","");
		
		$("input[id='planproducido.horas']").val("");
		$("input[id='planproducido.total']").val("");
		
	});
	
});

function editDetail(event){
	
	event.preventDefault();
	
	var element = $(this);
	var container = element.parent().parent();

	console.log(element.attr("href"));
	
	$("input[id='planproducido.id']").val(container.attr("data-row-code"));
	
	$("input[id='planproducido.item']").val(container.find("td[data-field='item']").text());
	$("input[id='planproducido.horas']").val(container.find("td[data-field='horas']").text());
	$("input[id='planproducido.total']").val(container.find("td[data-field='total']").text());
	
	$("input[id='planproducido.insumo']").val(container.find("td[data-field='insumo']").text());
	$("input[id='planproducido.insumo']").attr("data-code", container.find("td[data-field='insumo']").attr("data-value"));
	
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