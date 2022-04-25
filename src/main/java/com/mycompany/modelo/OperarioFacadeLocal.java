/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modelo;

import com.mycompany.entity.Operario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Local
public interface OperarioFacadeLocal {

    void create(Operario operario);

    void edit(Operario operario);

    void remove(Operario operario);

    Operario find(Object id);

    List<Operario> findAll();

    List<Operario> findRange(int[] range);

    int count();
    
    //Metodo para obtener datos del operario ingresando su codigo
    Operario obtenerOperarioPorCodigo(Integer codOperario) throws Exception;

    //Metodo que me trae una lista de Operarios ordenados por apellidos [A-Z]
    List<Operario> findAllOperarioOrderByNombre() throws Exception;
    
}
