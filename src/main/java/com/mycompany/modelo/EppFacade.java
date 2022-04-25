/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.modelo;

import com.mycompany.entity.Epp;
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
public class EppFacade extends AbstractFacade<Epp> implements EppFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_ControlEpps_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EppFacade() {
        super(Epp.class);
    }
    
    @Override
    public Epp obtenerEppPorCodigo(Integer codEpp) throws Exception {
        System.out.println("EppFacade.obtenerEppPorCodigo 1 :" + codEpp);
        Epp epp = null;
        try {
            String hql = "FROM Epp WHERE codepp = :codEpp";        
            Query q = em.createQuery(hql);
            q.setParameter("codEpp", codEpp);
            
            List<Epp> listaEpps = q.getResultList();
            if(!listaEpps.isEmpty()){
                epp = listaEpps.get(0);
                System.out.println("EppFacade.obtenerEppPorCodigo 2 :" + epp.getNombreepp());
            }   
        
        } catch (Exception e) {
            System.out.println("::::::::::" );
            e.getStackTrace();
            throw e;
        }
        
        System.out.println("EppFacade.obtenerEppPorCodigo 3:" + epp);
        return epp;
    }

@Override
    public List<Epp> findAllEppOrderByNombre() throws Exception {
        System.out.println("EppFacade.findAllEppOrderByNombre 1 :" );
        List<Epp> listaEppOrderByNombre = new ArrayList<>();
        try {
            String hql = "FROM Epp ORDER BY nombreEpp ASC";
            Query q = em.createQuery(hql);
            listaEppOrderByNombre = q.getResultList();            
            System.out.println("EppFacade.findAllEppOrderByNombre 2 :"  + listaEppOrderByNombre.size());
        } catch (Exception e) {
             e.getStackTrace();
            throw e;
        }
        return listaEppOrderByNombre;
    }

}
