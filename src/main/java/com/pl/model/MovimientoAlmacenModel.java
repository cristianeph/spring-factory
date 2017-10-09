package com.pl.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(
        name="movimientoalmacen"
)
public class MovimientoAlmacenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="idkardex")
    private KardexModel kardex;
    private Date fecha;
    private Boolean tipo;
    private BigDecimal cantidad;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public KardexModel getKardex() {
        return kardex;
    }
    public void setKardex(KardexModel kardex) {
        this.kardex = kardex;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Boolean getTipo() {
        return tipo;
    }
    public void setTipo(Boolean tipo) {
        this.tipo = tipo;
    }
    public BigDecimal getCantidad() {
        return cantidad;
    }
    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
}
