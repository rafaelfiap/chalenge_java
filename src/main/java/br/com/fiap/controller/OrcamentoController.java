package br.com.fiap.controller;

import br.com.fiap.dtos.OrcamentoDto;
import br.com.fiap.exceptions.OrcamentoNotFoundException;
import br.com.fiap.exceptions.OrcamentoNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Orcamento;
import br.com.fiap.services.interfaces.OrcamentoService;
import br.com.fiap.services.OrcamentoServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/orcamento")
public class OrcamentoController {

    // Instância de OrcamentoService obtida da factory
    private final OrcamentoService orcamentoService = OrcamentoServiceFactory.create();

    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(OrcamentoDto input) throws UnsupportedServiceOperationException {
        if (input.getIdOrcamento() == null) {
            try {
                // Criação do objeto Orcamento com base no DTO recebido
                Orcamento orcamento = this.orcamentoService.create(
                        new Orcamento(
                                null,
                                input.getValorOrcamento(),
                                input.getSituacao(),
                                input.getIdVeiculo(),
                                input.getIdOficina(),
                                input.getIdServico(),
                                input.getIdPeca()
                        )
                );
                return Response
                        .status(Response.Status.CREATED)
                        .entity(orcamento)
                        .build();
            } catch (SQLException | OrcamentoNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir orçamento"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novos orçamentos"))
                    .build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(this.orcamentoService.findAll()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, OrcamentoDto input) {
        try {
            // Atualização do objeto Orcamento com base no DTO e ID recebidos
            Orcamento updated = this.orcamentoService.update(
                    new Orcamento(
                            id,
                            input.getValorOrcamento(),
                            input.getSituacao(),
                            input.getIdVeiculo(),
                            input.getIdOficina(),
                            input.getIdServico(),
                            input.getIdPeca()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (OrcamentoNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar orçamento"))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.orcamentoService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (OrcamentoNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar orçamento"))
                    .build();
        }
    }
}
