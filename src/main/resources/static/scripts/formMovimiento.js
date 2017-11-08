/**
 * 
 */
var urlPage = location.pathname;
var Warehouse = null;
var urlResource = BASE_PATH + "/api/production/movimientoalmacen";
var urlParametersResource = BASE_PATH + "/api/production/movimientoalmacen/parameters";

document.addEventListener("DOMContentLoaded", function () {

    Warehouse = new Vue({
		el: "#warehouse",
		data: {
            id: 0,
            codigo: 0,
            fecha: "",
            tipo: 0,
            cantidad: 0,
            movimientoDetalle: {
                id: 0,
                insumo: {
                    selected: "",
                    options: []
                }
            }
		},
		methods: {
			setData: function(data) {
                this.movimientoDetalle.insumo.selected = data.movimientoDetalle.insumo.id;
                this.movimientoDetalle.id = data.movimientoDetalle.id;
				this.id = data.id;
				this.codigo = data.codigo;
				this.fecha = data.fecha;
                this.tipo = data.tipo;
                this.cantidad = data.cantidad;
			},
			getData: function() {
			    var object = JSON.parse(JSON.stringify(this.$data))
                object.movimientoDetalle.insumo = {"id" : document.querySelector("select[name='insumo']").value};
				return object;
			}
		},
		beforeMount: function() {
            getData(urlParametersResource).onload = function() {
                var response = JSON.parse(this.responseText);
                Warehouse.movimientoDetalle.insumo.options = response.content;
                Warehouse.movimientoDetalle.insumo.selected = "";
                if(getUrlValue().id != undefined){
                    var url = urlResource + "/" + getUrlValue().id;
                    getData(url).onload = function(data){
                        var response = JSON.parse(this.responseText);
                        Warehouse.setData(response);
                    };
                }
            };
		}
	});
	
	document.querySelector("[data-operation='grabarDocumento']").onclick = function(event){
		event.preventDefault();
		var object = Warehouse.getData();
		console.log(object);
		var url = urlResource;
		postData(url, object).onload = function() {
			var response = JSON.parse(this.responseText);
            if (handleHttpStatus(response) == true) {
				console.log(response);
                Warehouse.setData(response);
                alert("El registro ha sido grabado correctamente");
            }
		}
	}
	
});