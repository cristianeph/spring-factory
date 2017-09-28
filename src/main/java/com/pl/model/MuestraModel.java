package com.pl.model;

import javax.persistence.*;

@Entity
@Table(
        name="muestra"
)
public class MuestraModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String descripcion;
    private String resultado;
    private String viscocidad;
    private String rendimiento;
    private String recipiente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getViscocidad() {
        return viscocidad;
    }

    public void setViscocidad(String viscocidad) {
        this.viscocidad = viscocidad;
    }

    public String getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(String rendimiento) {
        this.rendimiento = rendimiento;
    }

    public String getRecipiente() {
        return recipiente;
    }

    public void setRecipiente(String recipiente) {
        this.recipiente = recipiente;
    }
}
