package com.pl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
	name="kardex"
)
public class KardexModel {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(name="idinsumo")
	private Integer idInsumo;
	private Integer relacion;
	private Integer stock;
	
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
	public Integer getRelacion() {
		return relacion;
	}
	public void setRelacion(Integer relacion) {
		this.relacion = relacion;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}	
	
}
