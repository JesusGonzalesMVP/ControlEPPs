/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.modelo;

import com.mycompany.entity.Usuario;
import com.mycompany.utilejb.EncriptarPaswword;
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
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "com.mycompany_ControlEpps_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario obtenerDatosPorUsuario(Usuario usuario) {
        Usuario usuarioEncontrado = null;
        
        try {
            
            String hql = "FROM Usuario WHERE nombreUsuario = :nombreUsuario";
            Query q = em.createQuery(hql);
            q.setParameter("nombreUsuario", usuario.getNombreusuario());
            
            List<Usuario> listaUsuarios = q.getResultList();
            if(!listaUsuarios.isEmpty()){
                usuarioEncontrado = listaUsuarios.get(0);
            }
            
        } catch (Exception e) {
            throw e;
        }
        
        return usuarioEncontrado;
    }

@Override
    public Usuario login(Usuario usuario) {
        System.out.println("usuarioFacade.login 0: ");
        Usuario user = this.obtenerDatosPorUsuario(usuario);
        System.out.println("usuarioFacade.login 1: ");
        if(user != null){
            System.out.println("usuarioFacade.login 2: ");
            if(!user.getPasswordusuario().equals(EncriptarPaswword.sha512(usuario.getPasswordusuario()))){
                System.out.println("usuarioFacade.login 3: ");
                user=null;
            }
        }
        return user;
    }

}
