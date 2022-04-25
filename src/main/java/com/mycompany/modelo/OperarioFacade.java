/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modelo;

import com.mycompany.entity.Operario;
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
public class OperarioFacade extends AbstractFacade<Operario> implements OperarioFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_ControlEpps_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OperarioFacade() {
        super(Operario.class);
    }

    @Override
    public Operario obtenerOperarioPorCodigo(Integer codOperario) throws Exception {
        Operario operario = null;
        try {
            String hql = "FROM Operario WHERE codOperario = :codOperario";
            Query q = em.createQuery(hql);
            q.setParameter("codOperario", codOperario);

            List<Operario> lista = q.getResultList();
            if (!lista.isEmpty()) {
                operario = lista.get(0);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return operario;
    }

    @Override
    public List<Operario> findAllOperarioOrderByNombre() throws Exception {
        System.out.println("OperarioFacade.findAllOperarioOrderByNombre 1 :");
        List<Operario> listaOperarioOrderByNombre = new ArrayList<>();
        try {
            String hql = "FROM Operario ORDER BY apellidosOperario ASC";
            Query q = em.createQuery(hql);
            listaOperarioOrderByNombre = q.getResultList();
            System.out.println("OperarioFacade.findAllOperarioOrderByNombre 2 :" + listaOperarioOrderByNombre.size());
        } catch (Exception e) {
            e.getStackTrace();
            throw e;
        }
        return listaOperarioOrderByNombre;
    }
}
