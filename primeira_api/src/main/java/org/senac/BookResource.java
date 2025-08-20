package org.senac;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/books")
public class BookResource {

    @GET
    public Response getAll() {
        return Response.ok(Book.listAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") int id) {
        Book entity = Book.findById(id);
        if(entity == null){
            return Response.status(404).build();
        }
        return Response.ok(entity).build();
    }

    @POST
    @Transactional // Serve para indicar que será feito alguma operação relacinada ao banco de dados
    public Response insert(Book book) {
        Book.persist(book); //insere novo livro
        return Response.status(201).entity(book).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") int id) {
        Book.deleteById(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") int id, Book newbook) {
        Book  entity = Book.findById(id);
        if(entity == null){
            return Response.status(404).build();
        }
        entity.titulo = newbook.titulo;
        entity.autor = newbook.autor;
        entity.editora = newbook.editora;
        entity.anoLancamento = newbook.anoLancamento;
        entity.estaDisponivel = newbook.estaDisponivel;

        return Response.status(200).entity(entity).build();

    }
}
