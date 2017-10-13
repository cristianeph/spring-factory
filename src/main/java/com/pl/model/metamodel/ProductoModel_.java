package com.pl.model.metamodel;

import com.pl.model.ProductoModel;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ProductoModel.class)
public class ProductoModel_ {

	public static volatile SingularAttribute<ProductoModel, Integer> id;
	public static volatile SingularAttribute<ProductoModel, String> descripcion;

}
