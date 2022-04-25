package com.mycompany.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Epp.class)
public abstract class Epp_ {

	public static volatile SingularAttribute<Epp, String> rutaimagenepp;
	public static volatile SingularAttribute<Epp, Date> fechaentrega;
	public static volatile SingularAttribute<Epp, Boolean> conformidad;
	public static volatile SingularAttribute<Epp, byte[]> imagenepp;
	public static volatile SingularAttribute<Epp, Integer> codepp;
	public static volatile SingularAttribute<Epp, Boolean> motivoentrega;
	public static volatile SingularAttribute<Epp, Integer> stockactual;
	public static volatile ListAttribute<Epp, Detalleregistro> detalleregistroList;
	public static volatile SingularAttribute<Epp, String> nombreepp;
	public static volatile SingularAttribute<Epp, String> tallaepp;
	public static volatile SingularAttribute<Epp, String> observacion;

}

