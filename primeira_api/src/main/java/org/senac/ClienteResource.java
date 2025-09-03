package org.senac;


import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;



@Path("/cliente")
public class ClienteResource {

    @GET
    public Response getAll() {
        return Response.ok(Cliente.listAll()).build();
    }
}
