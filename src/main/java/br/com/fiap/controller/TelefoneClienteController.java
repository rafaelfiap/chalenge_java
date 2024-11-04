package br.com.fiap.controller;


import br.com.fiap.dtos.TelefoneDto;
import br.com.fiap.exceptions.TelefoneNotFoundException;
import br.com.fiap.exceptions.TelefoneNotSavedException;
import br.com.fiap.models.Telefone;
import br.com.fiap.services.interfaces.TelefoneService;
import br.com.fiap.services.TelefoneClienteServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/telefone-cliente")
public class TelefoneClienteController {

    // Instância de TelefoneService obtida da factory para Telefone de Cliente
    private final TelefoneService telefoneService = TelefoneClienteServiceFactory.create();

    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(TelefoneDto input) {
        if (input.getIdTelefone() == null) {
            try {
                // Criação do objeto Telefone com base no DTO recebido
                Telefone telefone = this.telefoneService.create(
                        new Telefone(
                                null,
                                input.getNumero(),
                                input.getTipo(),
                                input.getIdReferencia()
                        )
                );
                return Response
                        .status(Response.Status.CREATED)
                        .entity(telefone)
                        .build();
            } catch (SQLException | TelefoneNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir telefone"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novos telefones"))
                    .build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(this.telefoneService.findAll()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, TelefoneDto input) {
        try {
            // Atualização do objeto Telefone com base no DTO e ID recebidos
            Telefone updated = this.telefoneService.update(
                    new Telefone(
                            id,
                            input.getNumero(),
                            input.getTipo(),
                            input.getIdReferencia()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (TelefoneNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar telefone"))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.telefoneService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (TelefoneNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar telefone"))
                    .build();
        }
    }
}
