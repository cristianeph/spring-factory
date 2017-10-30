package com.pl.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(
	name="solicitudinsumo"
)
public class SolicitudInsumoModel {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "solicitud", cascade = CascadeType.ALL)
	private MovimientoDetalleModel movimientoDetalle;
	
	@Column(name="idplan")
	private Integer idPlan;

	private BigDecimal cantidad;

	private String codigo;

	private String estado;

    public SolicitudInsumoModel() {
    }

    public SolicitudInsumoModel(Integer idPlan, BigDecimal cantidad, String codigo, String estado) {
        this.idPlan = idPlan;
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.estado = estado;
    }

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
}
