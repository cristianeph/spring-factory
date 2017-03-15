package com.pl.model;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(PlanModel.class)
public class PlanModel_ {

	public static volatile SingularAttribute<PlanModel, Integer> id;
	public static volatile SingularAttribute<PlanModel, String> codigo;
	public static volatile SingularAttribute<PlanModel, Date> fecha;
	public static volatile SingularAttribute<PlanModel, String> estado;
	
}
