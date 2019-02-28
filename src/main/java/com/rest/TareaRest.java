/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest;

import com.models.Tarea;
import com.services.ServicioPersona;
import com.services.ServicioTarea;
import java.util.Date;
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
@Path("api/tarea")
public class TareaRest {
    private static ServicioPersona sp = new ServicioPersona();
    private static ServicioTarea st = new ServicioTarea();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarTareas(){
        try {
            return Response.ok(st.todos()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTarea(@PathParam("id") Long id){
        try {
            st.fijarTarea(null);
            st.fijarTarea(st.obtener(id));
            if (st.getTarea().getId_tarea() != null) {
                return Response.status(Response.Status.OK).entity(st.getTarea()).build();
            }else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }            
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/comentarios/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerComentariosPorTarea(@PathParam("id") Long id){
        try {
            st.fijarTarea(null);
            st.fijarTarea(st.obtener(id));
            if (st.getTarea().getId_tarea() != null) {
                return Response.status(Response.Status.OK).entity(st.getTarea().getComentarios()).build();
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
    public Response guardarTarea(Tarea tarea){
        try {
            sp.fijarPersona(sp.obtener(tarea.getPersona().getId_persona()));
            ServicioTarea aux = new ServicioTarea();
            aux.fijarTarea(tarea);
            aux.getTarea().setPersona(sp.getPersona());
            if(aux.guardar()){
                return Response.status(Response.Status.CREATED).entity(aux.getTarea()).build();
            }else{
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarTarea(Tarea tarea){
        try {
            st.fijarTarea(null);
            st.fijarTarea(st.obtener(tarea.getId_tarea()));

            st.getTarea().setTitulo(tarea.getTitulo());
            st.getTarea().setDescripcion(tarea.getDescripcion());
            st.getTarea().setEstado(tarea.getEstado());
            st.getTarea().setUpdated_at(new Date());

            if(st.guardar()){
                return Response.status(Response.Status.CREATED).entity(st.getTarea()).build();
            }else{
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
