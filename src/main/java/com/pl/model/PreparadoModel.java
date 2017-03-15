package com.pl.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(
	name="preparado"
)
public class PreparadoModel {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(name="idinsumo")
	private Integer idInsumo;
	private String descripcion;
	private BigDecimal cantidad;
	private BigDecimal costo;
	@Column(name="costototal")
	private BigDecimal costoTotal;
	private Integer item;
	private Integer relacion;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idpedido")
	private PedidoModel pedido;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdInsumo() {
		return idInsumo;
	}
	public void setIdInsumo(Integer idInsumo) {
		this.idInsumo = idInsumo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getCantidad() {
		return cantidad;
	}
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	public BigDecimal getCosto() {
		return costo;
	}
	public void setCosto(BigDecimal costo) {
		this.costo = costo;
	}
	public BigDecimal getCostoTotal() {
		return costoTotal;
	}
	public void setCostoTotal(BigDecimal costoTotal) {
		this.costoTotal = costoTotal;
	}
	public Integer getItem() {
		return item;
	}
	public void setItem(Integer item) {
		this.item = item;
	}
	
	public Integer getRelacion() {
		return relacion;
	}
	public void setRelacion(Integer relacion) {
		this.relacion = relacion;
	}
	
	@JsonIgnore
	public PedidoModel getPedido() {
		return pedido;
	}
	public void setPedido(PedidoModel pedido) {
		this.pedido = pedido;
	}
	
	public void releasePedidoModel(){
		this.pedido = null;
	}
	
}
