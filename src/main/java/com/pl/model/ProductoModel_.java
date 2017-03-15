package com.pl.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductoModel.class)
public class ProductoModel_ {

	public static volatile SingularAttribute<ProductoModel, Integer> id;	
	public static volatile SingularAttribute<ProductoModel, String> descripcion;

}
