package com.pl.model;

public class PlanTarjetaModel {
	
	private PlanModel plan;
	private OrdenTrabajoModel trabajo;
	
	public PlanModel getPlan() {
		return plan;
	}
	public void setPlan(PlanModel plan) {
		this.plan = plan;
	}
	public OrdenTrabajoModel getTrabajo() {
		return trabajo;
	}
	public void setTrabajo(OrdenTrabajoModel trabajo) {
		this.trabajo = trabajo;
	}
	
}
