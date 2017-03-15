package com.pl.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ClienteModel.class)
public class ClienteModel_ {

	public static volatile SingularAttribute<ClienteModel, Integer> id;
	public static volatile SingularAttribute<ClienteModel, String> razonsocial;
	public static volatile SingularAttribute<ClienteModel, String> direccion;
	public static volatile SingularAttribute<ClienteModel, String> ruc;
	
}
