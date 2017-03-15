/**
 * 
 */

var parameters = {
	"entity" : null,
	"action" : ""
};

$(document).ready(function($){
	
	$("input[id='relacion.criterio']").on("keyup", function(e){
		
		if(e.keyCode == 13){
			
			console.log("se cancelo el enter!");
			return false;
			
		}else{
		
			var value = $(this).val();
			var action = $(this).attr("data-action");
			var field = $(this).attr("data-field");
			
			var property = $(this).attr("data-property");
			var entity = $(this).attr("data-entity");
			console.log(entity + " : " + property);
			baseObjects[entity][property] = value;
			
			if(value != ""){
				
				clearTimeout(delay);
				
				delay = setTimeout(function(){
					
					retrieveData(baseObjects[entity], action);
					
				},1000);
				
			}
			
		}
		
	});
		
	$("a[data-operation='cambiar']").click(fireFindModal);
	
});

function fireFindModal(event){
	
	event.preventDefault();
	
	var element = $(this);
	var parameters = {
		"property" : element.attr("data-property"),
		"entity" : element.attr("data-entity"),
		"action" : element.attr("href"),
		"element" : element.attr("data-element"),
		"title" : element.attr("data-title"),
		"id" : element.parent().find("input").attr("id")
	};
	
	fireFindModalData(parameters, true);
	
}

function fireFindModalData(parameters, search){
	
	$(".moda").show();
	
	$(".moda .form input").val("");
	
	if(search == false){
		
		retrieveData(parameters.value, parameters.action);
		
		$(".moda .form").hide();
		
	}else{
		
		$(".moda .list table tfoot tr:nth-child(2)").hide();
		
	}
	
	$(".moda .find").find(".titl div:nth-child(2) strong").text(parameters.title);
	$(".moda .find").find("table tbody").empty();
	$(".moda .find").find("input[id='relacion.criterio']").focus();
	$(".moda .find").find("input[id='relacion.criterio']").attr("placeholder", parameters.property);
	$(".moda .find").find("input[id='relacion.criterio']").attr("data-property", parameters.property);
	$(".moda .find").find("input[id='relacion.criterio']").attr("data-entity", parameters.entity);
	$(".moda .find").find("input[id='relacion.criterio']").attr("data-action", parameters.action);
	$(".moda .find").find("input[id='relacion.criterio']").attr("data-input", parameters.element);
	$(".moda .find").find("input[id='relacion.criterio']").attr("data-element", parameters.element);
	
}

function retrieveData(entity, action){

	showNotification();
	clearTimeout(delay);
	
	delay = setTimeout(function(){
		
		parameters.entity = entity;
		parameters.action = action;
		
		console.log("se ejecuto la consulta al servidor");
		console.log(entity);
		
		getData(action, entity)
			.done(function(data){
				
				console.log(data);
				console.log("el servidor respondio sin problemas");
				updateReferences(data);
				showNotification();
				
			})
			.fail(function(data){
				
				console.log("el servidor tiene problemas para responder la peticion");
				
			});
	
	}, 1800);
	
}