/**
 * 
 */

var sustituir = 0;
var validar = 0;
var validarCount = false;

$(document).ready(function($){
	
	$("input[id='plan.fecha']").datetimepicker({
		dateFormat:"dd/mm/yy",
		timeFormat: "HH:mm:ss"
	});
	
	if($.getUrlVar("id") != null){
		
		var parameters = {
			"id" : parseInt($.getUrlVar("id"))
		};
		
		getData($("#documentView").attr("href"), parameters).done(function(data){
			
			console.log(data);
			
			$("input[id='plan.id']").val(data.id);
			$("input[id='plan.codigo']").val(data.codigo);
			$("input[id='plan.fecha']").val(data.fecha);
			$("input[id='plan.estado']").val(data.estado);
			
			$("input[id='pedido.id']").val(data.pedido.id);
			$("input[id='pedido.codigo']").val(data.pedido.codigo);
			$("input[id='pedido.fecha']").val(data.pedido.fecha);
			$("input[id='pedido.cantidad']").val(data.pedido.cantidad);
			
			$("input[id='pedido.cliente']").val(data.pedido.cliente.razonsocial);
			$("input[id='pedido.cliente']").attr("data-value", data.pedido.cliente.id);
			
			$("input[id='pedido.producto']").val(data.pedido.producto.descripcion);
			$("input[id='pedido.producto']").attr("data-value", data.pedido.producto.id);
			
			$("input[id='formula.id']").val(data.pedido.producto.formula.id);
			$("input[id='formula.codigo']").val(data.pedido.producto.formula.codigo);
			$("input[id='formula.nombre']").val(data.pedido.producto.formula.nombre);
			$("input[id='formula.fecha']").val(data.pedido.producto.formula.fecha);
			
			if(data.pedido.pedidoPreparados != null){
				
				var cantidadpedida = data.pedido.cantidad;
				var details = data.pedido.pedidoPreparados;
				var parameters = data;
				
				getData($("#detailCheck").attr("href"), parameters).done(function(data){
					
					$(data).each(function(o, kardexitem){
						
						$(details).each(function(i, formulaitem){
							console.log("comparando: " + kardexitem.idInsumo + " : " + formulaitem.idInsumo);
							if(kardexitem.idInsumo == formulaitem.idInsumo){
								
								var cantidadtotal = formulaitem.cantidad * cantidadpedida;
								var estado = "";
								
								var cubierto = (kardexitem.stock/cantidadtotal);
								console.log(kardexitem.stock + " : " + cantidadtotal);
								var porcentajecubierto = 0
								
								if(kardexitem.stock >= cantidadtotal) {
									porcentajecubierto = 100
								}else{
									porcentajecubierto = ((Math.round(cubierto * 100) / 100) * 100);
								}
								
								if(kardexitem.stock >= cantidadtotal) {
									estado = "<a href='#'><span>Insumo validado</span></a>";
								}else{
									estado = "<a href='#' data-operation='replaceResource'><span>Buscar sustituto</span></a>";
								}
								
								$(".form.list tbody").append(
									"<tr data-row-code='" + formulaitem.id + "'>" +
										"<td data-field='item' ><span>" + formulaitem.item + "</span></td>" +
										"<td data-field='insumo' " +
											"data-value='" + formulaitem.idInsumo + "'" +
											"data-relation='" + formulaitem.relacion + "'" +
											"data-quantity='" + formulaitem.cantidad + "'" +
											"data-cost='" + formulaitem.costo + "'>" +
											"<span>" + formulaitem.descripcion + "</span>" +
										"</td>" +
										"<td data-field='cantidad'><span>" + formulaitem.cantidad + "</span></td>" +
										"<td data-field='total'><span>" + cantidadtotal + "</span></td>" +
										"<td data-field='estado'>" + estado + "</td>" +
										"<td data-field='cubierto'><span>" + porcentajecubierto + " %</span></td>" +
									"</tr>"
								);
								
							}
							
						});
						
					});
					
					$("a[data-operation='replaceResource']").on("click", editDetail);
					
				});
				
			}
			
		});
		
	}
	
	$("a[data-operation='grabarDocumento']").click(function(e){
		
		e.preventDefault();
		
		var correcto = true;
		
		$(".form.list tbody tr").each(function(i, item){
			
			console.log($(item).find("[data-field='estado']").text());
			
			if($(item).find("[data-field='estado']").text() == "Buscar sustituto"){
				
				correcto = false;
				
			}
			
		});
		
		if(correcto == true){
			
			var parameters = {
				"id" : parseInt($("input[id='plan.id']").val())
			}
			
			getData($("#documentState").attr("href"), parameters).done(function(data){
				
				alert("el estado del Plan de Produccion cambio a validado");
				
				$("input[id='plan.estado']").val(data.estado);
				
			});
			
		}else{
			
			alert("no se efectuara ningun cambio en el Plan de Produccion ya que los insumos no estan validados");
			
		}
		
	});
	
	$(".moda .find .list tfoot a[data-operation='acceptChanges']").click(function(event){
		
		event.preventDefault();
		
		var seleccionados = $(".moda .find table tbody tr[data-selected='true']").length;
		
		if(seleccionados > 0){
			
			var seguro = confirm("Esta seguro de sustituir el(los) insumo(s) seleccionado(s)?")
			
			if(seguro == true){
				
				//console.log(sustituir);
				
				var base = $(".form.list tbody tr[data-row-code='" + sustituir + "']");
				
				var temporaryelement = {
					"id" : parseInt($(base).attr("data-row-code")),
					"item" : parseInt($(base).find("[data-field='item']").text()),
					"insumo" : {
						"id" : parseInt($(base).find("[data-field='insumo']").attr("data-value")),
						"descripcion" : $(base).find("[data-field='insumo']").text(),
						"relacion" : parseInt($(base).find("[data-field='insumo']").attr("data-relation")),
						"cantidad" : parseFloat($(base).find("[data-field='insumo']").attr("data-quantity")),
						"costo" : parseFloat($(base).find("[data-field='insumo']").attr("data-cost"))
					},
					"cantidad" : parseInt($(base).find("[data-field='cantidad']").text())
				};
				
				console.log(temporaryelement);
				
				var total = 0;
				
				$(".moda .find table tbody tr[data-selected='true']").each(function(i, item){
					
					var temporaryitem = {
						"id" : parseInt($(item).attr("data-code")),
						"descripcion" : $(item).find("td:nth-child(1)").attr("data-name"),
						"cantidad" : parseInt($(item).find("td:nth-child(2)").attr("data-value")) 
					}
					
					total += temporaryitem.cantidad;
					
					if(total > validar){
						
						temporaryitem.cantidad = validar - (total - temporaryitem.cantidad);
						
					}
					
					console.log(temporaryitem)
					
					$(
						"<tr data-row-code='0'>" +
							"<td data-field='item' ><span></span></td>" +
							"<td data-field='insumo' " +
								"data-value='" + temporaryitem.id + "'" +
								"data-relation='" + temporaryelement.insumo.relacion + "'" +
								"data-quantity='" + temporaryelement.insumo.cantidad + "'" +
								"data-cost='" + temporaryelement.insumo.costo + "'>" +
								"<span>" + temporaryitem.descripcion + "</span>" +
							"</td>" +
							"<td data-field='cantidad'><span>" + (temporaryitem.cantidad / parseFloat($("input[id='pedido.cantidad']").val())) + "</span></td>" +
							"<td data-field='total'><span>" + temporaryitem.cantidad + "</span></td>" +
							"<td data-field='estado'><a href='#'><span>Insumo validado</span></a></td>" +
							"<td data-field='cubierto'><span>100 %</span></td>" +
						"</tr>"
					).insertAfter(".form.list tbody tr[data-row-code='" + sustituir + "']");
					
				});
				
				$(".form.list tbody tr[data-row-code='" + sustituir + "']").remove();
				
				$(".form.list tbody tr").each(function(i, item){
					
					$(item).find("[data-field='item']").html("<span>" + (i+1) + "</span>");
					$(item).find("[data-operation='editarDetalle']").attr("href", "item=" + (i+1));
					$(item).find("[data-operation='eliminarDetalle']").attr("href", "item=" + (i+1));
					
				});
				
				$(".moda").hide();
				
				//validando documento
				
				var parametersDetails = [];
				
				$(".form.list tbody tr").each(function(i, item){
					
					var element = $(item);
					
					var detail = {
						"id" : parseInt(element.attr("data-row-code")),
						"cantidad" : parseFloat(element.find("td[data-field='cantidad']").text()),
						"relacion" : parseFloat(element.find("td[data-field='insumo']").attr("data-relation")),
						"item" : element.find("td[data-field='item']").text(),
						"idInsumo" : element.find("td[data-field='insumo']").attr("data-value"),
						"descripcion" : element.find("td[data-field='insumo']").text(),
						"costo" : parseFloat(element.find("td[data-field='insumo']").attr("data-cost")),
						"costoTotal" : 
							parseFloat(element.find("td[data-field='insumo']").attr("data-cost")) * 
							parseFloat(element.find("td[data-field='cantidad']").text())
					};
					
					parametersDetails.push(detail);
					
				});
				
				console.log(parametersDetails);
				
				var parameters = {
					"id" : parseInt($("input[id='pedido.id']").val()),
					"codigo" : $("input[id='pedido.codigo']").val(),
					"cantidad" : $("input[id='pedido.cantidad']").val(),
					"fecha" : $("input[id='pedido.fecha']").val(),
					"producto" : {
						"id" : parseInt($("input[id='pedido.producto']").attr("data-value"))
					},
					"cliente" : {
						"id" : parseInt($("input[id='pedido.cliente']").attr("data-value"))
					},
					"pedidoPreparados" : parametersDetails
				};
				
				console.log(parameters);
				
				getData($("#detailSave").attr("href"), parameters).done(function(data){
					
					console.log("grabo/actualizo el registro!");
					
					alert("El sistema valido el Insumo sustituto");
					
				});
				
			}
			
		}else{
			
			alert("No ha seleccionado ningun sustituto");
			
		}
		
	});
	
});

function editDetail(event){
	
	event.preventDefault();	
	
	console.log("entrooo");
	
	var element = $(this);
	var container = element.parent().parent();
	
	sustituir = parseInt(container.attr("data-row-code"));
	
	validar = parseInt(container.find("td[data-field='total']").text());
	
	var entityparameters = {
		"id" : parseInt(container.find("td[data-field='insumo']").attr("data-value")),
		"relacion" : parseInt(container.find("td[data-field='insumo']").attr("data-relation"))
	};
	
	var cantidadtotal = parseInt(container.find("td[data-field='total']").text());
	
	getData($("#detailValidate").attr("href"), entityparameters).done(function(data){
		
		//alert("valido!");
		
		if(data.content[0].cantidad >= cantidadtotal){
			
			alert("el sistema mostrara los insumos sustitutos");
			
			var element = $(this);
			var parameters = {
				"property" : "insumo",
				"entity" : "insumo",
				"action" : $("#detailReplace").attr("href"),
				"element" : null,
				"id" : element.parent().find("input").attr("id"),
				"value" : entityparameters
			};
			
			fireFindModalData(parameters, false);
			
		}else{
			
			//alert("solicitud");
			
			var condicion = confirm("No se ha encontrado stock completo del insumo, ni en sus sustitutos. Desea generar una solicitud?");
			
			if(condicion == true){
				
				var parameters = {
					"plan" : {
						"id" : parseInt($("input[id='plan.id']").val())
					},
					"insumo" : {
						"id" : entityparameters.id
					}
				};
				
				getData($("#requestResources").attr("href"), parameters).done(function(data){
					
					alert("se ha generado la solicitud, el estado del plan cambiara");
					
					$("input[id='plan.estado']").val(data.estado);
					
				});
				
			}
			
		}
		
	});
	
}

function updateReferences(data){
	
	$(".moda .find table tbody").empty()
	
	$(data.content).each(function(i, item){
		
		$(".moda .find table tbody").append( 
			"<tr data-selected='false' data-code='" + item.id + "'>" +
				"<td data-name='" + item.descripcion + "'><span>" + item.descripcion  + "</span></td>" +
				"<td data-value='" + item.stock + "'><span>" + item.stock + "</span></td>" +
				"<td data-action='" + item.id + "'><span><input type='checkbox' value='" + item.id + "' /></span></td>" +
				"<td data-action='" + item.id + "'></td>" +
			"</tr>"
		);
		
	});
	
	if($(".moda .find table tbody tr").length > 0){
		
		$(".moda .find table tbody tr input[type='checkbox']").on("click", function(event){
			
			var element = $(this);
			var row = element.parent().parent().parent();
			var selected = row.attr("data-selected");
			
			row.attr("data-selected", (selected == "true") ? "false": "true");
			
			var total = 0;
			
			console.log(element.is(":checked"));
			
			if(element.is(":checked") == true){
				
				$(".moda .find table tbody tr[data-selected='true']").each(function(i, item){
					
					var value = parseInt($(item).find("td:nth-child(2)").attr("data-value"));
					
					total += value;
					
					if(validar >= total){
						
						console.log("bien");
						
					}else{
						
						console.log("mal");
						
						if(validarCount == true){
							
							alert("no puede seleccionar mas items, el stock de sustitutos ya cubre lo requerido en la formula");
							
							row.attr("data-selected", "false");
							
							total = total - value;
							
							event.preventDefault();
							
						}else{
							
							validarCount = true;
							
						}
						
					}
					
				});
				
				console.log("evaluar: validar = " + validar + " : total = " + total);
				
			}else{
				
				row.attr("data-selected", "false");
				
				if(validarCount == true){
					
					validarCount = false;
					
				}
				
			}
			
		});
		
	}
	
}