package com.pl.model;

public class MuestraPruebaModel {
    private Iterable<MuestraModel> muestras;
    private Iterable<PruebaFormulaModel> pruebas;

    public MuestraPruebaModel() {
    }

    public Iterable<MuestraModel> getMuestras() {
        return muestras;
    }

    public void setMuestras(Iterable<MuestraModel> muestras) {
        this.muestras = muestras;
    }

    public Iterable<PruebaFormulaModel> getPruebas() {
        return pruebas;
    }

    public void setPruebas(Iterable<PruebaFormulaModel> pruebas) {
        this.pruebas = pruebas;
    }
}
