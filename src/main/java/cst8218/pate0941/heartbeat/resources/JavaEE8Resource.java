package cst8218.pate0941.heartbeat.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */

@Path("javaee8")
public class JavaEE8Resource {
    
    @GET
    public Response ping(){
        return Response
                .ok("ping")
                .build();
    }
    
//    public Response created()
//    {
//        return Response.status(Response.Status.CREATED).entity(this).build();
//    }
//    
//    public Response okResponse()
//    {
//        return Response.status(Response.Status.OK).entity(this).build();
//        
//    }
//    
//    public Response noContenResponse()
//    {
//        return Response.status(Response.Status.NO_CONTENT).entity(this).build();
//    }
//    
//    public Response badRequestResponse()
//    {
//        return Response.status(Response.Status.BAD_GATEWAY).entity(this).build();
//    }
//    
//    public Response nonResponse()
//    {
//        return Response.status(Response.Status.NOT_FOUND).entity(this).build();
//    }
//    
//    public Response methodNotAllowedResponse()
//    {
//        return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(this).build();
//    }
//    
//    public Response internalServerErrorResponse()
//    {
//        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(this).build();
//    }
    
}
