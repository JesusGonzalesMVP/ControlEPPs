/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modelo;

import com.mycompany.entity.Registro;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Local
public interface RegistroFacadeLocal {

    void create(Registro registro);

    void edit(Registro registro);

    void remove(Registro registro);

    Registro find(Object id);

    List<Registro> findAll();

    List<Registro> findRange(int[] range);

    int count();
    
    //Obtener el ultimo registro en la tabla RegistroEPP de la BD
    Registro obtenerUltimoRegistro()throws Exception;

    //Averiguar si la tabla RegistroEpp posee registros
    Long obtenerTotalRegistros();

    //Metodo para guardar el resgitro en la tabla RegistroEpp en la BD
    boolean guardarRegistro(Registro registro)throws Exception;
    
    //Metodo que me trae una lista de Registros ordenados por Fecha DESC
    List<Registro> findAllRegistroOrderByFecha() throws Exception;
}
