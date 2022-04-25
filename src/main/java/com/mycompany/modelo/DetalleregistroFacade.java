/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.modelo;

import com.mycompany.entity.Detalleregistro;
import com.mycompany.entity.Registro;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Stateless
public class DetalleregistroFacade extends AbstractFacade<Detalleregistro> implements DetalleregistroFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_ControlEpps_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleregistroFacade() {
        super(Detalleregistro.class);
    }

    @Override
    public boolean guardarRegistroDetalleregistro(Detalleregistro detalleregistro) throws Exception {
        em.persist(detalleregistro);
        return true;
    }

    @Override
    public List<Detalleregistro> findAllDetalleRegistroOrderByFecha(int codRegistro) throws Exception {
        System.out.println("registroFacade.findAllDetalleRegistroOrderByFecha 1 :" + codRegistro );
        List<Detalleregistro> listaDetalleRegistroOrderByFecha = new ArrayList<>();
        try {
            String hql = "FROM Detalleregistro WHERE codRegistro = :codRegistro ORDER BY fechaentregaepp DESC";
            Query q = em.createQuery(hql);
            q.setParameter("codRegistro", codRegistro);
            
            listaDetalleRegistroOrderByFecha = q.getResultList();            
            System.out.println("EppFacade.findAllDetalleRegistroOrderByFecha 2 :"  + listaDetalleRegistroOrderByFecha.size());
        } catch (Exception e) {
             e.getStackTrace();
            throw e;
        }
        return listaDetalleRegistroOrderByFecha;
    }

}
