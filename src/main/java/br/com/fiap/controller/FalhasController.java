package br.com.fiap.controller;

import br.com.fiap.dtos.FalhasDto;
import br.com.fiap.exceptions.FalhasNotFoundException;
import br.com.fiap.exceptions.FalhasNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Falhas;
import br.com.fiap.services.interfaces.FalhasService;
import br.com.fiap.services.FalhasServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/Falhas")
public class FalhasController {

    // Instância de FalhasService obtida da factory
    private final FalhasService FalhasService = FalhasServiceFactory.create();

    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(FalhasDto input) throws UnsupportedServiceOperationException {
        if (input.getIdFalha() == null) {
            try {
                // Criação do objeto Falhass com base no DTO recebido
                Falhas Falhas = this.FalhasService.create(
                        new Falhas(
                                null,
                                input.getDescricaoFalha(),
                                input.getDescricaoSolucao(),
                                input.getIdOrcamento(),
                                input.getIdVeiculo(),
                                input.getGravidade()
                        )
                );
                return Response
                        .status(Response.Status.CREATED)
                        .entity(Falhas)
                        .build();
            } catch (SQLException | FalhasNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir Falhas"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novas Falhass"))
                    .build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(this.FalhasService.findAll()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, FalhasDto input) {
        try {
            // Atualização do objeto Falhass com base no DTO e ID recebidos
            Falhas updated = this.FalhasService.update(
                    new Falhas(
                            id,
                            input.getDescricaoFalha(),
                            input.getDescricaoSolucao(),
                            input.getIdOrcamento(),
                            input.getIdVeiculo(),
                            input.getGravidade()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (FalhasNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar Falhas"))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.FalhasService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (FalhasNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar Falhas"))
                    .build();
        }
    }
}
