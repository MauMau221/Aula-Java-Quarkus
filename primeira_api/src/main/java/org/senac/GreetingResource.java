package org.senac;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

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
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertEntity(MyEntity entity){
        MyEntity.persist(entity);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id") int id){
        MyEntity.deleteById(id);
    }
}
