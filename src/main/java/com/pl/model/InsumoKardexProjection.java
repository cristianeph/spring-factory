package com.pl.model;

public interface InsumoKardexProjection {
    Integer getId();
    Integer getStock();
    InsumoProjection getInsumo();
    interface InsumoProjection {
        Integer getId();
        String getDescripcion();
    }
}
