/**
 * 
 */
var delay = 0;

var actual = window.location.href;

var today = new Date();

var notification = {
	"element" : "",
	"type" : "",
	"icon" : "",
	"mode" : "",
	"event" : "",
	"description" : "",
	"recomendation" : "",
	"description" : "",
	"action" : "",
	"spin" : false
};

var pedido = {
	"id" : 0,
	"codigo" : "",
	"fecha" : null,
	"cantidad" : 0,
}

var plan = {
	"id" : 0,
	"codigo" : "",
	"fecha" : null,
	"estado" : "",
	"solicitud" : 0
}

var insumo = {
	"id" : 0,
	"descripcion" : "",
	"relacion" : ""
}

var tarjeta = {
	"id" : 0
}

var maquina = {
	"id" : 0,
	"descripcion" : ""
}

var actividad = {
	"id" : 0,
	"descripcion" : ""
}

var producto = {
	"id" : 0,
	"descripcion" : ""
}

var formula = {
	"id" : 0,
	"codigo" : "",
	"nombre" : "",
	"fecha" : null
}

var personal = {
	"id" : 0,
	"codigo" : "",
	"nombres" : "",
	"apellidos" : ""
}

var cliente = {
	"id" : 0,
	"razonsocial" : "",
	"ruc" : "",
	"direccion" : ""
}

var periodo = {
	"id" : 0,
	"ano" : "",
	"mes" : "",
	"estado" : ""
}

var baseObjects = {
	"plan" : plan,
	"pedido" : pedido,
	"insumo" : insumo,
	"tarjeta" : tarjeta,
	"maquina" : maquina,
	"actividad" : actividad,
	"producto" : producto,
	"formula" : formula,
	"personal" : personal,
	"cliente" : cliente,
	"periodo" : periodo
};

/*$(document).ready(function($){
	
	var urlPage = location.pathname;
	var urlParts = urlPage.split("/");
	var urlReform = "";
	
	$(urlParts).each(function(i, item){
		
		if(i < urlParts.length-1){
		
			urlReform += item + "/";
			
		}
		
	});
	
	console.log(urlReform);
	
	$(".quic a[href*='" + urlReform + "list']").attr("class", "sele");
	
	$("a[data-operation='cancelModal']").click(function(e){
		
		e.preventDefault();
		
		$(".moda").hide();
		
	});
	
	$("input.filt").on("keyup", function(e){
		
		var valor = $(this);
		
		if(valor.val() == ""){
			
			$("table tbody tr").show();
			
		}else{
			
			$("table tbody tr").hide();
			$("table tbody tr td").each(function(i, item){
				
				if($(this).is(":icontains('" + valor.val() + "')") == true){
					
					$(this).parent().show();
					
				} 
				
			});
			
		}
		
	});
	
	$("a[data-operation='imprimirDocumento']").click(function(e){
		
		window.print();
	
	});
	
	$(".noti a").click(function(event){
		event.preventDefault();
		$(".noti").toggleClass("show");
		$("body").toggleClass("over");
	});
	
});*/

window.addEventListener("popstate", function(e){
	
	if(e.state != null){
		
		console.log(e.state);
		var retrieve = e.state
		history.replaceState(retrieve, null, actual + "?" + $.param(retrieve.entity));
		retrieveData(retrieve.entity, retrieve.action);
			
	}
	
});

function getBase64FromImage(input, output){
	
	if(input.files && input.files[0]){
		
		var reader = new FileReader();
		
		reader.onload = function(e){
			
			output.val(e.target.result);
			console.log(e.target.result);
		};
		
		reader.readAsDataURL(input.files[0]);
		
	}
	
}

function speakAsistant(text, lang, rate){
	
	var speech = new SpeechSynthesisUtterance();
	speech.text = 'Bienvenido al sistema, le informamos que hemos visto que sus ventas han crecido un 10% con respecto a la semana pasada';
	speech.lang = 'es-419';
	speech.rate = 1;
	speech.onend = function(event){
		console.log('Text to voice API: Synthesis finished in ' + event.elapsedTime + ' seconds.');
	}
    speechSynthesis.speak(speech);
	
}

function showNotification(){
	
	console.log("building notification panel");
	
	$(".noti").toggleClass("show");
	$("body").toggleClass("over");
	
}

function getBirthdayAlerts(){
	
	
	
}


function getData(url, data){
	
	//console.log(data);	

	var token = document.querySelector("meta[name='_csrf']").getAttribute("content");
	var header = document.querySelector("meta[name='_csrf_header']").getAttribute("content");	
	var xhr = new XMLHttpRequest();
	
	xhr.open("POST", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.setRequestHeader(header, token);
	xhr.send(JSON.stringify(data));
	
	return xhr;
	
}

function pad(number, length) {
	   
	var str = '' + number;
	
    while(str.length < length){
    	
    	str = '0' + str;
    	
    }
    		   
    return str;

}

function getStringLocalDate(fecha){
	
	return pad(fecha.getDate(),2)+"/"+pad(fecha.getMonth()+1,2)+"/"+fecha.getFullYear() /*mac*/
	//return pad(fecha.getMonth()+1,2)+"/"+pad(fecha.getDate(),2)+"/"+fecha.getFullYear() /*windows*/
	
}

function getLocalDate(fecha){
	
	var fechaEvaluada = fecha;
	
	if(fechaEvaluada != null){
		
		fechaEvaluada = fechaEvaluada.substring(0, fechaEvaluada.indexOf("T"));
		fechaEvaluada = fechaEvaluada.replace(/-/g, "/");
		fechaEvaluada = new Date(fechaEvaluada);
		fechaEvaluada = pad(fechaEvaluada.getDate(),2)+"/"+pad(fechaEvaluada.getMonth()+1,2)+"/"+fechaEvaluada.getFullYear();
		
	}else{
		
		fechaEvaluada = "";
		
	}
	
	return fechaEvaluada;
	
}

function calcularEdad(fechaNacimiento){
	
	var edad = 0;
	
	fechaActual = new Date();
	//fechaNacimiento = new Date(1991,8,16);
	if(fechaNacimiento.getMonth() <= fechaActual.getMonth()){
		
		if((fechaNacimiento.getMonth()==fechaActual.getMonth()) && (fechaNacimiento.getDay() <= fechaActual.getDay())){
			
			edad = (fechaActual.getFullYear() - fechaNacimiento.getFullYear()) -1;
			
		}else{
			
			edad = fechaActual.getFullYear() - fechaNacimiento.getFullYear();
			
		}
		
	}else{
		
		edad = (fechaActual.getFullYear() - fechaNacimiento.getFullYear()) -1;
		
	}
	
	return edad;
	
}

function insertParam(key, value) {
    key = escape(key); value = escape(value);

    var kvp = document.location.search.substr(1).split('&');
    if (kvp == '') {
        document.location.search = '?' + key + '=' + value;
    }
    else {

        var i = kvp.length; var x; while (i--) {
            x = kvp[i].split('=');

            if (x[0] == key) {
                x[1] = value;
                kvp[i] = x.join('=');
                break;
            }
        }

        if (i < 0) { kvp[kvp.length] = [key, value].join('='); }

        //this will reload the page, it's likely better to store this until finished
        document.location.search = kvp.join('&');
    }
}

function utf8_to_b64(str) {
	return window.btoa(unescape(encodeURIComponent(str)));
}

function b64_to_utf8(str) {
	return decodeURIComponent(escape(window.atob(str)));
}