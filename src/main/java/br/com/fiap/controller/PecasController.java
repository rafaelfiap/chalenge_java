package br.com.fiap.controller;

import br.com.fiap.dtos.PecasDto;
import br.com.fiap.exceptions.PecasNotFoundException;
import br.com.fiap.exceptions.PecasNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Pecas;
import br.com.fiap.services.interfaces.PecasService;
import br.com.fiap.services.PecasServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/pecas")
public class PecasController {

    // Instância de PecasService obtida da factory
    private final PecasService pecasService = PecasServiceFactory.create();

    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(PecasDto input) throws UnsupportedServiceOperationException {
        if (input.getIdPeca() == null) {
            try {
                // Criação do objeto Pecas com base no DTO recebido
                Pecas peca = this.pecasService.create(
                        new Pecas(
                                null,
                                input.getMarca(),
                                input.getQuantidade(),
                                input.getValor(),
                                input.getDescricao(),
                                input.getIdOrcamento(),
                                input.getIdServico()
                        )
                );
                return Response
                        .status(Response.Status.CREATED)
                        .entity(peca)
                        .build();
            } catch (SQLException | PecasNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir peça"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novas peças"))
                    .build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(this.pecasService.findAll()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, PecasDto input) {
        try {
            // Atualização do objeto Pecas com base no DTO e ID recebidos
            Pecas updated = this.pecasService.update(
                    new Pecas(
                            id,
                            input.getMarca(),
                            input.getQuantidade(),
                            input.getValor(),
                            input.getDescricao(),
                            input.getIdOrcamento(),
                            input.getIdServico()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (PecasNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar peça"))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.pecasService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (PecasNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar peça"))
                    .build();
        }
    }
}
