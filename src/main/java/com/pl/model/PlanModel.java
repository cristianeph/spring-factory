package com.pl.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(
	name="plan"
)
public class PlanModel {
	
	@Transient
	private Integer rows;
	@Transient
	private Integer resume;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String codigo;
	private Date fecha;
	private String estado;
	private Integer solicitud;
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="idpedido")
	private PedidoModel pedido;
	
	/*@OneToMany(fetch=FetchType.EAGER, mappedBy = "plan", orphanRemoval=true, cascade = CascadeType.ALL)
	private Collection<TarjetaHorarioModel> planProducidos = new ArrayList<TarjetaHorarioModel>();*/
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="trabajo")
	
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getResume() {
		return resume;
	}
	public void setResume(Integer resume) {
		this.resume = resume;
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
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getSolicitud() {
		return solicitud;
	}
	public void setSolicitud(Integer solicitud) {
		this.solicitud = solicitud;
	}
	public PedidoModel getPedido() {
		return pedido;
	}
	public void setPedido(PedidoModel pedido) {
		this.pedido = pedido;
	}

	/*public Collection<TarjetaHorarioModel> getPlanProducidos() {
		return planProducidos;
	}
	public void setPlanProducidos(Collection<TarjetaHorarioModel> planProducidos) {
		this.planProducidos = planProducidos;
	}

	public void clearProducidoModel(){
		for (TarjetaHorarioModel producido : planProducidos) {
			producido.releasePlanModel();
		}
		planProducidos.clear();
	}*/

}
