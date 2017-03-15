package com.pl.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TrabajoModel.class)
public class TrabajoModel_ {
	
	public static volatile SingularAttribute<TrabajoModel, Integer> id;
	public static volatile SingularAttribute<TrabajoModel, Integer> horas;
	public static volatile SingularAttribute<TrabajoModel, String> comentarios;

}
