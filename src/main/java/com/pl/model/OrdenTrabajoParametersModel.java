package com.pl.model;

public class OrdenTrabajoParametersModel {
    private Iterable<ParteProduccionModel> parte;
    private Iterable<MermaModel> merma;
    private Iterable<PruebaProduccionModel> prueba;

    public OrdenTrabajoParametersModel() {
    }

    public Iterable<ParteProduccionModel> getParte() {
        return parte;
    }

    public void setParte(Iterable<ParteProduccionModel> parte) {
        this.parte = parte;
    }

    public Iterable<MermaModel> getMerma() {
        return merma;
    }

    public void setMerma(Iterable<MermaModel> merma) {
        this.merma = merma;
    }

    public Iterable<PruebaProduccionModel> getPrueba() {
        return prueba;
    }

    public void setPrueba(Iterable<PruebaProduccionModel> prueba) {
        this.prueba = prueba;
    }
}
