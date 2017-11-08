/**
 *
 */
var urlPage = location.pathname;
var Requestinput = null;
var urlResource = BASE_PATH + "/api/production/solicitudinsumo";
var urlParametersResource = BASE_PATH + "/api/production/solicitudinsumo/parameters";

document.addEventListener("DOMContentLoaded", function () {

    Requestinput = new Vue({
        el: "#request",
        data: {
            id: 0,
            codigo: 0,
            fecha: "",
            idPlan: 0,
            cantidad: 0,
            movimientoDetalle: {
                id: 0,
                insumo: {
                    selected: "",
                    options: []
                }
            },
            estado: "",
        },
        methods: {
            setData: function(data) {
                this.movimientoDetalle.insumo.selected = data.movimientoDetalle.insumo.id;
                this.movimientoDetalle.id = data.movimientoDetalle.id;
                this.id = data.id;
                this.codigo = data.codigo;
                this.fecha = data.fecha;
                this.idPlan = data.idPlan;
                this.cantidad = data.cantidad;
                this.estado = data.estado;
            },
            getData: function() {
                var object = JSON.parse(JSON.stringify(this.$data));
                object.movimientoDetalle.insumo = {"id" : document.querySelector("select[name='insumo']").value};
                return object;
            }
        },
        beforeMount: function() {
            getData(urlParametersResource).onload = function() {
                var response = JSON.parse(this.responseText);
                Requestinput.movimientoDetalle.insumo.options = response.content;
                Requestinput.movimientoDetalle.insumo.selected = "";
                if(getUrlValue().id != undefined){
                    var url = urlResource + "/" + getUrlValue().id;
                    getData(url).onload = function(data){
                        var response = JSON.parse(this.responseText);
                        Requestinput.setData(response);
                    };
                }
            };
        }
    });

    document.querySelector("[data-operation='grabarDocumento']").onclick = function(event){
        event.preventDefault();
        var object = Requestinput.getData();
        console.log(object);
        var url = urlResource;
        postData(url, object).onload = function() {
            var response = JSON.parse(this.responseText);
            if (handleHttpStatus(response) == true) {
                console.log(response);
                Requestinput.setData(response);
                alert("El registro ha sido grabado correctamente");
            }
        }
    }

});