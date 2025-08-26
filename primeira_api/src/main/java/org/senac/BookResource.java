package org.senac;

import io.quarkus.panache.common.Sort;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Set;

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
        if (entity == null) {
            return Response.status(404).build();
        }
        return Response.ok(entity).build();
    }

    @GET
    @Path("/search")
    public Response search(
            @QueryParam("q") String q,
            @QueryParam("sort") @DefaultValue("id") String sort,
            @QueryParam("direction") @DefaultValue("asc") String direction,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("10") int size
    ) {
        Set<String> allowed = Set.of("id", "titulo", "autor", "editora", "anoLancamento", "estaDisponivel");
        if (!allowed.contains(sort))
            sort = "id";

        Sort sortObj = Sort.by(
                sort,
                "desc".equalsIgnoreCase(direction) ? Sort.Direction.Descending : Sort.Direction.Ascending
                );

        int effectivePage = page <= 1 ? 0 : page - 1;

        var query = (q == null || q.isBlank()
                ? Book.findAll(sortObj)
                :Book.find("lower(titulo) like ?1 or lower(autor) like ?1",
                sortObj,
                "%" + q.toLowerCase() + "%"));

        var books = query.page(effectivePage, size).list();
        return Response.ok(books).build();
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
        Book entity = Book.findById(id);
        if (entity == null) {
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
