package com.pl.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class IndicadorAvanceModel {
	
	private Date inicio;
	private Date fin;
	private Collection<PedidoPlanTrabajoModel> pedidoPlanes = new ArrayList<PedidoPlanTrabajoModel>();
	
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
	public Collection<PedidoPlanTrabajoModel> getPedidoPlanes() {
		return pedidoPlanes;
	}
	public void setPedidoPlanes(Collection<PedidoPlanTrabajoModel> pedidoPlanes) {
		this.pedidoPlanes = pedidoPlanes;
	}
	
}