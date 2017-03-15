package com.pl.model;

public class KardexInsumoModel {
	
	private Integer id;
	private String descripcion;
	private Long stock;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}
	public KardexInsumoModel(Integer id, String descripcion, Long stock) {
		
		this.id = id;
		this.descripcion = descripcion;
		this.stock = stock;
	}

}
