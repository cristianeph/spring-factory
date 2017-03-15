package com.pl.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class CostoModel {
	
	private Date inicio;
	private Date fin;
	private Collection<PlanTarjetaModel> planTarjetas = new ArrayList<PlanTarjetaModel>();
	
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}
	public Collection<PlanTarjetaModel> getPlanTarjetas() {
		return planTarjetas;
	}
	public void setPlanTarjetas(Collection<PlanTarjetaModel> planTarjetas) {
		this.planTarjetas = planTarjetas;
	}
	
}
