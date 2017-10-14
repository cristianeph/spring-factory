package com.pl.model.metamodel;

import com.pl.model.InsumoModel;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(InsumoModel.class)
public class InsumoModel_ {
	
	public static volatile SingularAttribute<InsumoModel, Integer> id;
	public static volatile SingularAttribute<InsumoModel, String> descripcion;

}
