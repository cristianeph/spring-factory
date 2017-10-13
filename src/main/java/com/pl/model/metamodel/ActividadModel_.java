package com.pl.model.metamodel;

import com.pl.model.ActividadModel;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ActividadModel.class)
public class ActividadModel_ {

	public static volatile SingularAttribute<ActividadModel, Integer> id;
	public static volatile SingularAttribute<ActividadModel, String> descripcion;
	
}
