/**
 *
 */
var urlPage = location.pathname;
var Production = null;
var urlResource = "/api/production/parteproduccion";
var urlParametersResource = "/api/production/trabajo/parameters";
var machineList = null;
var activityList = null;
var planList = null;

document.addEventListener("DOMContentLoaded", function () {

    Production = new Vue({
        el: "#production",
        data: {
            master: {
                id: 0,
                codigo: 0,
                fecha: 0,
                inicio: 0,
                fin: 0,
                maquina: {
                    selected: "",
                    options: machineList
                },
                actividad: {
                    selected: "",
                    options: activityList
                },
                tarjetaTrabajos: []
            },
            detail: {
                id: 0,
                plan: {
                    selected: "",
                    options: planList
                },
                item: 0,
                horas: 0,
                cantidad: 0,
                merma: 0,
                comentarios: "",
                incidencias: ""
            }
        },
        methods: {
            setData: function(data) {
                this.master.id = data.id;
                this.master.codigo = data.codigo;
                this.master.fecha = data.fecha;
                this.master.inicio = data.inicio;
                this.master.fin = data.fin;
                this.master.maquina.selected = (data.maquina) ? data.maquina.id : "";
                this.master.actividad.selected = (data.maquina) ? data.actividad.id: "";
                this.master.tarjetaTrabajos = data.tarjetaTrabajos;
            },
            getData: function() {
                var object = JSON.parse(JSON.stringify(this.$data.master));
                object.maquina = {"id" : document.querySelector("select[name='maquina']").value};
                object.actividad = {"id" : document.querySelector("select[name='actividad']").value};
                return object;
            }
        },
        beforeMount: function() {
            getData(urlParametersResource).onload = function() {
                var response = JSON.parse(this.responseText);
                Production.master.actividad.options = response.maquinas.content;
                Production.master.actividad.selected = "";
                Production.master.maquina.options = response.actividades.content;
                Production.master.maquina.selected = "";

                if(getUrlValue().id != undefined){
                    var url = urlResource + "/" + getUrlValue().id;
                    getData(url).onload = function(data){
                        var response = JSON.parse(this.responseText);
                        Production.setData(response);
                    };
                }
            }
        }
    });

    document.querySelector("[data-operation='grabarDocumento']").onclick = function(event){
        event.preventDefault();
        var object = Production.getData();
        var url = urlResource;
        console.log("entire object", object);
        postData(url, object).onload = function() {
            var response = JSON.parse(this.responseText);
            if (handleHttpStatus(response) == true) {
                console.log("the response", response);
                Production.setData(response);
                alert("El registro ha sido grabado correctamente");
            }
        }
    }

});