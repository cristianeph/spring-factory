package com.pl.model;

public class MermaTrabajoModel {
    private MermaModel merma;
    private OrdenTrabajoModel trabajo;

    public MermaModel getMerma() {
        return merma;
    }

    public void setMerma(MermaModel merma) {
        this.merma = merma;
    }

    public OrdenTrabajoModel getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(OrdenTrabajoModel trabajo) {
        this.trabajo = trabajo;
    }
}
