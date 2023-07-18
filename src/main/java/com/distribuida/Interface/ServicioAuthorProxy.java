package com.distribuida.Interface;

import com.distribuida.DTO.AuthorDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
@Path("/authors")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ServicioAuthorProxy {

    @GET
    public List<AuthorDTO> listarAutor();

    @POST
    public Response crearAutor(AuthorDTO author) ;

    @PUT
    @Path("/{id}")
    public Response actualizarAutor(@PathParam("id") Integer id, AuthorDTO autor);
    @GET
    @Path("/{id}")
    public Response obtenerAutorId(@PathParam("id") Integer id);

    @DELETE
    @Path("/{id}")
    public Response eliminarAutor(@PathParam("id") Integer id) ;

}
