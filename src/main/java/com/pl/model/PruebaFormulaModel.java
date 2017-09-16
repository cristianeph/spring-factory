package com.pl.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(
        name = "pruebaformula"
)
public class PruebaFormulaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String codigo;
    private String estado;
    private String observaciones;
    private String sugerencias;
    private BigDecimal merma;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getSugerencias() {
        return sugerencias;
    }

    public void setSugerencias(String sugerencias) {
        this.sugerencias = sugerencias;
    }

    public BigDecimal getMerma() {
        return merma;
    }

    public void setMerma(BigDecimal merma) {
        this.merma = merma;
    }
}
