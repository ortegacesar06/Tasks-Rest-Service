/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daos;

import com.models.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Potato Power
 */
public class PersonaDao extends AdaptadorDao<Persona>{
    private Persona persona;
    
    public PersonaDao() {
        super(Persona.class);
    }
    
    public Persona getPersona() {
        if(persona==null)
           persona= new Persona();
        return persona;
    }
    
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    public boolean guardar (){
        boolean verificar = false;
        try{
            getManager().getTransaction().begin();
            if(persona.getId_persona()!= null){
                modificar(persona);
            }else{
                guardar(persona);
            }
            getManager().getTransaction().commit();
            verificar = true;
        }catch(Exception e){
            System.out.println("No se ha podido guardar o modificar " + e);
        }
        return verificar;
    }
    
    public Persona obtenerPersonaPorCedula(String cedula){
        Persona p = null;
        try {
            Query q = getManager().createQuery("SELECT p FROM Persona p WHERE p.dni = :cedula");
            q.setParameter("cedula", cedula);
            p = (Persona)q.getSingleResult();
        } catch (Exception e) {
            System.out.println("ERROR -> Class: PersonaDao, Meth: obtenerPersonaPorCedula()");
        }
        return p;
    }
    
    public List<Persona> listarPersonas(){
        List<Persona> lista = new ArrayList<>();
        try{
            Query q = getManager()
                    .createQuery("SELECT p FROM Persona p");
            lista = q.getResultList();
        }catch(Exception e){
            System.out.println("ERROR -> Class: PersonaDao, Meth: listarSinAdministrador()");
        }
        return lista;
    }
}
