package com.distribuida.rest;

import com.distribuida.db.Book;
import com.distribuida.repo.BookRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Transactional
public class BookRest {
    @Inject
    BookRepository repo;


    @GET
    public List<Book> listarLibros() {
        return repo.listAll();
    }

    @POST
    public Response crearLibros(Book libro) {
        repo.persist(libro);
        return Response.status(Response.Status.CREATED)
                .entity("Libro creado exitosamente")
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizarLibro(@PathParam("id") Long id, Book libro) {
        Book libroExistente = repo.findById(id);

        if (libroExistente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Libro no encontrado")
                    .build();
        }
        libroExistente.setAuthor(libro.getAuthor());
        libroExistente.setIsbn(libro.getIsbn());
        libroExistente.setTitle(libro.getTitle());
        libroExistente.setPrice(libro.getPrice());


        libroExistente.persist();

        return Response.status(Response.Status.OK)
                .entity("Libro actualizado exitosamente")
                .build();
    }
    @GET
    @Path("/{id}")
    public Response obtenerLibroPorId(@PathParam("id") Long id) {

        var libro = repo.findById(id);

        if (libro == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("libro no encontrado")
                    .build();
        }

        return Response.status(Response.Status.OK)
                .entity(libro)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarLibro(@PathParam("id") Long id) {
        Book libro = repo.findById(id);
        if (libro == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("libro no encontrado")
                    .build();
        }
        libro.delete();
        return Response.status(Response.Status.OK)
                .entity("libro borrado exitosamente")
                .build();

    }

}
