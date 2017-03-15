/**
 * 
 */

var Costo = Backbone.Model.extend({
	defaults: {
		plan	:null,
		trabajo	:null
	}
});

var Costos = Backbone.Collection.extend({
	model: Costo,
	initialize: function(){
		console.log("inicializando colleccion!");
	}
});

$(document).ready(function($){
	
	if($.getUrlVar("id") != null){
		
		var parameters = {
			"id" : parseInt($.getUrlVar("id"))
		};
		
		getData($("#documentView").attr("href"), parameters).done(function(data){
			
			console.log(data);
			
			$("input[id='periodo.id']").val(data.id);
			$("input[id='periodo.ano']").val(data.ano);
			$("input[id='periodo.mes']").val(data.mes);
			$("input[id='periodo.estado']").val(data.estado);
			
			var date = new Date();
			date.setMonth(parseInt($("input[id='periodo.mes']").val()) - 1);
			
			var firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
			var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
			
			parameters = {
				"inicio" : firstDay.format("dd/mm/yyyy HH:MM:ss"),
				"fin" : lastDay.format("dd/mm/yyyy HH:MM:ss")
			}
			
			console.log(parameters);
			
			getData($("#getCosts").attr("href"), parameters).done(function(data){
				
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
		
	}
	
	$("a[data-operation='grabarDocumento']").click(function(e){
		
		e.preventDefault();
		
		if($("input[id='periodo.estado']").val() == "pendiente"){
			
			var ahora = new Date();
			
			var parameters = {
				"id": $("input[id='periodo.id']").val(),
				"ano": $("input[id='periodo.ano']").val(),
				"mes": $("input[id='periodo.mes']").val(),
	            "estado": "enviado"
			};
			
			console.log(parameters);
			
			getData($("#documentSave").attr("href"), parameters).done(function(data){
				
				console.log("grabo/actualizo toda el registro!");
				
				alert("El sistema envio el costo de produccion del periodo seleccionado");
				
				$("input[id='periodo.estado']").val(data.estado);
				
			});
			
		}
		
	});
	
});