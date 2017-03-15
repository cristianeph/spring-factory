package com.pl.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(PersonalModel.class)
public class PersonalModel_ {

	public static volatile SingularAttribute<PersonalModel, Integer> id;
	public static volatile SingularAttribute<PersonalModel, String> codigo;
	public static volatile SingularAttribute<PersonalModel, String> nombres;
	public static volatile SingularAttribute<PersonalModel, String> apellidos;
	
}
