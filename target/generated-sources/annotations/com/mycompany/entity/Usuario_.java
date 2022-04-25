package com.mycompany.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, String> nombreusuario;
	public static volatile ListAttribute<Usuario, Registro> registroList;
	public static volatile SingularAttribute<Usuario, Integer> codusuario;
	public static volatile SingularAttribute<Usuario, Persona> codpersona;
	public static volatile SingularAttribute<Usuario, String> passwordusuario;

}

