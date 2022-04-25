/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.modelo;

import com.mycompany.entity.Detalleregistro;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
@Local
public interface DetalleregistroFacadeLocal {

    void create(Detalleregistro detalleregistro);

    void edit(Detalleregistro detalleregistro);

    void remove(Detalleregistro detalleregistro);

    Detalleregistro find(Object id);

    List<Detalleregistro> findAll();

    List<Detalleregistro> findRange(int[] range);

    int count();
    
    boolean guardarRegistroDetalleregistro(Detalleregistro detalleregistro)throws Exception;
    
    //Metodo que me trae una lista de Registros ordenados por Fecha DESC
    List<Detalleregistro> findAllDetalleRegistroOrderByFecha(int codRegistro) throws Exception;
    
}
