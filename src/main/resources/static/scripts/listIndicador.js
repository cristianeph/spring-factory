/**
 * 
 */

var Avance = Backbone.Model.extend();
var Productividad = Backbone.Model.extend();
var Incidencia = Backbone.Model.extend();
var Merma = Backbone.Model.extend();
var Rechazo = Backbone.Model.extend();

var Avances = Backbone.Collection.extend({
	model: Avance,
	initialize: function(){console.log("inicializando colleccion!");}
});
var Productividades = Backbone.Collection.extend({
	model: Productividad,
	initialize: function(){console.log("inicializando colleccion!");}
});
var Incidencias = Backbone.Collection.extend({
	model: Incidencia,
	initialize: function(){console.log("inicializando colleccion!");}
});
var Mermas = Backbone.Collection.extend({
	model: Merma,
	initialize: function(){console.log("inicializando colleccion!");}
});
var Rechazos = Backbone.Collection.extend({
	model: Rechazo,
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
	
	$("a[data-operation='indicatorAvance']").click(function(event){
		
		event.preventDefault();
		
		var element = $(this);
		var action = element.attr("href")
		
		var parameters = {
			"inicio" : $("input[data-field='inicio']").val() + " 00:00:00",
			"fin" : $("input[data-field='fin']").val() + " 00:00:00"
		}
		
		console.log(parameters);
		
		getData(action, parameters).done(function(data){
			
			console.log(data);
			
			var avancesCollection = new Avances(data.pedidoPlanes);

			console.log(avancesCollection);
			
			window.AvancesView = Backbone.View.extend({
				el			: ".list",
				template	: _.template($("#template-avance").html()),
			    initialize	:function(){
			        this.render();
			    },
			    render		: function () {
			    	this.$el.html(this.template({collection:this.collection.toJSON()}));
			    }
			});
			
			window.IndicadorAvancesView = new AvancesView({collection:avancesCollection});
			
		});
		
	});
	
	$("a[data-operation='indicatorProductividad']").click(function(event){
		
		event.preventDefault();
		
		var element = $(this);
		var action = element.attr("href")
		
		var parameters = {
			"inicio" : $("input[data-field='inicio']").val() + " 00:00:00",
			"fin" : $("input[data-field='fin']").val() + " 00:00:00"
		}
		
		console.log(parameters);
		
		getData(action, parameters).done(function(data){
			
			console.log(data);
			
			var productividadesCollection = new Productividades(data.planes.content);

			console.log(productividadesCollection);
			
			window.ProductividadesView = Backbone.View.extend({
				el			: ".list",
				template	: _.template($("#template-productividad").html()),
			    initialize	:function(){
			        this.render();
			    },
			    render		: function () {
			    	this.$el.html(this.template({collection:this.collection.toJSON()}));
			    }
			});
			
			window.IndicadorProductividadesView = new ProductividadesView({collection:productividadesCollection});
			
		});
		
	});
	
	$("a[data-operation='indicatorIncidencia']").click(function(event){
		
		event.preventDefault();
		
		var element = $(this);
		var action = element.attr("href")
		
		var parameters = {
			"inicio" : $("input[data-field='inicio']").val() + " 00:00:00",
			"fin" : $("input[data-field='fin']").val() + " 00:00:00"
		}
		
		console.log(parameters);
		
		getData(action, parameters).done(function(data){
			
			console.log(data);
			
			var incidenciasCollection = new Incidencias(data.trabajos.content);

			console.log(incidenciasCollection);
			
			window.IncidenciasView = Backbone.View.extend({
				el			: ".list",
				template	: _.template($("#template-incidencia").html()),
			    initialize	:function(){
			        this.render();
			    },
			    render		: function () {
			    	this.$el.html(this.template({collection:this.collection.toJSON()}));
			    }
			});
			
			window.IndicadorIncidenciasView = new IncidenciasView({collection:incidenciasCollection});
			
		});
		
	});
	
	$("a[data-operation='indicatorMerma']").click(function(event){
		
		event.preventDefault();
		
		var element = $(this);
		var action = element.attr("href")
		
		var parameters = {
			"inicio" : $("input[data-field='inicio']").val() + " 00:00:00",
			"fin" : $("input[data-field='fin']").val() + " 00:00:00"
		}
		
		console.log(parameters);
		
		getData(action, parameters).done(function(data){
			
			console.log(data);
			
			var mermasCollection = new Mermas(data.trabajos.content);

			console.log(mermasCollection);
			
			window.MermasView = Backbone.View.extend({
				el			: ".list",
				template	: _.template($("#template-merma").html()),
			    initialize	:function(){
			        this.render();
			    },
			    render		: function () {
			    	this.$el.html(this.template({collection:this.collection.toJSON()}));
			    }
			});
			
			window.IndicadorMermasView = new MermasView({collection:mermasCollection});
			
		});
		
	});
	
	$("a[data-operation='indicatorRechazo']").click(function(event){
		
		event.preventDefault();
		
		var element = $(this);
		var action = element.attr("href")
		
		var parameters = {
			"inicio" : $("input[data-field='inicio']").val() + " 00:00:00",
			"fin" : $("input[data-field='fin']").val() + " 00:00:00"
		}
		
		console.log(parameters);
		
		getData(action, parameters).done(function(data){
			
			console.log(data);
			
			var rechazosCollection = new Rechazos(data.planes.content);

			console.log(rechazosCollection);
			
			window.RechazosView = Backbone.View.extend({
				el			: ".list",
				template	: _.template($("#template-rechazo").html()),
			    initialize	:function(){
			        this.render();
			    },
			    render		: function () {
			    	this.$el.html(this.template({collection:this.collection.toJSON()}));
			    }
			});
			
			window.IndicadorRechazosView = new RechazosView({collection:rechazosCollection});
			
		});
		
	});
	
});