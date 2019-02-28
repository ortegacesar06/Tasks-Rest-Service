/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daos;

import com.models.Tarea;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Potato Power
 */
public class TareaDao extends AdaptadorDao<Tarea>{
    private Tarea tarea;

    public TareaDao() {
        super(Tarea.class);
    }
    
    public Tarea getTarea() {
        if(tarea==null)
           tarea = new Tarea();
        return tarea;
    }
    
    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }
    
    public boolean guardar (){
        boolean verificar = false;
        try{
            getManager().getTransaction().begin();
            if(tarea.getId_tarea()!=null){
                modificar(tarea);
            }else{
                guardar(tarea);
            }
            getManager().getTransaction().commit();
            verificar = true;
        }catch(Exception e){
            System.out.println("No se ha podido guardar o modificar " + e);
        }
        return verificar;
    }
    
    public Tarea obtenerTareaPorId(Long id){
        Tarea t = null;
        try {
            Query q = getManager().createQuery("SELECT t FROM Tarea t WHERE t.id_tarea = :id");
            q.setParameter("id", id);
            t = (Tarea)q.getSingleResult();
        } catch (Exception e) {
            System.out.println("ERROR -> Class: TareaDao, Meth: obtenerTareaPorId()");
        }
        return t;
    }
    
    public List<Tarea> listarTareas(){
        List<Tarea> lista = new ArrayList<>();
        try{
            Query q = getManager()
                    .createQuery("SELECT t FROM Tarea t");
            lista = q.getResultList();
        }catch(Exception e){
            System.out.println("ERROR -> Class: TareaDao, Meth: listarTareas()");
        }
        return lista;
    }
}
