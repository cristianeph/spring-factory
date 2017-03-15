package com.pl.model;

import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(
	name="pedido"
)
public class PedidoModel {
	
	@Transient
	private Integer rows;
	@Transient
	private Integer resume;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String codigo;
	private Date fecha;
	private BigDecimal cantidad;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idproducto")
	private ProductoModel producto;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idcliente")
	private ClienteModel cliente;
	
	@OneToOne(fetch=FetchType.EAGER, mappedBy="pedido", cascade = CascadeType.ALL)
	private PlanModel plan;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido", orphanRemoval=true, cascade = CascadeType.ALL)
	private Collection<PreparadoModel> pedidoPreparados = new ArrayList<PreparadoModel>();
	
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
	public BigDecimal getCantidad() {
		return cantidad;
	}
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	public ProductoModel getProducto() {
		return producto;
	}
	public void setProducto(ProductoModel producto) {
		this.producto = producto;
	}
	public ClienteModel getCliente() {
		return cliente;
	}
	public void setCliente(ClienteModel cliente) {
		this.cliente = cliente;
	}
	@JsonIgnore
	public PlanModel getPlan() {
		return plan;
	}
	public void setPlan(PlanModel plan) {
		this.plan = plan;
	}
	public Collection<PreparadoModel> getPedidoPreparados() {
		return pedidoPreparados;
	}
	public void setPedidoPreparados(Collection<PreparadoModel> pedidoPreparados) {
		this.pedidoPreparados = pedidoPreparados;
	}
	
	public void clearPreparadoModel(){
		for (PreparadoModel preparado : pedidoPreparados) {
			preparado.releasePedidoModel();
		}
		pedidoPreparados.clear();
	}

}
