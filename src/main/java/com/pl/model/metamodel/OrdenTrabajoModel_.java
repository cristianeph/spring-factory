package com.pl.model.metamodel;

import com.pl.model.OrdenTrabajoModel;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OrdenTrabajoModel.class)
public class OrdenTrabajoModel_ {
	
	public static volatile SingularAttribute<OrdenTrabajoModel, Integer> id;
	public static volatile SingularAttribute<OrdenTrabajoModel, Integer> horas;
	public static volatile SingularAttribute<OrdenTrabajoModel, String> comentarios;

}
