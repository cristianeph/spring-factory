package com.pl.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(
	name="ingrediente"
)
public class IngredienteModel {
	
	@Transient
	private Integer rows;
	@Transient
	private Integer resume;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private BigDecimal cantidad;
	private Integer item;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idinsumo")
	private InsumoModel insumo;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idformula")
	private FormulaModel formula;

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

	public BigDecimal getCantidad() {
		return cantidad;
	}

	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	
	public Integer getItem() {
		return item;
	}

	public void setItem(Integer item) {
		this.item = item;
	}

	public InsumoModel getInsumo() {
		return insumo;
	}

	public void setInsumo(InsumoModel insumo) {
		this.insumo = insumo;
	}

	@JsonIgnore
	public FormulaModel getFormula() {
		return formula;
	}

	public void setFormula(FormulaModel formula) {
		this.formula = formula;
	}
	
	public void releaseFormulaModel(){
		this.formula = null;
	}
	
}
