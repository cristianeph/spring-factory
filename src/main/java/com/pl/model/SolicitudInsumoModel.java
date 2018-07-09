package com.pl.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(
        name = "solicitudinsumo"
)
public class SolicitudInsumoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String codigo;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "solicitud", cascade = CascadeType.ALL)
    private MovimientoDetalleModel movimientoDetalle;

    private Date fecha;

    @Column(name = "idplan")
    private Integer idPlan;

    private BigDecimal cantidad;

    private String estado;

    public SolicitudInsumoModel() {
    }

    public SolicitudInsumoModel(String codigo, Date fecha, Integer idPlan, BigDecimal cantidad, String estado) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.idPlan = idPlan;
        this.cantidad = cantidad;
        this.estado = estado;
    }

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

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
