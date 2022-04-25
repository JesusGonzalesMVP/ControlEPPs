/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.modelo;

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
public class RegistroFacade extends AbstractFacade<Registro> implements RegistroFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_ControlEpps_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegistroFacade() {
        super(Registro.class);
    }
    
    @Override
    public Registro obtenerUltimoRegistro() throws Exception {
        Registro registro = null;
        try {
            String hql = "FROM Registro ORDER BY codRegistro DESC";
            Query q = em.createQuery(hql).setMaxResults(1);
            
            List<Registro> listaRegistros = q.getResultList();            
            if(!listaRegistros.isEmpty()){
                registro = listaRegistros.get(0);
            }           
        } catch (Exception e) {
            throw e;
        }
        
        return registro;
    }

    @Override
    public Long obtenerTotalRegistros() {
        Long number = null;
        System.out.println("registroFacade.obtenerTotalRegistros 1: ");
        try {
            String hql = "SELECT COUNT(*) FROM Registro";
            Query q = em.createQuery(hql);
            List<Registro> listaRegistros = q.getResultList();
            if(!listaRegistros.isEmpty()){
                number = Long.parseLong(Integer.toString(listaRegistros.size()));
            }
        
            System.out.println("registroFacade.obtenerTotalRegistros 2: " + number);
        } catch (NumberFormatException e) {
            throw e;
        }
        System.out.println("registroFacade.obtenerTotalRegistrosa 3: " + number);
        return number;
    }

    @Override
    public boolean guardarRegistro(Registro registro) throws Exception {
        System.out.println("registroFacade.guardarRegistro 1: " + registro);
        try {
            System.out.println("registroFacade.guardarRegistro 2: ");
            em.persist(registro);
            System.out.println("registroFacade.guardarRegistro 3: ");
        } catch (Exception e) {
            System.out.println("registroFacade.guardarRegistro Error: " );
            throw e;
        }
        return true;
    }

    @Override
    public List<Registro> findAllRegistroOrderByFecha() throws Exception {
        System.out.println("registroFacade.findAllRegistroOrderByFecha 1 :" );
        List<Registro> listaRegistroOrderByFecha = new ArrayList<>();
        try {
            String hql = "FROM Registro ORDER BY fechaRegistro DESC";
            Query q = em.createQuery(hql);
            listaRegistroOrderByFecha = q.getResultList();            
            System.out.println("EppFacade.findAllRegistroOrderByFecha 2 :"  + listaRegistroOrderByFecha.size());
        } catch (Exception e) {
             e.getStackTrace();
            throw e;
        }
        return listaRegistroOrderByFecha;
    }

}
