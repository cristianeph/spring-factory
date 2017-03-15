package com.pl.model;

public class PedidoPlanTrabajoModel {
	
	private PedidoModel pedido;
	private PlanModel plan;
	private TrabajoModel trabajo;
	
	public PedidoModel getPedido() {
		return pedido;
	}
	public void setPedido(PedidoModel pedido) {
		this.pedido = pedido;
	}
	public PlanModel getPlan() {
		return plan;
	}
	public void setPlan(PlanModel plan) {
		this.plan = plan;
	}
	public TrabajoModel getTrabajo() {
		return trabajo;
	}
	public void setTrabajo(TrabajoModel trabajo) {
		this.trabajo = trabajo;
	}
	
	

}
