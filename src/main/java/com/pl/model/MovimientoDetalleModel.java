package com.pl.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(
        name = "movimientodetalle"
)
public class MovimientoDetalleModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="idinsumo")
    private InsumoModel insumo;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="idsolicitudinsumo", nullable = true)
    private SolicitudInsumoModel solicitud;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="idmovimientoalmacen", nullable = true)
    private MovimientoAlmacenModel movimiento;
    private String tipo;
    private BigDecimal cantidad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public InsumoModel getInsumo() {
        return insumo;
    }

    public void setInsumo(InsumoModel insumo) {
        this.insumo = insumo;
    }

    @JsonIgnore
    public SolicitudInsumoModel getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudInsumoModel solicitud) {
        this.solicitud = solicitud;
    }

    @JsonIgnore
    public MovimientoAlmacenModel getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(MovimientoAlmacenModel movimiento) {
        this.movimiento = movimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
}