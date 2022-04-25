package com.mycompany.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Persona.class)
public abstract class Persona_ {

	public static volatile SingularAttribute<Persona, String> direccionpersona;
	public static volatile ListAttribute<Persona, Usuario> usuarioList;
	public static volatile SingularAttribute<Persona, String> nombrespersona;
	public static volatile SingularAttribute<Persona, String> celularpersona;
	public static volatile SingularAttribute<Persona, String> apellidospersona;
	public static volatile SingularAttribute<Persona, Integer> codpersona;
	public static volatile SingularAttribute<Persona, String> dnipersona;

}

