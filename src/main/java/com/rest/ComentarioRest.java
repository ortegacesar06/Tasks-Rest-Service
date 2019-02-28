/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest;

import com.models.Comentario;
import com.services.ServicioComentario;
import com.services.ServicioPersona;
import com.services.ServicioTarea;
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
@Path("api/comentario")
public class ComentarioRest {
    private static ServicioPersona sp = new ServicioPersona();
    private static ServicioTarea st = new ServicioTarea();
    private static ServicioComentario sc = new ServicioComentario();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarComentarios(){
        try {
            return Response.ok(sc.todos()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerComentario(@PathParam("id") Long id){
        try {
            sc.fijarComentario(null);
            sc.fijarComentario(sc.obtener(id));
            if(sc.getComentario().getId_comentario() != null){
                return Response.status(Response.Status.OK).entity(sc.getComentario()).build();
            }else{
                return Response.status(Response.Status.NOT_FOUND).build();
            }  
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
      
    }
    
    @GET
    @Path("/tarea/{id}")
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
    public Response guardarComentario(Comentario comentario){
        try {
            st.fijarTarea(st.obtener(comentario.getTarea().getId_tarea()));
            ServicioComentario aux = new ServicioComentario();
            aux.fijarComentario(comentario);
            aux.getComentario().setTarea(st.getTarea());
            if(aux.guardar()){
                return Response.status(Response.Status.CREATED).entity(aux.getComentario()).build();
            }else{
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarComentario(@PathParam("id") Long id, Comentario comentario){
        try {
            sc.fijarComentario(null);
            sc.fijarComentario(sc.obtener(id));
            sc.getComentario().setContenido(comentario.getContenido());

            if(sc.guardar()){
                return Response.status(Response.Status.CREATED).entity(sc.getComentario()).build();
            }else{
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
