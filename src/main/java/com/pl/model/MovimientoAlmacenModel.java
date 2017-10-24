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

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "movimiento")
    private MovimientoDetalleModel movimientoDetalle;

    private Date fecha;

    private Boolean tipo;

    private BigDecimal cantidad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MovimientoDetalleModel getMovimientoDetalle() {
        return movimientoDetalle;
    }

    public void setMovimientoDetalle(MovimientoDetalleModel movimientoDetalle) {
        this.movimientoDetalle = movimientoDetalle;
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
