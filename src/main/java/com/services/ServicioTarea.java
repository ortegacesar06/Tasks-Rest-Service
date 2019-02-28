/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.daos.TareaDao;
import com.models.Tarea;
import java.util.List;

/**
 *
 * @author Potato Power
 */
public class ServicioTarea {
    private TareaDao obj = new TareaDao();
    
    public Tarea getTarea(){
        return obj.getTarea();
    }
    
    public boolean guardar(){
        return obj.guardar();
    }
    
    public List<Tarea> todos(){
        return obj.listar();
    }
    
    public Tarea obtener(Long id){
        return obj.obtener(id);
    }
    
    public void fijarTarea(Tarea tarea){
        obj.setTarea(tarea);
    }
}
