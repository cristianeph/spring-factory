package com.pl.model;

public class KardexRelacionModel {

	private Long cantidad;
	private Integer relacion;
	
	
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	public Integer getRelacion() {
		return relacion;
	}
	public void setRelacion(Integer relacion) {
		this.relacion = relacion;
	}
	
	public KardexRelacionModel(Long cantidad, Integer relacion) {
		
		this.cantidad = cantidad;
		this.relacion = relacion;
		
	}

}
