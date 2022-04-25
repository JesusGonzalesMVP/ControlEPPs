package com.mycompany.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Registro.class)
public abstract class Registro_ {

	public static volatile SingularAttribute<Registro, String> formatoregistro;
	public static volatile SingularAttribute<Registro, String> nombreproyecto;
	public static volatile SingularAttribute<Registro, String> ubicacion;
	public static volatile SingularAttribute<Registro, String> numeroregistro;
	public static volatile SingularAttribute<Registro, Integer> numerotrabajadores;
	public static volatile SingularAttribute<Registro, Integer> codregistro;
	public static volatile SingularAttribute<Registro, Usuario> codusuario;
	public static volatile SingularAttribute<Registro, Date> fecharegistro;
	public static volatile ListAttribute<Registro, Detalleregistro> detalleregistroList;
	public static volatile SingularAttribute<Registro, Boolean> aprobadoregistro;
	public static volatile SingularAttribute<Registro, Operario> codoperario;
	public static volatile SingularAttribute<Registro, Boolean> revisadoregistro;
	public static volatile SingularAttribute<Registro, String> plantaoperario;
	public static volatile SingularAttribute<Registro, String> nombreproyectooperario;
	public static volatile SingularAttribute<Registro, Boolean> entregadoregistro;

}

