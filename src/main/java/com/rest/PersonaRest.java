/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest;

import com.models.Persona;
import com.services.ServicioPersona;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Potato Power
 */
@Path("api/persona")
public class PersonaRest {
    private static ServicioPersona sp = new ServicioPersona();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarPersonas(){
        try {
            return Response.ok(sp.todos()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPersona(@PathParam("id") Long id){
        try {
            sp.fijarPersona(null);
            sp.fijarPersona(sp.obtener(id));
            if (sp.getPersona().getId_persona() != null) {
                return Response.status(Response.Status.OK).entity(sp.getPersona()).build();
            }else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }        
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response guardarPersona(Persona persona){
        try {
            ServicioPersona aux = new ServicioPersona();
            aux.fijarPersona(persona);
            if(aux.guardar()){
                return Response.status(Response.Status.CREATED).entity(aux.getPersona()).build();
            }else{
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarPersona(@PathParam("id") Long id, Persona persona){
        try {
            sp.fijarPersona(null);
            sp.fijarPersona(sp.obtener(id));
            sp.getPersona().setDni(persona.getDni());
            sp.getPersona().setNombres(persona.getNombres());
            sp.getPersona().setApellidos(persona.getApellidos());
            sp.getPersona().setPais(persona.getPais());
            sp.getPersona().setCiudad(persona.getCiudad());
            sp.getPersona().setSexo(persona.getSexo());
            sp.getPersona().setTelefono(persona.getTelefono());

            if(sp.guardar()){
                return Response.status(Response.Status.OK).entity(sp.getPersona()).build();
            }else{
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
