/**
 * 
 */

var allMonthEvents = [];

$(document).ready(function($){
	
	$(".navi a[href*='calendar']").attr("class", "sele");
	
	var ahora = new Date();
	
	var parameters = cliente;
	parameters.resume = 3;
	parameters.fechaNacimiento = ahora.format("dd/mm/yyyy HH:MM:ss");
	
	getData($("#getCustomers").attr("href"), parameters).done(function(data){
		
		buildCalendarEvents(data.content, "birthday");
		
		console.log(allMonthEvents);
		
		/*$("#cale-home").css({"color":"#717171"}).fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			locale: "es",
			editable: false,
			events: allMonthEvents,
			eventRender: function(event, element){
				element.find('span.fc-title').html(element.find('span.fc-title').text());
			}
		});*/
		
		var parameters = cita;
		parameters.resume = 1;
		parameters.programado = ahora.format("dd/mm/yyyy HH:MM:ss");
		
		getData($("#getAppointments").attr("href"), parameters).done(function(data){
			
			buildCalendarEvents(data.content, "appointment");
			
			console.log(allMonthEvents);
			
			$("#cale-home").css({"color":"#717171"}).fullCalendar({
				header: false,
				height: 900,
				firstDay: 0,
				locale: "es",
				editable: false,
				events: allMonthEvents,
				eventRender: function(event, element){
					element.find('span.fc-title').html(element.find('span.fc-title').text());
				}
			});
			
		});
		
	});
	
});

function buildCalendarEvents(events, type){
	
	$(events).each(function(i, item){
		
		var date = new Date();
		
		if(type == "birthday"){
			
			var fechaEvaluada = item.fechaNacimiento.split(" ")[0];
			fechaEvaluada = fechaEvaluada.split("/");
			
			var birthdayDate = new Date(fechaEvaluada[2], fechaEvaluada[1] - 1, fechaEvaluada[0]);
			var birthdayCelebration = birthdayDate.setFullYear(date.getFullYear());

			//console.log(item.fechaNacimiento + " : " + moment(birthdayCelebration).format("YYYY-MM-DDThh:mm:ss"));
			
			var event = {
				"title" : "<i class='icon-gift'></i> " + item.apellidos + " " + item.nombres,
				"url" : $("#viewCustomer").attr("href") + "?codigo=" + item.codigo,
				"allDay" : true,
				"start" : moment(birthdayCelebration).format("YYYY-MM-DDThh:mm:ss"),
				"end" : moment(birthdayCelebration).format("YYYY-MM-DDThh:mm:ss"),
				"color" : "transparent",
				"textColor" : "#6B6B6B"
			};
			
		}else if(type == "appointment"){
			
			console.log(item);
			
			if(item.efectuado != null){
				
				var fechaEvaluada = item.efectuado.split(" ")[0];
				fechaEvaluada = fechaEvaluada.split("/");
				
				var birthdayDate = new Date(fechaEvaluada[2], fechaEvaluada[1] - 1, fechaEvaluada[0]);
				var birthdayCelebration = birthdayDate.setFullYear(date.getFullYear());
			
				var event = {
					"title" : "Cita: " + item.cliente.apellidos + " " + item.cliente.nombres,
					"url" : $("#viewAppointment").attr("href") + "?codigo=" + item.codigo,
					"start" : moment(birthdayCelebration).format("YYYY-MM-DDThh:mm:ss"),
					"end" : moment(birthdayCelebration).format("YYYY-MM-DDThh:mm:ss"),
					"color" : "#EA774D",
					"textColor" : "#F7E7E0"
				};
				
			}else{
				
				var fechaEvaluada = item.programado.split(" ")[0];
				fechaEvaluada = fechaEvaluada.split("/");
				
				var birthdayDate = new Date(fechaEvaluada[2], fechaEvaluada[1] - 1, fechaEvaluada[0]);
				var birthdayCelebration = birthdayDate.setFullYear(date.getFullYear());
			
				var event = {
					"title" : "Cita: " + item.cliente.apellidos + " " + item.cliente.nombres,
					"url" : $("#viewAppointment").attr("href") + "?codigo=" + item.codigo,
					"start" : moment(birthdayCelebration).format("YYYY-MM-DDThh:mm:ss"),
					"end" : moment(birthdayCelebration).format("YYYY-MM-DDThh:mm:ss"),
					"color" : "#5C4A45",
					"textColor" : "#F7E7E0"
				};
				
			}
			
		}
		
		allMonthEvents.push(event);
		
	});
	
}