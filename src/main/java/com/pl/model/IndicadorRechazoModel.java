package com.pl.model;

import java.util.ArrayList;
import java.util.Date;

public class IndicadorRechazoModel {
	
	private Date inicio;
	private Date fin;
	private Iterable<PlanModel> planes = new ArrayList<PlanModel>();
	
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
	public Iterable<PlanModel> getPlanes() {
		return planes;
	}
	public void setPlanes(Iterable<PlanModel> planes) {
		this.planes = planes;
	}

}
