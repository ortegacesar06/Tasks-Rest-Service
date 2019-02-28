/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daos;

import com.models.Comentario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Potato Power
 */
public class ComentarioDao extends AdaptadorDao<Comentario>{
    private Comentario comentario;

    public ComentarioDao() {
        super(Comentario.class);
    }
    
    public Comentario getComentario() {
        if(comentario == null)
           comentario = new Comentario();
        return comentario;
    }
    
    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }
    
    public boolean guardar (){
        boolean verificar = false;
        try{
            getManager().getTransaction().begin();
            if(comentario.getId_comentario()!=null){
                modificar(comentario);
            }else{
                guardar(comentario);
            }
            getManager().getTransaction().commit();
            verificar = true;
        }catch(Exception e){
            System.out.println("No se ha podido guardar o modificar " + e);
        }
        return verificar;
    }
    
    public Comentario obtenerComentarioPorId(Long id){
        Comentario t = null;
        try {
            Query q = getManager().createQuery("SELECT c FROM Comentario c WHERE c.id_comentario = :id");
            q.setParameter("id", id);
            t = (Comentario)q.getSingleResult();
        } catch (Exception e) {
            System.out.println("ERROR -> Class: ComentarioDao, Meth: obtenerComentarioPorId()");
        }
        return t;
    }
    
    public List<Comentario> listarComentarios(){
        List<Comentario> lista = new ArrayList<>();
        try{
            Query q = getManager()
                    .createQuery("SELECT c FROM Comentario c");
            lista = q.getResultList();
        }catch(Exception e){
            System.out.println("ERROR -> Class: ComentarioDao, Meth: listarComentarios()");
        }
        return lista;
    }
}
