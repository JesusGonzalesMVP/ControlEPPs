package com.mycompany.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Detalleregistro.class)
public abstract class Detalleregistro_ {

	public static volatile SingularAttribute<Detalleregistro, Integer> cantidadentregaepp;
	public static volatile SingularAttribute<Detalleregistro, Boolean> conformidad;
	public static volatile SingularAttribute<Detalleregistro, Integer> coddetalleregistro;
	public static volatile SingularAttribute<Detalleregistro, Date> fechaentregaepp;
	public static volatile SingularAttribute<Detalleregistro, Epp> codepp;
	public static volatile SingularAttribute<Detalleregistro, Boolean> motivoentrega;
	public static volatile SingularAttribute<Detalleregistro, Registro> codregistro;
	public static volatile SingularAttribute<Detalleregistro, String> observacion;
	public static volatile SingularAttribute<Detalleregistro, String> nombreepp;
	public static volatile SingularAttribute<Detalleregistro, String> tallaepp;

}

