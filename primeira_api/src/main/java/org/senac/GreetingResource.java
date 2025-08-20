package org.senac;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.awt.*;
import java.util.List;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MyEntity hello() {
        return new MyEntity("Hello from Quarkus REST");
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)

    public List<MyEntity> getAll() {
        return MyEntity.listAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MyEntity getById(@PathParam("id") int id) {
        return MyEntity.findById(id);
    }

    @POST
    @Transactional // Serve para indicar que será feito alguma operação relacinada ao banco de dados
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertEntity(MyEntity entity){
        MyEntity.persist(entity);
    }

    @DELETE
    @Path("{id}")
    @Transactional // Serve para indicar que será feito alguma operação relacinada ao banco de dados
    public void delete(@PathParam("id") int id){
        MyEntity.deleteById(id);
    }

    @PUT
    @Path("{id}")
    @Transactional // Serve para indicar que será feito alguma operação relacinada ao banco de dados
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEntity(MyEntity entity, @PathParam("id") int id) {
        MyEntity entity1 = MyEntity.findById(id);
        if(entity1 == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(entity1).build();
    }
}
