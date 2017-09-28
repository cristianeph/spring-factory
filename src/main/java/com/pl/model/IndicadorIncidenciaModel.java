package com.pl.model;

import java.util.ArrayList;
import java.util.Date;

public class IndicadorIncidenciaModel {
	
	private Date inicio;
	private Date fin;
	private Iterable<OrdenTrabajoModel> trabajos = new ArrayList<OrdenTrabajoModel>();
	
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
	public Iterable<OrdenTrabajoModel> getTrabajos() {
		return trabajos;
	}
	public void setTrabajos(Iterable<OrdenTrabajoModel> trabajos) {
		this.trabajos = trabajos;
	}

}
