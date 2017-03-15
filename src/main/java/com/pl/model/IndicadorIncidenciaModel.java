package com.pl.model;

import java.util.ArrayList;
import java.util.Date;

public class IndicadorIncidenciaModel {
	
	private Date inicio;
	private Date fin;
	private Iterable<TrabajoModel> trabajos = new ArrayList<TrabajoModel>();
	
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
	public Iterable<TrabajoModel> getTrabajos() {
		return trabajos;
	}
	public void setTrabajos(Iterable<TrabajoModel> trabajos) {
		this.trabajos = trabajos;
	}

}
