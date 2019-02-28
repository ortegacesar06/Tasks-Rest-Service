/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.services;

import com.daos.ComentarioDao;
import com.models.Comentario;
import java.util.List;

/**
 *
 * @author Potato Power
 */
public class ServicioComentario {
    private ComentarioDao obj = new ComentarioDao();
    
    public Comentario getComentario(){
        return obj.getComentario();
    }
    
    public boolean guardar(){
        return obj.guardar();
    }
    
    public List<Comentario> todos(){
        return obj.listar();
    }
    
    public Comentario obtener(Long id){
        return obj.obtener(id);
    }
    
    public void fijarComentario(Comentario comentario){
        obj.setComentario(comentario);
    }
}
