/**
 * 
 */
$(document).ready(function($){
	$("#list").tablesorter();

	$(".busq a, .reco a, .filt a, .rang a").click(function(e){
		e.preventDefault();
	});
	$(".busq input").on("keyup", function(e){
		if(e.keyCode == 13){
			console.log("se cancelo el enter!");
			return false;
		}else{
			var value = $(this).val();
			var action = $(this).parent().attr("href");
			var field = $(this).attr("data-field");
			entity[field] = value;
			clearTimeout(delay);
			delay = setTimeout(function(){
				retrieveData(entity, action);
			},1000);
		}
	});
	$(".resu a").click(function(e){
		e.preventDefault();
		var action = $(this).attr("href");
		var value = $(this).attr("data-value");
		entity["resume"] = parseInt(value);
		retrieveData(entity, action);
	});
	$(".reco select").change(function(){
		var action = $(this).parent().attr("href");
		var value = $(this).val();
		if(value != ""){
			entity["rows"] = parseInt(value);
			retrieveData(entity, action);
		}
	});
	
});

function retrieveData(entity, action){
	console.log("consultando...")
	showNotification();
	clearTimeout(delay);
	
	delay = setTimeout(function(){
		
		parameters.entity = entity;
		parameters.action = action;
		
		for(var propertyName in entity) {
		   
			// you can get the value like this: myObject[propertyName]

			$(".titl ul li a[id='data." + propertyName + "']").parent().remove();
			
			if(	propertyName != "registrado" &&
				entity[propertyName] != 0 && 
				entity[propertyName] != null && 
				entity[propertyName] != "" && 
				entity[propertyName] != "01/01/2015" && 
				entity[propertyName] != "01/01/2015 00:00:00"){
				
				$(".titl ul").append(
					"<li>" +
						"<a href='#' id='data." + propertyName + "'><span class='icon-cross'></span><span>" + propertyName + ": " + entity[propertyName] + "</span></a>" +
					"</li>"
				);
				
			}
			
		}
		
		history.pushState(parameters, null, actual + "?" + $.param(entity));
		
		console.log("se ejecuto la consulta al servidor");
		console.log(entity);
		
		getData(action, entity)
			.done(function(data){
				console.log(data);
				console.log("el servidor respondio sin problemas");
				updateLista(data);
				showNotification();
				
			})/*
			.fail(function(data){
				console.log("el servidor tiene problemas para responder la peticion");
			});*/
	
	}, 1000);
	
}