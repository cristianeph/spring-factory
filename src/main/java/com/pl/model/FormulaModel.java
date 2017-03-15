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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.pl.model.IngredienteModel;

@Entity
@Table(
	name="formula"
)
public class FormulaModel {
	
	@Transient
	private Integer rows;
	@Transient
	private Integer resume;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String codigo;
	private String nombre;
	private Date fecha;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "formula", orphanRemoval=true, cascade = CascadeType.ALL)
	private Collection<IngredienteModel> formulaIngredientes = new ArrayList<IngredienteModel>();
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="formula")
	
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Collection<IngredienteModel> getFormulaIngredientes() {
		return formulaIngredientes;
	}

	public void setFormulaIngredientes(Collection<IngredienteModel> formulaIngredientes) {
		this.formulaIngredientes = formulaIngredientes;
	}

	public void clearIngredienteModel(){
		for (IngredienteModel ingrediente : formulaIngredientes) {
			ingrediente.releaseFormulaModel();
		}
		formulaIngredientes.clear();
	}
	
}
