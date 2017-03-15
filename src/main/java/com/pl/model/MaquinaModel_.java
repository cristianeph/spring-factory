package com.pl.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(MaquinaModel.class)
public class MaquinaModel_ {

	public static volatile SingularAttribute<MaquinaModel, Integer> id;
	public static volatile SingularAttribute<MaquinaModel, String> descripcion;
	
}
