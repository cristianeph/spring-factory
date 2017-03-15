package com.pl.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ActividadModel.class)
public class ActividadModel_ {

	public static volatile SingularAttribute<ActividadModel, Integer> id;
	public static volatile SingularAttribute<ActividadModel, String> descripcion;
	
}
