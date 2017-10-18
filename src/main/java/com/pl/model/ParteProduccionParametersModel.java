package com.pl.model;

public class ParteProduccionParametersModel {
    private Iterable<OrdenTrabajoModel> trabajos;
    private Iterable<MaquinaModel> maquinas;
    private Iterable<ActividadModel> actividades;

    public ParteProduccionParametersModel() {
    }

    public Iterable<OrdenTrabajoModel> getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(Iterable<OrdenTrabajoModel> trabajos) {
        this.trabajos = trabajos;
    }

    public Iterable<MaquinaModel> getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(Iterable<MaquinaModel> maquinas) {
        this.maquinas = maquinas;
    }

    public Iterable<ActividadModel> getActividades() {
        return actividades;
    }

    public void setActividades(Iterable<ActividadModel> actividades) {
        this.actividades = actividades;
    }
}
