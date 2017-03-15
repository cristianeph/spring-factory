package com.pl.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(PedidoModel.class)
public class PedidoModel_ {

	public static volatile SingularAttribute<PedidoModel, Integer> id;
	public static volatile SingularAttribute<PedidoModel, String> codigo;
	
}
