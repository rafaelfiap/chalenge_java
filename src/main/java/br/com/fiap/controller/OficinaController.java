package br.com.fiap.controller;

import br.com.fiap.dtos.OficinaDto;
import br.com.fiap.exceptions.OficinaNotFoundException;
import br.com.fiap.exceptions.OficinaNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Oficina;
import br.com.fiap.services.interfaces.OficinaService;
import br.com.fiap.services.OficinaServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/oficina")
public class OficinaController {

    // Instância de OficinaService obtida da factory
    private final OficinaService oficinaService = OficinaServiceFactory.create();

    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(OficinaDto input) throws UnsupportedServiceOperationException {
        if (input.getIdOficina() == null) {
            try {
                // Criação do objeto Oficina com base no DTO recebido
                Oficina oficina = this.oficinaService.create(
                        new Oficina(
                                null,
                                input.getCnpj(),
                                input.getNome(),
                                input.getEmail()
                        )
                );
                return Response
                        .status(Response.Status.CREATED)
                        .entity(oficina)
                        .build();
            } catch (SQLException | OficinaNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir oficina"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novas oficinas"))
                    .build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(this.oficinaService.findAll()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, OficinaDto input) {
        try {
            // Atualização do objeto Oficina com base no DTO e ID recebidos
            Oficina updated = this.oficinaService.update(
                    new Oficina(
                            id,
                            input.getCnpj(),
                            input.getNome(),
                            input.getEmail()

                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (OficinaNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar oficina"))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.oficinaService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (OficinaNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar oficina"))
                    .build();
        }
    }
}
