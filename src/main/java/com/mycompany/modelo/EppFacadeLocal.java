/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modelo;

import com.mycompany.entity.Epp;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Local
public interface EppFacadeLocal {

    void create(Epp epp);

    void edit(Epp epp);

    void remove(Epp epp);

    Epp find(Object id);

    List<Epp> findAll();

    List<Epp> findRange(int[] range);

    int count();
    
    //Metodo en el cual obtengo epp por codigo
    Epp obtenerEppPorCodigo(Integer codEpp)throws Exception;

    //Metodo que me trae una lista de Epps ordenados por nombre [A-Z]
    List<Epp> findAllEppOrderByNombre() throws Exception;
}
