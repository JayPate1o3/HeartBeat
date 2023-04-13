/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.pate0941.heartbeat.service;

import cst8218.pate0941.heartbeat.entity.Heart;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
 * @author Jay Patel
 */
@Stateless
@Path("cst8218.pate0941.heartbeat.heart")
public class HeartFacadeREST extends AbstractFacade<Heart> {

    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    public HeartFacadeREST() {
        super(Heart.class);
    }

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response postRoot(Heart entity) {            // method for POST with an id in the url   
        if (entity.getId() == null){
            //if entity.validate();
            
            //call init method for default intialixzation before post new crete
            entity.defaultInitialization();
            
            super.create(entity);
            
            //else return Response.status(Response.Status.BAD_REQUEST).entity("validation failed").build();
            //return Response.status(Response.Status.CREATED).entity(newEntity).build();
            return Response.status(Response.Status.CREATED).entity(entity).build();
        } 
        else {
            //id is valid and exist in database super.find(entity.getId() != null && id is not null
            Heart oldHeart = super.find(entity.getId());
            
            if(oldHeart != null) {
                //update
                entity.updates(oldHeart);
                return Response.status(Response.Status.OK).entity(oldHeart).build();
            }
            else{
                //return bad request
                return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
            }
              //(entity.getId() != null){
            //Heart oldHeart = super.find(entity.getId())
            //if (oldHeart == null)
            //    return Response.status(Response.Status.NOT_FOUND).build();   //404 not found
            //else entity.updates(oldHeart);
            //}
           // return Response.ok(entity).build();
            
            // return Response.status(Response.Status.OK).build();
        }
    }
    
    @POST
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response postSpecific(@PathParam("id") Long id, Heart entity) {   // method for POST request with id field in it
        //super.edit(entity);
        if (entity.getId() == null){
            entity.setId(id);
        }else if (!entity.getId().equals(id)){
            return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
        }
        Heart oldHeart = super.find(id);  // copying data if id is present inside the poll
        
        if(oldHeart == null)//id does not exists
        {
            //super.create(entity);
            return Response.status(Response.Status.NOT_FOUND).entity(entity).build();
        }
        
        
        entity.updates(oldHeart);
     
        return Response.status(Response.Status.OK).entity(oldHeart).build();
    }
    
    @PUT                                        // PUT method on root source just to throw NOT_ALLOWED error
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response rootEdit(Heart entity) {
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).build(); // update it to NOT_ALLOWED
    }
    
    @PUT
    @Path("{id}")                                            // PUT method on root source with id to edit the data accordingly
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response putOnId(@PathParam("id") Long id, Heart entity) {
        if (entity.getId() == null){
            entity.setId(id);
        }else if (!entity.getId().equals(id)){
            return Response.status(Response.Status.BAD_REQUEST).entity(entity).build();
        }
        Heart oldHeart = super.find(id);  // copying data if id is present inside the poll
        
        if(oldHeart == null)//id does not exists
        {
            //super.create(entity);
            return Response.status(Response.Status.NOT_FOUND).entity(entity).build();
        }
        
        
        //entity.updates(oldHeart);
        entity.defaultInitialization();
        super.edit(entity);
        
        return Response.status(Response.Status.OK).entity(oldHeart).build();
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Heart find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Heart> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Heart> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}