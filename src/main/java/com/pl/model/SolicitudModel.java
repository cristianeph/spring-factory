package com.pl.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(
	name="solicitudinsumo"
)
public class SolicitudModel {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="idinsumo")
	private InsumoModel insumo;
	
	@Column(name="idplan")
	private Integer idPlan;

	private BigDecimal cantidad;

	private String codigo;

	private String estado;

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
