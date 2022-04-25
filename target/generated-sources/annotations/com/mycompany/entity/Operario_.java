package com.mycompany.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Operario.class)
public abstract class Operario_ {

	public static volatile SingularAttribute<Operario, String> dnioperaio;
	public static volatile SingularAttribute<Operario, Integer> codoperario;
	public static volatile SingularAttribute<Operario, String> apellidosoperario;
	public static volatile ListAttribute<Operario, Registro> registroList;
	public static volatile SingularAttribute<Operario, byte[]> imagenoperario;
	public static volatile SingularAttribute<Operario, String> nombresoperario;
	public static volatile SingularAttribute<Operario, String> puestooperario;
	public static volatile SingularAttribute<Operario, String> plantaoperario;
	public static volatile SingularAttribute<Operario, String> areaoperario;
	public static volatile SingularAttribute<Operario, String> rutaimagenoperario;

}

