package com.pl.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(
        name="solicitudformula"
)
public class SolicitudFormulaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date fecha;
    private String observacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idprueba")
    private PruebaFormulaModel prueba;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idmuestra")
    private MuestraModel muestra;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public PruebaFormulaModel getPrueba() {
        return prueba;
    }

    public void setPrueba(PruebaFormulaModel prueba) {
        this.prueba = prueba;
    }

    public MuestraModel getMuestra() {
        return muestra;
    }

    public void setMuestra(MuestraModel muestra) {
        this.muestra = muestra;
    }
}
