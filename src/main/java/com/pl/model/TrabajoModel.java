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
	name="trabajo"
)
public class TrabajoModel {
	
	@Transient
	private Integer rows;
	@Transient
	private Integer resume;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idtarjeta")
	private ParteProduccionModel tarjeta;
	
	/*@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idmaquina")
	private MaquinaModel maquina;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idactividad")
	private ActividadModel actividad;*/
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idplan")
	private PlanModel plan;
	
	private Integer item;
	private Integer horas;
	private BigDecimal cantidad;
	private BigDecimal merma;
	private String comentarios;
	private String incidencias;
	
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
	public ParteProduccionModel getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(ParteProduccionModel tarjeta) {
		this.tarjeta = tarjeta;
	}
	public PlanModel getPlan() {
		return plan;
	}
	public void setPlan(PlanModel plan) {
		this.plan = plan;
	}
	public Integer getItem() {
		return item;
	}
	public void setItem(Integer item) {
		this.item = item;
	}
	public Integer getHoras() {
		return horas;
	}
	public void setHoras(Integer horas) {
		this.horas = horas;
	}
	public BigDecimal getCantidad() {
		return cantidad;
	}
	public void setCantidad(BigDecimal cantidad) {
		this.cantidad = cantidad;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public void releaseTrabajoModel(){
		this.tarjeta = null;
	}
	public BigDecimal getMerma() {
		return merma;
	}
	public void setMerma(BigDecimal merma) {
		this.merma = merma;
	}
	public String getIncidencias() {
		return incidencias;
	}
	public void setIncidencias(String incidencias) {
		this.incidencias = incidencias;
	}
	
}
