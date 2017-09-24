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
	name="tarjetahorario"
)
public class ProducidoModel {
	
	@Transient
	private Integer rows;
	@Transient
	private Integer resume;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idplan")
	private PlanModel plan;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idpersonal")
	private PersonalModel personal;
	
	private BigDecimal horas;
	private BigDecimal total;
	private Integer item;
	
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
	@JsonIgnore
	public PlanModel getPlan() {
		return plan;
	}
	public void setPlan(PlanModel plan) {
		this.plan = plan;
	}
	public PersonalModel getPersonal() {
		return personal;
	}
	public void setPersonal(PersonalModel personal) {
		this.personal = personal;
	}
	public BigDecimal getHoras() {
		return horas;
	}
	public void setHoras(BigDecimal horas) {
		this.horas = horas;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Integer getItem() {
		return item;
	}
	public void setItem(Integer item) {
		this.item = item;
	}
	
	public void releasePlanModel(){
		this.plan = null;
	}

}
