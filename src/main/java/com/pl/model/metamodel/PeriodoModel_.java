package com.pl.model.metamodel;

import com.pl.model.PeriodoModel;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(PeriodoModel.class)
public class PeriodoModel_ {

	public static volatile SingularAttribute<PeriodoModel, Integer> id;
	public static volatile SingularAttribute<PeriodoModel, String> ano;
	public static volatile SingularAttribute<PeriodoModel, String> mes;
	public static volatile SingularAttribute<PeriodoModel, String> estado;
	
}
