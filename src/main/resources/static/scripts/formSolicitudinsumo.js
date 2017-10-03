/**
 *
 */
var urlPage = location.pathname;
var Requestinput = null;
var urlResource = BASE_PATH + "/api/production/solicitudinsumo";
var urlParametersResource = BASE_PATH + "/api/production/insumo";
var insumoList = null;

document.addEventListener("DOMContentLoaded", function () {

    Requestinput = new Vue({
        el: "#request",
        data: {
            id: 0,
            insumo: {
                selected: "",
                options: insumoList
            },
            idPlan: 0,
            cantidad: 0,
            codigo: "",
            estado: "",
        },
        methods: {
            setData: function(data) {
                this.id = data.id;
                this.insumo.selected = data.insumo.id;
                this.idPlan = data.idPlan;
                this.cantidad = data.cantidad;
                this.codigo = data.codigo;
                this.estado = data.estado;
            },
            getData: function() {
                var object = JSON.parse(JSON.stringify(this.$data));
                object.insumo = {"id" : document.querySelector("select[name='insumo']").value};
                return object;
            }
        },
        beforeMount: function() {
            getData(urlParametersResource).onload = function() {
                var response = JSON.parse(this.responseText);
                Requestinput.insumo.options = response.content;
                Requestinput.insumo.selected = "";
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