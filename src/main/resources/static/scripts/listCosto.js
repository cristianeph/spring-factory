/**
 * 
 */

var Costo = Backbone.Model.extend();

var Costos = Backbone.Collection.extend({
	model: Costo,
	initialize: function(){console.log("inicializando colleccion!");}
});

var entity = tarjeta;

var parameters = {
	"entity" : entity,
	"action" : ""
};

$(document).ready(function($){
	
	$("input[data-field='inicio']").datepicker({
		dateFormat:"dd/mm/yy",
		showButtonPanel:true,
		changeMonth:true,
		changeYear:true
	}).datepicker("setDate","d m y");
	
	$("input[data-field='fin']").datepicker({
		dateFormat:"dd/mm/yy",
		showButtonPanel:true,
		changeMonth:true,
		changeYear:true
	}).datepicker("setDate","d m y");
	
	$(".acci a").click(function(event){
		
		event.preventDefault();
		
		var element = $(this);
		var action = element.attr("href")
		
		var parameters = {
			"inicio" : $("input[data-field='inicio']").val() + " 00:00:00",
			"fin" : $("input[data-field='fin']").val() + " 00:00:00"
		}
		
		console.log(parameters);
		
		getData(action, parameters).done(function(data){
			
			console.log(data.planTarjetas);
			
			var costosCollection = new Costos(data.planTarjetas);

			console.log(costosCollection);
			
			window.CostosView = Backbone.View.extend({
				el			: ".list",
				template	: _.template($("#template-costos").html()),
			    initialize	:function(){
			        this.render();
			    },
			    render		: function () {
			    	this.$el.html(this.template({collection:this.collection.toJSON()}));
			    }
			});
			
			window.ReporteCostosView = new CostosView({collection:costosCollection});
			
		});
		
	});
	
});

function updateLista(data){
	
	$("table tbody").empty();
	
	$(data.content).each(function(i, item){
		
		var planes = "";
		var list = item.tarjetaTrabajos;
		
		$(item.tarjetaTrabajos).each(function(i, item){
			
			var semicollon = ((list.length-1) == i) ? "" : ", ";
			planes += "<strong>" + item.plan.codigo + "</strong> - " + item.plan.fecha + semicollon;
			
		});

		var horas = 0;
		
		$(item.tarjetaTrabajos).each(function(i, item){
			horas += parseInt(item.horas);
		});
		
		$("table tbody").append(
			"<tr data-code='" + item.id + "'>" +
			"<td data-field='codigo'><span>" + item.id + "</span></td>" +
			"<td><span>" + planes + "</span></td>" +
			"<td><span>" + horas + "</span></td>" +
			"</tr>"
		);
		
		$("#list").trigger("update");
		
	});

	$(".titl ul li:nth-child(2) a span:nth-child(2)").text(" " + data.content.length.toString() + " registros listados");
	$("table tfoot tr td span").empty().html(data.content.length.toString() + " registros listados...");
	
}