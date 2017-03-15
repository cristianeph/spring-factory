package com.pl.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(PeriodoModel.class)
public class PeriodoModel_ {

	public static volatile SingularAttribute<PeriodoModel, Integer> id;
	public static volatile SingularAttribute<PeriodoModel, String> ano;
	public static volatile SingularAttribute<PeriodoModel, String> mes;
	public static volatile SingularAttribute<PeriodoModel, String> estado;
	
}
